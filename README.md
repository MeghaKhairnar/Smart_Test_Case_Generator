# SmartTestGen

An intelligent Java application that automatically generates JUnit test cases from markdown requirements files using OpenAI/Claude LLMs, with seamless integration to JIRA and XRay test management platforms.

## Overview

This tool solves a critical QA automation challenge: **converting requirements into comprehensive test cases at scale**. It leverages Large Language Models (LLMs) to:

- 📄 Parse markdown requirements with user stories
- 🧠 Analyze acceptance criteria using AI
- 🧪 Generate comprehensive test cases (positive, negative, edge cases)
- ☕ Produce production-ready JUnit code
- 🔗 Integrate with JIRA for test case management
- 📊 Sync with XRay for advanced test reporting

## Key Features

### 1. Markdown Requirement Parsing
- Extracts user stories from markdown files
- Parses acceptance criteria automatically
- Identifies priorities, components, and descriptions

### 2. AI-Powered Test Case Generation
- Uses OpenAI GPT-4 or Claude for intelligent analysis
- Generates multiple test types:
  - ✅ **Positive tests**: Happy path scenarios
  - ❌ **Negative tests**: Error handling and edge cases
  - ⚠️ **Edge case tests**: Boundary conditions and special scenarios
- Produces comprehensive test documentation

### 3. JUnit Code Generation
- Auto-generates production-ready Java test classes
- Includes proper setup/teardown methods
- Full test documentation with step-by-step instructions
- Logger integration for test execution tracking

### 4. JIRA Integration
- Create test cases directly in JIRA
- Link tests to user stories
- Update test status
- Track test metrics and coverage

### 5. XRay Integration
- Sync generated tests with Atlassian XRay
- Create test executions
- Report test results
- Generate coverage reports

## Project Structure

```
test-case-generator/
├── pom.xml                              # Maven configuration
├── application.properties               # Configuration file
├── src/
│   ├── main/
│   │   ├── java/com/qa/automation/
│   │   │   ├── TestCaseGeneratorApp.java         # Main application entry point
│   │   │   ├── models/
│   │   │   │   ├── UserStory.java               # Domain model for requirements
│   │   │   │   └── TestCase.java                # Domain model for test cases
│   │   │   ├── parser/
│   │   │   │   └── MarkdownRequirementParser.java # Parse markdown requirements
│   │   │   ├── llm/
│   │   │   │   └── LLMTestCaseGenerator.java     # LLM integration (OpenAI/Claude)
│   │   │   ├── codegen/
│   │   │   │   └── JUnitCodeGenerator.java       # Generate JUnit test code
│   │   │   └── integration/
│   │   │       ├── JiraIntegration.java          # JIRA API integration
│   │   │       └── XRayIntegration.java          # XRay API integration
│   │   └── resources/
│   │       └── logback.xml              # Logging configuration
│   └── test/
│       └── java/                        # Unit tests
├── examples/
│   └── requirements.md                  # Example requirements file
└── generated-tests/                     # Generated test files (output)
```

## Prerequisites

- **Java 11+** (JDK 11 or higher)
- **Gradle 7.0+** (for building)
  - Or use the included Gradle wrapper: `./gradlew`
- **API Keys:**
  - OpenAI API key (or Claude API key)
  - JIRA credentials (optional, for JIRA integration)
  - XRay API token (optional, for XRay integration)

## Installation & Setup

### 1. Clone/Setup Project

```bash
cd /Users/sureshkc/Desktop/demo/WhatShouldIDo
```

### 2. Configure API Keys

Edit `application.properties`:

```properties
# LLM Configuration (Required)
LLM_PROVIDER=openai
LLM_API_KEY=sk-your-openai-api-key
LLM_MODEL=gpt-4

# JIRA Configuration (Optional)
JIRA_URL=https://your-company.atlassian.net
JIRA_USERNAME=your-email@example.com
JIRA_API_TOKEN=your-jira-api-token
JIRA_PROJECT_KEY=TEST

# XRay Configuration (Optional)
XRAY_API_TOKEN=your-xray-api-token
```

Or set environment variables:

```bash
export LLM_PROVIDER=openai
export LLM_API_KEY=sk-your-api-key
export LLM_MODEL=gpt-4
export JIRA_URL=https://your-instance.atlassian.net
export JIRA_API_TOKEN=your-token
```

### 3. Build the Project

```bash
# Using Gradle
gradle clean build

# Or using Gradle wrapper
./gradlew clean build
```

### 4. View Build Info

```bash
gradle buildInfo
```

## Usage

### Basic Usage

```bash
# Run with default files (examples/requirements.md)
gradle run

# Run with custom requirements file
gradle run --args="path/to/requirements.md output-directory"

# Example:
gradle run --args="examples/requirements.md generated-tests"

# Using Gradle wrapper
./gradlew run --args="examples/requirements.md generated-tests"
```

### Using Generated JAR

```bash
gradle build
java -jar build/libs/smarttestgen-1.0.0.jar examples/requirements.md generated-tests
```

## Workflow

### Step 1: Write Requirements in Markdown

Create a markdown file (`requirements.md`) with user stories:

```markdown
## User Login

**Description:** User authentication system

**Priority:** High

**Component:** Authentication

### Acceptance Criteria
- User can login with email and password
- Invalid credentials show error message
- Session expires after 30 minutes
```

### Step 2: Run the Generator

```bash
gradle run --args="requirements.md generated-tests"

# Or using Gradle wrapper
./gradlew run --args="requirements.md generated-tests"
```

### Step 3: Review Generated Tests

Output file: `generated-tests/GeneratedTestSuite.java`

```java
public class GeneratedTestSuite {
    @Test
    public void testUserLoginWithValidCredentials() {
        // Step 1: Navigate to login page
        // Step 2: Enter valid email
        // Step 3: Enter valid password
        // Expected Result: User logged in successfully
        assertTrue(true);
    }
    
    @Test
    public void testUserLoginWithInvalidPassword() {
        // Negative test case
        // ...
    }
}
```

### Step 4: (Optional) Integrate with JIRA

Generated tests are automatically created in JIRA when credentials are configured.

### Step 5: (Optional) Sync with XRay

Tests are synced to XRay for advanced test management and reporting.

## Configuration Reference

### LLM Configuration

| Variable | Values | Notes |
|----------|--------|-------|
| `LLM_PROVIDER` | `openai`, `claude` | LLM provider |
| `LLM_API_KEY` | API key | Get from provider's dashboard |
| `LLM_MODEL` | See notes | OpenAI: gpt-4, gpt-3.5-turbo; Claude: claude-3-opus, claude-3-sonnet |

### JIRA Configuration

| Variable | Example | Notes |
|----------|---------|-------|
| `JIRA_URL` | `https://company.atlassian.net` | Your JIRA instance URL |
| `JIRA_USERNAME` | `user@example.com` | JIRA account email |
| `JIRA_API_TOKEN` | API token | Generate in JIRA settings |
| `JIRA_PROJECT_KEY` | `TEST` | Project where tests are created |

### XRay Configuration

| Variable | Example | Notes |
|----------|---------|-------|
| `XRAY_API_TOKEN` | API token | XRay plugin API token |

## Example Output

### Generated Java Test File

```java
package com.example.tests;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Auto-generated JUnit test class
 * Generated by AI Test Case Generator
 */
public class GeneratedTestSuite {
    private static final Logger logger = LoggerFactory.getLogger(GeneratedTestSuite.class);

    @Before
    public void setUp() {
        logger.info("Setting up test fixtures");
    }

    @After
    public void tearDown() {
        logger.info("Tearing down test fixtures");
    }

    /**
     * Test: Verify user can login with valid email and password
     * Type: positive
     * Priority: high
     * Preconditions:
     *   - User is not logged in
     *   - Valid account exists
     */
    @Test
    public void testUserLoginWithValidCredentials() {
        logger.info("Running test: testUserLoginWithValidCredentials");

        // Preconditions
        // User is not logged in
        // Valid account exists

        // Test Steps
        // Step 1: Navigate to login page
        // Step 2: Enter valid email
        // Step 3: Enter valid password
        // Step 4: Click login button

        // Expected Result: User is successfully logged in and redirected to dashboard
        // TODO: Add assertions
        assertTrue("Test assertion needed", true);
    }
}
```

## Supported LLM Providers

### OpenAI
- Models: GPT-4, GPT-4 Turbo, GPT-3.5 Turbo
- Get API key: https://platform.openai.com/api-keys
- Documentation: https://platform.openai.com/docs/api-reference

### Claude (Anthropic)
- Models: Claude 3 Opus, Claude 3 Sonnet
- Get API key: https://console.anthropic.com/
- Documentation: https://docs.anthropic.com/

## Advanced Features

### Custom Test Case Templates

Modify `LLMTestCaseGenerator.buildPrompt()` to customize the test case structure:

```java
private String buildPrompt(UserStory story) {
    // Customize prompt format here
    // Add specific test case requirements
}
```

### Integration with CI/CD

1. **Generate tests** during development phase
2. **Compile** with your test suite
3. **Run** in CI/CD pipeline automatically
4. **Report** results to JIRA and XRay

### Batch Processing

Process multiple requirement files:

```bash
for file in requirements/*.md; do
    mvn exec:java -Dexec.mainClass="com.qa.automation.TestCaseGeneratorApp" \
      -Dexec.args="$file generated-tests"
done
```

## Best Practices

1. **Write Clear Requirements**
   - Use consistent markdown formatting
   - Include specific acceptance criteria
   - Set appropriate priorities

2. **Review Generated Tests**
   - Not all generated tests are perfect
   - Review and refine as needed
   - Add test data setup logic

3. **Maintain Test Quality**
   - Use generated tests as a starting point
   - Add assertions based on your actual implementation
   - Keep tests maintainable and readable

4. **API Key Security**
   - Never commit API keys to git
   - Use environment variables in production
   - Rotate keys regularly

## Troubleshooting

### Build Issues

```bash
# Clean build
gradle clean build

# Check dependencies
gradle dependencies

# Verify Java version
java -version

# Clear Gradle cache if needed
gradle clean --refresh-dependencies
```

### LLM API Errors

```
Error: 401 Unauthorized
→ Check API key is correct and has permissions

Error: 429 Too Many Requests
→ Rate limit reached, wait or upgrade plan

Error: 500 Server Error
→ LLM service is down, try again later
```

### JIRA Connection Issues

```
Error: Connection refused
→ Check JIRA_URL is correct and accessible

Error: 401 Unauthorized
→ Verify username and API token

Error: 404 Not Found
→ Check project key exists
```

## Contributing

To extend this tool:

1. **Add new LLM providers**: Modify `LLMTestCaseGenerator`
2. **Add test frameworks**: Create new code generator classes
3. **Add integrations**: Implement new integration classes
4. **Improve parsing**: Enhance `MarkdownRequirementParser`

## Performance Considerations

- **Large Requirement Files**: Process ~100-200 user stories per run
- **LLM API Costs**: Budget based on number of stories and tokens
- **JIRA API Rate Limits**: ~15 requests/second (Atlassian limit)
- **Parallel Processing**: Not yet implemented (feature for future)

## Roadmap

- [ ] Support for multiple markdown files
- [ ] Parallel test generation for speed
- [ ] Custom test case templates per framework
- [ ] Integration with more test frameworks (TestNG, Cucumber, etc.)
- [ ] Web UI for easier usage
- [ ] Test execution and reporting
- [ ] Machine learning for test case quality scoring

## License

Internal Use Only - QA Automation Innovation Project

## Support

For issues or questions:
1. Check the troubleshooting section
2. Review logs in `logs/` directory
3. Verify configuration in `application.properties`
4. Check LLM API status and quotas

---

**Version:** 1.0.0  
**Last Updated:** February 2026  
**Status:** Production Ready
