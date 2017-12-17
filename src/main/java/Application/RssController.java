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
        LOGGER.info("### Otrzymano zadanie pobrania kanalu RSS !");

        ArrayList<RssFeed> feeds = new ArrayList<>(); //Kolekcja ktora bedzie przechowywac obiekty klasy RssFeed
        SyndFeedInput input = new SyndFeedInput();
        try {
            URL feedURL = new URL("http://fakty.interia.pl/swiat/feed"); //Adres kanalu RSS
            SyndFeed feed = input.build(new XmlReader(feedURL));    //Tutaj przechowujemy caly feed kanalu

            //Petla for-each przechodzimy po kolejnych pozycjach
            for (SyndEntry entry: feed.getEntries()){

                //Wydzielanie opisu/ pozbywanie sie linkow/obrazkow
                String line = entry.getDescription().getValue();
                String description;    //Do przechowywania opisu przefiltrowanego
                if (line.contains("</a>")) {
                    int firstPosition = line.indexOf("</a>");
                    description = line.substring(firstPosition);
                    description = description.replace("</a>", "");
                    int lastPosition = description.lastIndexOf("</p>");
                    description = description.substring(0, lastPosition);
                } //W przypadku gdyby wpis nie zawieral znacznika "</a>"
                else if (line.contains("<p>")) {
                    int firstPosition = line.indexOf("<p>");
                    description = line.substring(firstPosition);
                    description = description.replace("<p>", "");
                    int lastPosition = description.lastIndexOf("</p>");
                    description = description.substring(0, lastPosition);
                } else {
                    description = "Nie powiodlo sie poprawne zaladowanie opisu";
                }

                //Po przefiltrowaniu mozemy bezpiecznie dodac obiekt do kolekcji
                feeds.add(new RssFeed(entry.getTitle(), description, entry.getPublishedDate().toString(), entry.getCategories().toString()));
            }

            LOGGER.info("### Poprawnie wczytano wszystkie pozycje");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Testy
        System.out.println("Teraz... Dane...");
        System.out.println("Data publikacji: " + feeds.get(0).getPublicationDate());
        //System.out.println("Kategoria : " + feeds.get(2).getCategory());
        System.out.println("Tytul: " + feeds.get(0).getTitle());
        System.out.println("Opis: " + feeds.get(0).getDescription());
        final RssDto rssDto = new RssDto(feeds);


        return new ResponseEntity<>(rssDto,HttpStatus.OK);
    }

}
