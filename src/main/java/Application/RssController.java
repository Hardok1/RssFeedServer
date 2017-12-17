package Application;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.net.URL;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/")
public class RssController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RssController.class);

    @RequestMapping(value = "rssINTERIA", method = RequestMethod.GET)
    public ResponseEntity<RssDto> retrieveRSS() {
        LOGGER.info("### Otrzymano zadanie pobrania kanalu RSS");

        ArrayList<RssFeed> feeds = new ArrayList<>(); //Kolekcja ktora bedzie przechowywac obiekty klasy RssFeed
        SyndFeedInput input = new SyndFeedInput();
        try {
            URL feedURL = new URL("http://fakty.interia.pl/swiat/feed"); //Adres kanalu RSS
            SyndFeed feed = input.build(new XmlReader(feedURL));    //Tutaj przechowujemy caly feed kanalu
            if (RssFilter.isFeedEmpty(feed.getEntries().get(0).getTitle())){
                LOGGER.info("### Nie znaleziono artykulow na kanale RSS !");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            //Petla for-each przechodzimy po kolejnych pozycjach
            for (SyndEntry entry: feed.getEntries()){

                //Wydzielanie opisu/ pozbywanie sie linkow/obrazkow
                String description = RssFilter.filterDescription(entry.getDescription().getValue());

                //Uzyskanie pola zawierajacego nazwe kategorii SyndCategoryImpl.name
                String category = entry.getCategories().get(0).getName();

                //Data w formacie yyyy-MM-dd
                String publicationDate = RssFilter.filterDate(entry.getPublishedDate());

                //Po przefiltrowaniu mozemy bezpiecznie dodac obiekt do kolekcji
                feeds.add(new RssFeed(entry.getTitle(), description, publicationDate, category));
            }

            LOGGER.info("### Poprawnie wczytano wszystkie pozycje");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final RssDto rssDto = new RssDto(feeds);
        return new ResponseEntity<>(rssDto,HttpStatus.OK);
    }
}