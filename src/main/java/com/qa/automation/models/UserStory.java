package com.qa.automation.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Model to represent a user story extracted from markdown requirements
 */
public class UserStory {
    private String id;
    private String title;
    private String description;
    private List<String> acceptanceCriteria;
    private String priority;
    private String component;

    public UserStory() {
        this.acceptanceCriteria = new ArrayList<>();
    }

    public UserStory(String id, String title, String description) {
        this();
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(List<String> acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public void addAcceptanceCriteria(String criteria) {
        this.acceptanceCriteria.add(criteria);
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return "UserStory{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", acceptanceCriteria=" + acceptanceCriteria +
                ", priority='" + priority + '\'' +
                ", component='" + component + '\'' +
                '}';
    }
}
