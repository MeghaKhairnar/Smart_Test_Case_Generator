# Getting Started with AI Test Case Generator

## Quick Start (5 minutes)

### 1. Build the Project
```bash
cd /Users/sureshkc/Desktop/demo/SmartTestGen
gradle clean build

# Or using Gradle wrapper
./gradlew clean build
```

### 2. Set LLM API Key
```bash
# Option A: Environment variable
export LLM_API_KEY=sk-your-openai-api-key
export LLM_PROVIDER=openai

# Option B: Edit application.properties
```

### 3. Run the Generator
```bash
gradle run --args="examples/requirements.md generated-tests"

# Or using Gradle wrapper
./gradlew run --args="examples/requirements.md generated-tests"
```

### 4. Check Output
Generated test file: `generated-tests/GeneratedTestSuite.java`

## Configuration

### OpenAI Setup
1. Go to https://platform.openai.com/api-keys
2. Create new API key
3. Set environment variable: `export LLM_API_KEY=sk-...`

### Claude Setup
1. Go to https://console.anthropic.com/
2. Create API key
3. Set: `export LLM_PROVIDER=claude` and `export LLM_API_KEY=claude-...`

### JIRA Setup (Optional)
1. Generate API token in JIRA: Settings → API Tokens
2. Set environment variables:
   ```bash
   export JIRA_URL=https://your-company.atlassian.net
   export JIRA_USERNAME=your-email@example.com
   export JIRA_API_TOKEN=your-token
   export JIRA_PROJECT_KEY=TEST
   ```

### XRay Setup (Optional)
1. Get XRay API token from your Jira instance
2. Set environment variable: `export XRAY_API_TOKEN=your-token`

## Using Your Own Requirements

1. Create a markdown file with user stories (see `examples/requirements.md` for format)
2. Run:
   ```bash
   gradle run --args="your-file.md output-dir"
   
   # Or using Gradle wrapper
   ./gradlew run --args="your-file.md output-dir"
   ```

## Example Requirement Format

```markdown
## Feature Name

**Description:** Feature description

**Priority:** High

**Component:** ComponentName

### Acceptance Criteria
- Criteria 1
- Criteria 2
- Criteria 3
```

## Output Structure

```
generated-tests/
└── GeneratedTestSuite.java      # JUnit test class
```

## Next Steps

1. ✅ Review generated tests
2. ✅ Add test data and assertions
3. ✅ Run tests: `gradle test`
4. ✅ Integrate with CI/CD pipeline
5. ✅ Sync with JIRA/XRay (if configured)

## Support

- **Log files**: Check `logs/test-generator.log` for details
- **Configuration**: Edit `application.properties`
- **Troubleshooting**: See README.md Advanced section
