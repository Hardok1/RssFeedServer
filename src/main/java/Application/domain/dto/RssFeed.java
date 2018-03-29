package Application.domain.dto;

public class RssFeed {
    private String title;
    private String description;
    private String publicationDate;
    private String category;
    private String link;

    public RssFeed(){

    }

    public RssFeed(String title, String description, String publicationDate, String category, String link) {
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.category = category;
        this.link = link;
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

    public String getLink() {
        return link;
    }
}
