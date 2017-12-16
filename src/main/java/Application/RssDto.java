package Application;

import java.util.ArrayList;

public class RssDto {
    private ArrayList<RssFeed> feeds = new ArrayList<>();

    public RssDto() {
    }

    public RssDto(ArrayList<RssFeed> feeds) {
        this.feeds = feeds;
    }

    public ArrayList<RssFeed> getFeeds() {
        return feeds;
    }
}
