package Application.domain.dto;

import Application.Application;

public class RssFeed {
    private String title;
    private String description;
    private String publicationDate;
    private String category;

    public RssFeed(){

    }

    public RssFeed(String title, String description, String publicationDate, String category) {
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getCategory() {
        return category;
    }
}
