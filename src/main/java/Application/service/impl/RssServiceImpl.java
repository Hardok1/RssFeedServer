package Application.service.impl;

import Application.domain.dto.RssDto;
import Application.domain.dto.RssFeed;
import Application.domain.dto.RssFilter;
import Application.service.RssService;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;


@Service
public class RssServiceImpl implements RssService {

    private boolean isRight;
    private ArrayList<RssFeed> feeds = new ArrayList<>(); //Kolekcja ktora bedzie przechowywac obiekty klasy RssFeed
    private SyndFeedInput input = new SyndFeedInput();


    @Override
    public RssDto getRssFeed(String url) {
        try {
            //URL feedURL = new URL("http://fakty.interia.pl/swiat/feed"); //Adres kanalu RSS
            //URL feedURL = new URL("http://www.rmf24.pl/fakty/polska/feed");
            URL feedURL = new URL(url);
            SyndFeed feed = input.build(new XmlReader(feedURL));    //Tutaj przechowujemy caly feed kanalu
            if (feed.getEntries().isEmpty()){
                isRight=false;
            }
            //Petla for-each przechodzimy po kolejnych pozycjach
            for (SyndEntry entry: feed.getEntries()){

                //Pozyskiwanie pól razem z ich walidacją

                // /Uzyskanie tytułu
                String title = "Empty";
                if (!(entry.getTitle().isEmpty())){
                    title = entry.getTitle();
                }

                //Wydzielanie opisu/ pozbywanie sie linkow/obrazkow
                String description = "Empty";
                if (!(entry.getDescription().getValue().isEmpty())) {
                    description = RssFilter.filterDescription(entry.getDescription().getValue());
                }

                //Uzyskanie pola zawierajacego nazwe kategorii SyndCategoryImpl.name
                String category ="Empty";
                if(!(entry.getCategories().isEmpty())){
                    category = entry.getCategories().get(0).getName();
                }

                //Data w formacie yyyy-MM-dd
                String publicationDate = "Empty";
                if (!(entry.getPublishedDate().toString().isEmpty())) {
                    publicationDate = RssFilter.filterDate(entry.getPublishedDate());
                }

                //Uzyskanie linku do artykułu
                String link = "Empty";
                if (!(entry.getLink()).isEmpty()){
                    link= entry.getLink();
                }

                //Po przefiltrowaniu mozemy bezpiecznie dodac obiekt do kolekcji
                RssFeed rssFeed = new RssFeed(title, description, publicationDate, category,link);
                if (!feeds.contains(rssFeed)) feeds.add(rssFeed);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        isRight=true;
        return new RssDto(feeds);
    }

    @Override
    public boolean isRight() {
        return isRight;
    }
}
