# 🤖 Smart Test Case Generator

> **AI-powered test automation tool that converts Markdown requirements into production-ready JUnit test cases.**

**Smart Test Case Generator** is an intelligent Java-based application designed to simplify software testing by automatically generating JUnit test cases from Markdown requirement documents using Large Language Models (LLMs).

The application can analyze functional requirements, identify different testing scenarios, and generate **positive, negative, and edge-case test cases**. It also provides optional integrations with **JIRA and XRay** for streamlined test management and tracking.

---

## ✨ Key Features

* 📝 **Markdown Requirement Parsing**
  Read and process structured software requirements written in Markdown.

* 🤖 **AI-Powered Test Generation**
  Use LLMs to analyze requirements and automatically generate relevant test cases.

* 🧪 **JUnit Test Generation**
  Generate Java/JUnit test classes based on identified requirements and scenarios.

* ✅ **Multiple Test Scenarios**

  * Positive test cases
  * Negative test cases
  * Edge cases
  * Boundary conditions

* 🔗 **JIRA Integration**
  Connect generated test information with JIRA projects.

* 🧩 **XRay Integration**
  Support automated test management through XRay.

* 📊 **Logging & Reporting**
  Track the generation process and application execution through logs and reports.

* 🔐 **Environment-Based Configuration**
  Keep API keys and credentials outside the source code.

---

## 🏗️ How It Works

```text
        ┌──────────────────────┐
        │ Markdown Requirements│
        └──────────┬───────────┘
                   │
                   ▼
        ┌──────────────────────┐
        │ Requirement Parser   │
        └──────────┬───────────┘
                   │
                   ▼
        ┌──────────────────────┐
        │   AI / LLM Engine    │
        │ OpenAI / Claude      │
        └──────────┬───────────┘
                   │
                   ▼
        ┌──────────────────────┐
        │ Test Case Generator  │
        └──────────┬───────────┘
                   │
                   ▼
        ┌──────────────────────┐
        │ Generated JUnit Tests│
        └──────────┬───────────┘
                   │
          ┌────────┴────────┐
          ▼                 ▼
     ┌─────────┐       ┌─────────┐
     │  JIRA   │       │  XRay   │
     └─────────┘       └─────────┘
```

### Workflow

1. Provide software requirements in Markdown format.
2. The application parses the requirements.
3. The selected AI model analyzes the requirements.
4. Test scenarios are identified automatically.
5. JUnit test classes are generated.
6. Developers/QA engineers review the generated tests.
7. Tests can optionally be synchronized with JIRA/XRay.

---

## 📂 Project Structure

```text
Smart_Test_Case_Generator/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   │
│   └── test/
│       └── java/
│
├── examples/
│   └── requirements.md
│
├── generated-tests/
│   └── GeneratedTest.java
│
├── application.properties
├── build.gradle
├── gradlew
├── gradlew.bat
└── README.md
```

> **Note:** The exact structure may vary depending on your implementation.

---

## 🛠️ Tech Stack

| Technology  | Purpose                       |
| ----------- | ----------------------------- |
| ☕ Java 11+  | Core application              |
| 🧪 JUnit    | Test case generation          |
| 🐘 Gradle   | Build & dependency management |
| 🤖 OpenAI   | AI-powered generation         |
| 🧠 Claude   | Alternative AI provider       |
| 📝 Markdown | Requirement input             |
| 🔗 JIRA     | Test/project management       |
| 🧩 XRay     | Test management               |
| 📋 Logging  | Application monitoring        |

---

## 📋 Prerequisites

Before running the project, make sure you have:

* **Java 11 or higher**
* **Gradle 7+** or Gradle Wrapper
* OpenAI or Claude API key
* JIRA credentials *(optional)*
* XRay API token *(optional)*
* Internet connection for AI/API integrations

Verify Java:

```bash
java -version
```

Verify Gradle:

```bash
gradle -version
```

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/<your-username>/Smart_Test_Case_Generator.git
```

Navigate to the project:

```bash
cd Smart_Test_Case_Generator
```

---

### 2. Configure Environment

Configure your API credentials using environment variables or your application configuration.

**Recommended:** Use environment variables instead of committing API keys to GitHub.

Example:

```bash
LLM_PROVIDER=openai
LLM_API_KEY=your_api_key
LLM_MODEL=your_model
```

For JIRA:

```bash
JIRA_URL=https://your-domain.atlassian.net
JIRA_USERNAME=your-email
JIRA_API_TOKEN=your_token
JIRA_PROJECT_KEY=PROJECT
```

For XRay:

```bash
XRAY_API_TOKEN=your_token
```

> ⚠️ **Never commit API keys, passwords, or access tokens to the repository.**

---

## 🔨 Build the Project

Using Gradle:

```bash
gradle clean build
```

Using Gradle Wrapper:

```bash
./gradlew clean build
```

On Windows:

```bash
gradlew.bat clean build
```

---

## ▶️ Running the Application

Run with the default configuration:

```bash
gradle run
```

Run with a custom Markdown requirements file:

```bash
gradle run --args="examples/requirements.md generated-tests"
```

Using Gradle Wrapper:

```bash
./gradlew run --args="examples/requirements.md generated-tests"
```

### Command Format

```text
<requirements-file> <output-directory>
```

Example:

```bash
./gradlew run --args="examples/login-requirements.md generated-tests"
```

---

## 📝 Example Requirement

Create a Markdown file such as:

```markdown
# User Login

## Description

The application should allow registered users to securely log in.

## Acceptance Criteria

- User can log in using valid credentials.
- Invalid credentials should display an appropriate error message.
- Empty username should display a validation message.
- Empty password should display a validation message.
- Account should be temporarily locked after multiple failed attempts.
- User session should expire after 30 minutes.
```

The AI analyzes these requirements and identifies possible test scenarios.

---

## 🧪 Example Generated Test

A simplified generated JUnit test may look like:

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginTest {

    @Test
    void testLoginWithValidCredentials() {
        // Arrange
        // Act
        // Assert

        assertTrue(true);
    }

    @Test
    void testLoginWithInvalidCredentials() {
        // Arrange
        // Act
        // Assert

        assertTrue(true);
    }

    @Test
    void testLoginWithEmptyPassword() {
        // Arrange
        // Act
        // Assert

        assertTrue(true);
    }
}
```

> The actual generated test implementation depends on the provided requirements, AI model, project configuration, and available application/test infrastructure.

---

## ⚙️ Configuration

### 🤖 LLM Configuration

| Variable       | Description               | Example        |
| -------------- | ------------------------- | -------------- |
| `LLM_PROVIDER` | AI provider               | `openai`       |
| `LLM_API_KEY`  | Provider API key          | `your-api-key` |
| `LLM_MODEL`    | Model used for generation | `your-model`   |

Supported providers:

```text
OpenAI
Claude
```

---

### 🔗 JIRA Configuration

| Variable           | Description        |
| ------------------ | ------------------ |
| `JIRA_URL`         | JIRA instance URL  |
| `JIRA_USERNAME`    | JIRA account/email |
| `JIRA_API_TOKEN`   | JIRA API token     |
| `JIRA_PROJECT_KEY` | Target project key |

Example:

```properties
JIRA_URL=https://your-domain.atlassian.net
JIRA_USERNAME=your-email@example.com
JIRA_API_TOKEN=your-api-token
JIRA_PROJECT_KEY=TEST
```

---

### 🧩 XRay Configuration

| Variable         | Description               |
| ---------------- | ------------------------- |
| `XRAY_API_TOKEN` | XRay authentication token |

---

## 🤖 AI Provider Support

The application is designed to support multiple LLM providers.

### OpenAI

* GPT models
* Configurable model selection
* Requirement analysis
* Test scenario generation

### Claude

* Claude models
* Configurable model selection
* Requirement analysis
* Test scenario generation

> Model names and availability may change over time. Configure the model supported by your API account.

---

## 🎯 Generated Test Categories

The generator aims to identify different types of testing scenarios:

### ✅ Positive Testing

Valid inputs and expected successful behavior.

### ❌ Negative Testing

Invalid inputs, incorrect data, and failure scenarios.

### ⚠️ Edge-Case Testing

Unusual or extreme conditions that may expose unexpected behavior.

### 📏 Boundary Testing

Testing values around minimum, maximum, and allowed limits.

---

## 🔐 Security

Security is important when working with AI APIs and project management tools.

### Recommended Practices

* Store API keys in environment variables.
* Do not commit `.env` files or secrets.
* Do not hard-code credentials.
* Add sensitive configuration files to `.gitignore`.
* Use separate API credentials for development and production.
* Rotate exposed API keys immediately.

Example `.gitignore`:

```gitignore
.env
*.key
application-local.properties
```

---

## 🧪 Testing

Run the project's test suite:

```bash
./gradlew test
```

Windows:

```bash
gradlew.bat test
```

Generate a build report:

```bash
./gradlew build
```

---

## 🔧 Troubleshooting

### Build Failure

Check your Java version:

```bash
java -version
```

Clean and rebuild:

```bash
./gradlew clean build
```

Check dependencies:

```bash
./gradlew dependencies
```

---

### API Authentication Error

Check:

* API key is correct.
* Correct LLM provider is configured.
* Selected model is available.
* API account has sufficient quota/credits.
* Internet connection is working.

---

### JIRA Integration Error

Check:

* JIRA URL is correct.
* Username/email is correct.
* API token is valid.
* Project key exists.
* Account has the required permissions.

---

### Generated Tests Are Incorrect

AI-generated tests should be **reviewed before being used in production**.

Try improving the requirements by including:

* Clear acceptance criteria
* Expected input/output
* Business rules
* Validation requirements
* Boundary conditions
* Error scenarios

---

## 📈 Project Benefits

Smart Test Case Generator helps reduce repetitive manual work involved in creating initial test scenarios.

### Traditional Approach

```text
Requirements
     ↓
Manual Analysis
     ↓
Manual Test Design
     ↓
Manual JUnit Implementation
     ↓
Review
```

### AI-Assisted Approach

```text
Requirements
     ↓
AI Analysis
     ↓
Test Scenario Generation
     ↓
JUnit Generation
     ↓
Human Review
```

The generated tests are intended to **assist developers and QA engineers**, not replace test review or engineering judgment.

---

## 🚀 Future Enhancements

Planned improvements include:

* 📄 Support for multiple Markdown files
* ⚡ Parallel test generation
* 🧪 TestNG support
* 🥒 Cucumber/Gherkin support
* 🌐 Web-based dashboard
* ▶️ Automated test execution
* 📊 Advanced test reports
* 🔄 Improved JIRA/XRay synchronization
* 🧠 Customizable AI prompts
* 📦 Support for additional LLM providers
* 🔍 Requirement-to-test traceability
* 📈 Test coverage analysis

---

## 🤝 Contributing

Contributions are welcome!

### Steps

1. Fork the repository.
2. Create a feature branch.

```bash
git checkout -b feature/new-feature
```

3. Make your changes.
4. Add or update tests.
5. Commit your changes.

```bash
git commit -m "Add new feature"
```

6. Push the branch.

```bash
git push origin feature/new-feature
```

7. Open a Pull Request.

---

## 📌 Best Practices

For better AI-generated test cases:

* Write requirements clearly.
* Use specific acceptance criteria.
* Include expected behavior.
* Mention validation rules.
* Include positive and negative scenarios.
* Define boundary conditions where applicable.
* Always review generated tests.
* Keep API credentials secure.

---

## ⚠️ Disclaimer

AI-generated test cases may contain assumptions or incomplete scenarios. Generated tests should be reviewed, validated, and adapted to the actual application's architecture and business requirements before execution or production use.

---

## 📄 License

This project is available under the license specified in the repository.

---

## 👨‍💻 Author

**Chetan Patil**

Full Stack Developer | JavaScript | React | Java | AI Integration

GitHub: `https://github.com/Chetan323212`

---

⭐ **If you find this project useful, consider giving the repository a star!**
