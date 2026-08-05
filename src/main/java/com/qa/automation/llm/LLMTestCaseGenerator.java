package com.qa.automation.llm;

import com.qa.automation.models.UserStory;
import com.qa.automation.models.TestCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Uses OpenAI/Claude LLM to analyze requirements and generate test cases
 */
public class LLMTestCaseGenerator {
    private static final Logger logger = LoggerFactory.getLogger(LLMTestCaseGenerator.class);
    
    private String llmProvider; // "openai" or "claude"
    private String apiKey;
    private String model;

    public LLMTestCaseGenerator(String llmProvider, String apiKey, String model) {
        this.llmProvider = llmProvider;
        this.apiKey = apiKey;
        this.model = model;
    }

    /**
     * Generate test cases from a user story using LLM
     */
    public List<TestCase> generateTestCasesFromStory(UserStory story) {
        logger.info("Generating test cases for user story: {}", story.getTitle());

        String prompt = buildPrompt(story);
        String response = callLLM(prompt);
        
        return parseTestCasesFromResponse(response, story);
    }

    /**
     * Build a structured prompt for the LLM
     */
    private String buildPrompt(UserStory story) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate comprehensive JUnit test cases for the following user story:\n\n");
        prompt.append("Title: ").append(story.getTitle()).append("\n");
        prompt.append("Description: ").append(story.getDescription()).append("\n");
        prompt.append("Priority: ").append(story.getPriority()).append("\n");
        prompt.append("Component: ").append(story.getComponent()).append("\n\n");
        
        prompt.append("Acceptance Criteria:\n");
        for (String criteria : story.getAcceptanceCriteria()) {
            prompt.append("- ").append(criteria).append("\n");
        }

        prompt.append("\nGenerate test cases in the following JSON format:\n");
        prompt.append("[\n");
        prompt.append("  {\n");
        prompt.append("    \"testName\": \"testSomething\",\n");
        prompt.append("    \"description\": \"Test description\",\n");
        prompt.append("    \"testType\": \"positive|negative|edge\",\n");
        prompt.append("    \"priority\": \"high|medium|low\",\n");
        prompt.append("    \"preconditions\": [\"condition1\", \"condition2\"],\n");
        prompt.append("    \"testSteps\": [\"step1\", \"step2\"],\n");
        prompt.append("    \"expectedResult\": \"Expected outcome\"\n");
        prompt.append("  }\n");
        prompt.append("]\n\n");
        prompt.append("Generate 3-5 test cases (positive, negative, and edge cases). Return ONLY valid JSON.");

        return prompt.toString();
    }

    /**
     * Call the LLM API
     */
    private String callLLM(String prompt) {
        logger.debug("Calling LLM provider: {}", llmProvider);
        
        if ("openai".equalsIgnoreCase(llmProvider)) {
            return callOpenAI(prompt);
        } else if ("claude".equalsIgnoreCase(llmProvider)) {
            return callClaude(prompt);
        } else {
            throw new IllegalArgumentException("Unknown LLM provider: " + llmProvider);
        }
    }

    /**
     * Call OpenAI API (requires implementation with actual OpenAI SDK)
     */
    private String callOpenAI(String prompt) {
        logger.info("Calling OpenAI API with model: {}", model);
        
        // Note: In production, use the actual OpenAI Java SDK
        // This is a mock implementation for demonstration
        try {
            // TODO: Implement actual OpenAI API call
            // Example using okhttp3 library
            String mockResponse = generateMockTestCases();
            logger.info("Received response from OpenAI");
            return mockResponse;
        } catch (Exception e) {
            logger.error("Error calling OpenAI API", e);
            throw new RuntimeException("Failed to call OpenAI API", e);
        }
    }

    /**
     * Call Claude API (requires implementation with actual Anthropic SDK)
     */
    private String callClaude(String prompt) {
        logger.info("Calling Claude API with model: {}", model);
        
        // Note: In production, use the actual Anthropic Claude SDK
        // This is a mock implementation for demonstration
        try {
            // TODO: Implement actual Claude API call
            String mockResponse = generateMockTestCases();
            logger.info("Received response from Claude");
            return mockResponse;
        } catch (Exception e) {
            logger.error("Error calling Claude API", e);
            throw new RuntimeException("Failed to call Claude API", e);
        }
    }

    /**
     * Parse test cases from LLM response
     */
    private List<TestCase> parseTestCasesFromResponse(String response, UserStory story) {
        List<TestCase> testCases = new ArrayList<>();
        
        try {
            // Extract JSON array from response
            String jsonArray = extractJsonFromResponse(response);
            ObjectMapper mapper = new ObjectMapper();
            
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> testCaseData = mapper.readValue(jsonArray, List.class);
            
            for (Map<String, Object> data : testCaseData) {
                TestCase tc = new TestCase();
                tc.setTestName((String) data.get("testName"));
                tc.setDescription((String) data.get("description"));
                tc.setTestType((String) data.get("testType"));
                tc.setPriority((String) data.get("priority"));
                tc.setRelatedUserStoryId(story.getId());
                tc.setExpectedResult((String) data.get("expectedResult"));
                
                @SuppressWarnings("unchecked")
                List<String> preconditions = (List<String>) data.get("preconditions");
                if (preconditions != null) {
                    tc.setPreconditions(preconditions);
                }
                
                @SuppressWarnings("unchecked")
                List<String> steps = (List<String>) data.get("testSteps");
                if (steps != null) {
                    tc.setTestSteps(steps);
                }
                
                testCases.add(tc);
            }
            
            logger.info("Parsed {} test cases from LLM response", testCases.size());
        } catch (Exception e) {
            logger.error("Error parsing test cases from LLM response", e);
        }
        
        return testCases;
    }

    /**
     * Extract JSON array from LLM response
     */
    private String extractJsonFromResponse(String response) {
        int startIndex = response.indexOf('[');
        int endIndex = response.lastIndexOf(']');
        
        if (startIndex >= 0 && endIndex > startIndex) {
            return response.substring(startIndex, endIndex + 1);
        }
        
        return response;
    }

    /**
     * Generate mock test cases for demonstration
     */
    private String generateMockTestCases() {
        return """
                [
                  {
                    "testName": "testUserLoginWithValidCredentials",
                    "description": "Verify user can login with valid email and password",
                    "testType": "positive",
                    "priority": "high",
                    "preconditions": ["User is not logged in", "Valid account exists"],
                    "testSteps": ["Navigate to login page", "Enter valid email", "Enter valid password", "Click login button"],
                    "expectedResult": "User is successfully logged in and redirected to dashboard"
                  },
                  {
                    "testName": "testUserLoginWithInvalidPassword",
                    "description": "Verify login fails with invalid password",
                    "testType": "negative",
                    "priority": "high",
                    "preconditions": ["User is not logged in", "Valid account exists"],
                    "testSteps": ["Navigate to login page", "Enter valid email", "Enter invalid password", "Click login button"],
                    "expectedResult": "Error message displayed: 'Invalid credentials'"
                  },
                  {
                    "testName": "testUserLoginWithNonexistentEmail",
                    "description": "Verify login fails with non-existent email",
                    "testType": "negative",
                    "priority": "medium",
                    "preconditions": ["User is not logged in"],
                    "testSteps": ["Navigate to login page", "Enter non-existent email", "Enter any password", "Click login button"],
                    "expectedResult": "Error message displayed: 'User not found'"
                  },
                  {
                    "testName": "testUserLoginWithEmptyEmailField",
                    "description": "Verify login validation for empty email",
                    "testType": "edge",
                    "priority": "medium",
                    "preconditions": ["User is not logged in"],
                    "testSteps": ["Navigate to login page", "Leave email field empty", "Enter password", "Click login button"],
                    "expectedResult": "Validation error displayed: 'Email is required'"
                  },
                  {
                    "testName": "testUserLoginSessionTimeout",
                    "description": "Verify session handling after timeout",
                    "testType": "negative",
                    "priority": "medium",
                    "preconditions": ["User is logged in", "Session timeout is 30 minutes"],
                    "testSteps": ["User logs in", "Wait for 31 minutes of inactivity", "Perform any action"],
                    "expectedResult": "User is logged out and redirected to login page"
                  }
                ]
                """;
    }
}
