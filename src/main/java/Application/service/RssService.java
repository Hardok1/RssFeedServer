package Application.service;

import Application.domain.dto.RssDto;


public interface RssService {

    RssDto getRssFeed(String url);

    boolean isRight();
}
