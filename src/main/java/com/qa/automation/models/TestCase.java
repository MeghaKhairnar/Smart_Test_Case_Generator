package com.qa.automation.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Model to represent a generated test case
 */
public class TestCase {
    private String id;
    private String testName;
    private String description;
    private List<String> preconditions;
    private List<String> testSteps;
    private String expectedResult;
    private String testType; // positive, negative, edge case
    private String priority;
    private String relatedUserStoryId;
    private String jiraTestKey; // if integrated with JIRA

    public TestCase() {
        this.preconditions = new ArrayList<>();
        this.testSteps = new ArrayList<>();
    }

    public TestCase(String testName, String description) {
        this();
        this.testName = testName;
        this.description = description;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(List<String> preconditions) {
        this.preconditions = preconditions;
    }

    public void addPrecondition(String precondition) {
        this.preconditions.add(precondition);
    }

    public List<String> getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(List<String> testSteps) {
        this.testSteps = testSteps;
    }

    public void addTestStep(String step) {
        this.testSteps.add(step);
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRelatedUserStoryId() {
        return relatedUserStoryId;
    }

    public void setRelatedUserStoryId(String relatedUserStoryId) {
        this.relatedUserStoryId = relatedUserStoryId;
    }

    public String getJiraTestKey() {
        return jiraTestKey;
    }

    public void setJiraTestKey(String jiraTestKey) {
        this.jiraTestKey = jiraTestKey;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "id='" + id + '\'' +
                ", testName='" + testName + '\'' +
                ", testType='" + testType + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
