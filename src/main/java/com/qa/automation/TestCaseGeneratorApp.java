package com.qa.automation;

import com.qa.automation.models.UserStory;
import com.qa.automation.models.TestCase;
import com.qa.automation.parser.MarkdownRequirementParser;
import com.qa.automation.llm.LLMTestCaseGenerator;
import com.qa.automation.codegen.JUnitCodeGenerator;
import com.qa.automation.integration.JiraIntegration;
import com.qa.automation.integration.XRayIntegration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Main application class for AI Test Case Generator
 */
public class TestCaseGeneratorApp {
    private static final Logger logger = LoggerFactory.getLogger(TestCaseGeneratorApp.class);

    public static void main(String[] args) {
        logger.info("========================================");
        logger.info("AI Test Case Generator - v1.0.0");
        logger.info("========================================\n");

        try {
            // Configuration
            String llmProvider = getConfig("LLM_PROVIDER", "openai");
            String llmApiKey = getConfig("LLM_API_KEY", "");
            String llmModel = getConfig("LLM_MODEL", "gpt-4");
            
            String jiraUrl = getConfig("JIRA_URL", "");
            String jiraUsername = getConfig("JIRA_USERNAME", "");
            String jiraApiToken = getConfig("JIRA_API_TOKEN", "");
            String jiraProjectKey = getConfig("JIRA_PROJECT_KEY", "TEST");
            
            String xrayApiToken = getConfig("XRAY_API_TOKEN", "");
            
            String requirementsFile = args.length > 0 ? args[0] : "examples/requirements.md";
            String outputDir = args.length > 1 ? args[1] : "generated-tests";

            logger.info("Configuration:");
            logger.info("  LLM Provider: {}", llmProvider);
            logger.info("  LLM Model: {}", llmModel);
            logger.info("  JIRA Project: {}", jiraProjectKey);
            logger.info("  Requirements File: {}", requirementsFile);
            logger.info("  Output Directory: {}\n", outputDir);

            // Step 1: Parse requirements
            logger.info("Step 1: Parsing requirements from {}", requirementsFile);
            MarkdownRequirementParser parser = new MarkdownRequirementParser();
            List<UserStory> userStories = parser.parseMarkdownFile(new File(requirementsFile));
            logger.info("  ✓ Found {} user stories\n", userStories.size());

            // Step 2: Generate test cases using LLM
            logger.info("Step 2: Generating test cases using {} LLM", llmProvider);
            LLMTestCaseGenerator llmGenerator = new LLMTestCaseGenerator(llmProvider, llmApiKey, llmModel);
            List<TestCase> allTestCases = new ArrayList<>();

            for (UserStory story : userStories) {
                logger.info("  Processing story: {}", story.getTitle());
                List<TestCase> testCases = llmGenerator.generateTestCasesFromStory(story);
                allTestCases.addAll(testCases);
                logger.info("    ✓ Generated {} test cases", testCases.size());
            }
            logger.info("  ✓ Total test cases generated: {}\n", allTestCases.size());

            // Step 3: Generate JUnit code
            logger.info("Step 3: Generating JUnit test code");
            JUnitCodeGenerator codeGenerator = new JUnitCodeGenerator("com.example.tests", "GeneratedTestSuite");
            String junitCode = codeGenerator.generateTestClass(allTestCases);
            
            // Save JUnit code to file
            String outputFile = outputDir + "/GeneratedTestSuite.java";
            new File(outputDir).mkdirs();
            Files.write(Paths.get(outputFile), junitCode.getBytes());
            logger.info("  ✓ JUnit code generated: {}\n", outputFile);

            // Step 4: Create test cases in JIRA (optional)
            if (!jiraUrl.isEmpty() && !jiraApiToken.isEmpty()) {
                logger.info("Step 4: Creating test cases in JIRA");
                JiraIntegration jiraIntegration = new JiraIntegration(jiraUrl, jiraUsername, jiraApiToken, jiraProjectKey);
                List<String> jiraKeys = jiraIntegration.createTestCases(allTestCases);
                logger.info("  ✓ Created {} test cases in JIRA\n", jiraKeys.size());

                // Step 5: Sync with XRay (optional)
                if (!xrayApiToken.isEmpty()) {
                    logger.info("Step 5: Syncing test cases with XRay");
                    XRayIntegration xrayIntegration = new XRayIntegration(jiraUrl, xrayApiToken, jiraProjectKey);
                    List<String> xrayTestIds = xrayIntegration.bulkCreateTests(allTestCases);
                    logger.info("  ✓ Synced {} test cases with XRay\n", xrayTestIds.size());
                }
            } else {
                logger.info("Step 4-5: Skipping JIRA/XRay integration (not configured)\n");
            }

            // Summary
            logger.info("========================================");
            logger.info("Generation Complete!");
            logger.info("========================================");
            logger.info("Summary:");
            logger.info("  User Stories: {}", userStories.size());
            logger.info("  Test Cases Generated: {}", allTestCases.size());
            logger.info("  Output File: {}", outputFile);
            logger.info("========================================\n");

            // Print sample test cases
            logger.info("Sample Generated Test Cases:");
            allTestCases.stream().limit(3).forEach(tc -> {
                logger.info("\n  Test: {}", tc.getTestName());
                logger.info("    Description: {}", tc.getDescription());
                logger.info("    Type: {}", tc.getTestType());
                logger.info("    Priority: {}", tc.getPriority());
            });

        } catch (IOException e) {
            logger.error("Error reading requirements file", e);
            System.exit(1);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            System.exit(1);
        }
    }

    /**
     * Get configuration value from environment or use default
     */
    private static String getConfig(String key, String defaultValue) {
        String value = System.getenv(key);
        return value != null ? value : defaultValue;
    }
}
