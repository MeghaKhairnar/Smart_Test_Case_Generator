package com.qa.automation.parser;

import com.qa.automation.models.UserStory;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses markdown files to extract user stories and requirements
 */
public class MarkdownRequirementParser {
    private static final Logger logger = LoggerFactory.getLogger(MarkdownRequirementParser.class);

    /**
     * Parse a markdown file and extract user stories
     */
    public List<UserStory> parseMarkdownFile(File markdownFile) throws IOException {
        logger.info("Parsing markdown file: {}", markdownFile.getAbsolutePath());

        String content = new String(Files.readAllBytes(markdownFile.toPath()));
        return parseMarkdownContent(content);
    }

    /**
     * Parse markdown content and extract user stories
     */
    public List<UserStory> parseMarkdownContent(String markdownContent) {
        List<UserStory> userStories = new ArrayList<>();
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdownContent);

        userStories = extractStoriesFromDocument(document);
        logger.info("Parsed {} user stories from markdown", userStories.size());

        return userStories;
    }

    private List<UserStory> extractStoriesFromDocument(Node document) {
        List<UserStory> stories = new ArrayList<>();
        UserStory currentStory = null;

        Node node = document.getFirstChild();
        while (node != null) {
            if (node instanceof Heading) {
                Heading heading = (Heading) node;
                if (heading.getLevel() == 2) {
                    if (currentStory != null) {
                        stories.add(currentStory);
                    }
                    String title = getTextContent(heading);
                    currentStory = new UserStory();
                    currentStory.setTitle(title);
                    currentStory.setId("STORY_" + title.replaceAll("\\s+", "_").toUpperCase());
                }
            } else if (node instanceof Paragraph && currentStory != null) {
                String text = getTextContent(node);
                if (text.startsWith("Priority:")) {
                    currentStory.setPriority(text.replace("Priority:", "").trim());
                } else if (text.startsWith("Component:")) {
                    currentStory.setComponent(text.replace("Component:", "").trim());
                } else if (text.startsWith("Description:")) {
                    currentStory.setDescription(text.replace("Description:", "").trim());
                } else if (text.contains("Acceptance Criteria") == false && !text.isEmpty() && currentStory.getDescription() == null) {
                    currentStory.setDescription(text);
                }
            } else if (node instanceof BulletList && currentStory != null) {
                BulletList list = (BulletList) node;
                Node listChild = list.getFirstChild();
                while (listChild != null) {
                    if (listChild instanceof ListItem) {
                        String criteria = getTextContent(listChild);
                        currentStory.addAcceptanceCriteria(criteria);
                    }
                    listChild = listChild.getNext();
                }
            }
            node = node.getNext();
        }

        if (currentStory != null) {
            stories.add(currentStory);
        }

        return stories;
    }

    private String getTextContent(Node node) {
        StringBuilder sb = new StringBuilder();
        Node child = node.getFirstChild();
        while (child != null) {
            if (child instanceof Text) {
                sb.append(((Text) child).getLiteral());
            } else if (child instanceof SoftLineBreak || child instanceof HardLineBreak) {
                sb.append(" ");
            } else {
                sb.append(getTextContent(child));
            }
            child = child.getNext();
        }
        return sb.toString().trim();
    }
}
