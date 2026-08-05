# Gradle Setup Guide

## Project Structure with Gradle

```
test-case-generator/
├── build.gradle                          # Gradle build configuration
├── settings.gradle                       # Gradle settings
├── gradle.properties                     # Gradle properties
├── gradlew                               # Gradle wrapper (Unix)
├── gradlew.bat                           # Gradle wrapper (Windows)
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.jar            # Gradle wrapper JAR
│       └── gradle-wrapper.properties     # Wrapper configuration
├── src/
│   ├── main/
│   │   ├── java/com/qa/automation/      # Source code
│   │   └── resources/                    # Configuration files
│   └── test/
│       └── java/                         # Unit tests
└── build/                                # Generated build output
    ├── classes/                          # Compiled classes
    └── libs/                             # Generated JAR files
```

## Gradle vs Maven Comparison

| Feature | Maven | Gradle |
|---------|-------|--------|
| Build File | `pom.xml` (XML) | `build.gradle` (Groovy/Kotlin) |
| Configuration Language | XML | DSL (Groovy/Kotlin) |
| Performance | Standard | Faster with caching/incremental builds |
| Dependency Format | Coordinates | Similar but more flexible |
| Task Definition | Built-in | Custom tasks easy to define |
| Wrapper | Not included | Included by default |

## Key Gradle Commands

### Build
```bash
# Clean and build
gradle clean build

# Build without tests
gradle build -x test

# Build with specific tasks
gradle compileJava jar
```

### Run
```bash
# Run application
gradle run

# Run with arguments
gradle run --args="file.md output"

# Run with JVM options
gradle run --jvmArgs="-Xmx1g"
```

### Testing
```bash
# Run all tests
gradle test

# Run specific test
gradle test --tests "TestClassName"

# Run test with logging
gradle test -i
```

### Information
```bash
# Show dependencies
gradle dependencies

# Show dependency tree
gradle dependencyTree

# Show tasks
gradle tasks

# Show properties
gradle properties

# Custom info task
gradle buildInfo
```

### Maintenance
```bash
# Clean build artifacts
gradle clean

# Refresh dependencies
gradle clean --refresh-dependencies

# Clear Gradle cache
rm -rf ~/.gradle/caches
gradle clean build

# Check for dependency updates
gradle dependencyUpdates
```

## Gradle Wrapper

The Gradle wrapper (`gradlew`/`gradlew.bat`) ensures everyone uses the same Gradle version:

```bash
# Unix/Mac - automatically executable
./gradlew build

# Windows
gradlew.bat build

# Benefits:
# - No need to install Gradle separately
# - Consistent version across team
# - Automatic download on first use
```

## Configuration Files

### build.gradle
Main build configuration with:
- Plugins (java, application)
- Repositories (mavenCentral)
- Dependencies (libraries)
- Custom tasks
- JAR configuration

### settings.gradle
Project structure definition:
- Root project name
- Subprojects (if any)

### gradle.properties
Build properties:
- Java version
- JVM arguments
- Version info
- Custom properties

## Environment Setup

### Install Gradle (Optional - use wrapper instead)
```bash
# macOS
brew install gradle

# Linux
sudo apt-get install gradle

# Verify
gradle --version
```

### Set Java Version
```bash
# Check installed versions
/usr/libexec/java_home -V

# Set JAVA_HOME
export JAVA_HOME=$(/usr/libexec/java_home -v 11)

# Or in gradle.properties
#org.gradle.java.home=/path/to/java11
```

## Gradle Daemon

Gradle runs a daemon process for faster builds:

```bash
# Status
gradle --status

# Stop daemon
gradle --stop

# Disable daemon (in gradle.properties)
org.gradle.daemon=false

# Run without daemon
gradle build --no-daemon
```

## Gradle Properties

Edit `gradle.properties` to customize:

```properties
# JVM memory
org.gradle.jvmargs=-Xmx2g

# Parallel builds
org.gradle.parallel=true

# Build caching
org.gradle.caching=true

# Java toolchain
java.version=11

# Custom properties
appVersion=1.0.0
appGroup=com.qa.automation
```

## Custom Gradle Tasks

Examples from `build.gradle`:

```bash
# Show build info
gradle buildInfo

# Show help
gradle helpRun

# Run application
gradle run
```

## Troubleshooting

### "Gradle not found"
```bash
# Use wrapper
./gradlew build

# Or install Gradle
brew install gradle
```

### "Java version mismatch"
```bash
# Set correct Java
export JAVA_HOME=$(/usr/libexec/java_home -v 11)

# Or in gradle.properties
org.gradle.java.home=/path/to/java11
```

### "Build fails with dependency errors"
```bash
# Refresh dependencies
gradle clean --refresh-dependencies

# Check dependencies
gradle dependencies
```

### "Gradle daemon issues"
```bash
# Stop daemon
gradle --stop

# Clear cache
rm -rf ~/.gradle/caches

# Rebuild
gradle clean build
```

## CI/CD Integration

### GitHub Actions Example
```yaml
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - run: ./gradlew build
```

### Jenkins Example
```groovy
stage('Build') {
    steps {
        sh './gradlew clean build'
    }
}
```

## Performance Tips

1. **Enable Gradle Daemon** (default):
   ```bash
   org.gradle.daemon=true
   ```

2. **Parallel Builds**:
   ```bash
   org.gradle.parallel=true
   org.gradle.workers.max=4
   ```

3. **Build Caching**:
   ```bash
   org.gradle.caching=true
   ```

4. **Incremental Compilation**:
   ```bash
   org.gradle.incremental=true
   ```

5. **Optimize Heap**:
   ```bash
   org.gradle.jvmargs=-Xmx2g -XX:MaxMetaspaceSize=512m
   ```

## Migration from Maven

Convert from Maven to Gradle:

```bash
# Using gradle init (if you still have pom.xml)
gradle init --type pom

# Key differences:
# pom.xml  → build.gradle
# mvn clean build → gradle build
# mvn test → gradle test
# mvn exec → gradle run
```

## Resources

- Official Documentation: https://docs.gradle.org/
- Gradle Plugins: https://plugins.gradle.org/
- Gradle Community: https://gradle.org/community/

---

This Gradle configuration is production-ready and includes:
- ✅ All required dependencies
- ✅ Custom build tasks
- ✅ Proper JAR packaging
- ✅ Application plugin for easy execution
- ✅ Gradle wrapper for consistency
