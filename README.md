# SmartTestGen

An intelligent Java application that automatically generates JUnit test cases from Markdown requirement files using AI. It supports OpenAI/Claude models and integrates with JIRA and XRay for automated test management.

---

## 🚀 Features

- Parse Markdown requirement files
- AI-powered test case generation
- Generate production-ready JUnit test classes
- Create positive, negative, and edge test cases
- JIRA integration
- XRay integration
- Logging and automated reporting

---

## 📂 Project Structure

```
test-case-generator/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── examples/
├── generated-tests/
├── application.properties
├── pom.xml
└── README.md
```

---

## 🛠 Prerequisites

- Java 11+
- Gradle 7+
- OpenAI or Claude API Key
- JIRA Credentials (Optional)
- XRay API Token (Optional)

---

## ⚙️ Installation

Clone the project and navigate into the directory.

```bash
cd project-directory
```

Configure API keys in `application.properties` or environment variables.

Build the project:

```bash
gradle clean build
```

or

```bash
./gradlew clean build
```

---

## ▶️ Usage

Run with default requirements file:

```bash
gradle run
```

Run with a custom Markdown file:

```bash
gradle run --args="examples/requirements.md generated-tests"
```

Using Gradle Wrapper:

```bash
./gradlew run --args="examples/requirements.md generated-tests"
```

---

## 📋 Workflow

1. Create a Markdown requirements file.
2. Run SmartTestGen.
3. AI generates JUnit test cases.
4. Review generated tests.
5. Optionally sync with JIRA and XRay.

---

## 📝 Example Requirement

```markdown
## User Login

**Description:** User authentication

### Acceptance Criteria

- User logs in with valid credentials
- Invalid credentials display an error
- Session expires after 30 minutes
```

---

## 💻 Example Generated Test

```java
@Test
public void testUserLoginWithValidCredentials() {
    // Navigate to login page
    // Enter valid credentials
    // Click Login

    assertTrue(true);
}
```

---

## ⚙ Configuration

### LLM

| Variable | Description |
|----------|-------------|
| LLM_PROVIDER | openai / claude |
| LLM_API_KEY | API Key |
| LLM_MODEL | GPT or Claude Model |

### JIRA

| Variable | Description |
|----------|-------------|
| JIRA_URL | JIRA Instance URL |
| JIRA_USERNAME | Username |
| JIRA_API_TOKEN | API Token |
| JIRA_PROJECT_KEY | Project Key |

### XRay

| Variable | Description |
|----------|-------------|
| XRAY_API_TOKEN | XRay API Token |

---

## 🤖 Supported AI Models

### OpenAI
- GPT-4
- GPT-4 Turbo
- GPT-3.5 Turbo

### Claude
- Claude 3 Opus
- Claude 3 Sonnet

---

## 📈 Best Practices

- Write clear Markdown requirements.
- Review generated test cases.
- Add assertions based on project logic.
- Store API keys securely using environment variables.

---

## 🔧 Troubleshooting

### Build Issues

```bash
gradle clean build
gradle dependencies
java -version
```

### API Errors

- Verify API Key
- Check internet connection
- Ensure API quota is available

### JIRA Issues

- Verify URL
- Check API Token
- Confirm Project Key

---

## 🤝 Contributing

Contributions are welcome.

You can improve the project by:

- Adding new AI providers
- Supporting more testing frameworks
- Enhancing Markdown parsing
- Improving integrations

---

## 🚀 Future Enhancements

- Multiple Markdown file support
- Parallel test generation
- TestNG and Cucumber support
- Web Dashboard
- Automated test execution
- Improved AI prompts
