package Application.service;

import Application.RssDto;


public interface RssService {

    RssDto getRssFeed();

    boolean isRight();
}
