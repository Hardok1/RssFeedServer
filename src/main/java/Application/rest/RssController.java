package Application.rest;

import Application.domain.dto.RssDto;
import Application.service.RssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


@Controller
@RequestMapping(value = "/rss/")
public class RssController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RssController.class);

    //URL Dla serwisow

    // Interia
    private static final String INTERIA_FAKTY = "http://fakty.interia.pl/feed";
    private static final String INTERIA_SWIAT =  "http://fakty.interia.pl/swiat/feed";
    private static final String INTERIA_POLSKA = "http://fakty.interia.pl/polska/feed";
    private static final String INTERIA_NAUKA = "http://fakty.interia.pl/nauka/feed";
    private static final String INTERIA_EUROSPORT ="http://eurosport.interia.pl/feed";
    private static final String INTERIA_EKSTRAKLASA = "https://eurosport.interia.pl/pilka-nozna/ekstraklasa/feed";
    private static final String INTERIA_NOWE_TECHNOLOGIE = "http://nt.interia.pl/feed";

    //RMF.FM
    private static final String RMF_FAKTY = "http://www.rmf24.pl/feed";
    private static final String RMF_POLSKA = "http://www.rmf24.pl/fakty/polska/feed";
    private static final String RMF_SWIAT = "http://www.rmf24.pl/fakty/swiat/feed";
    private static final String RMF_KULTURA = "http://www.rmf24.pl/kultura/feed";
    private static final String RMF_EKONOMIA ="http://www.rmf24.pl/ekonomia/feed";
    private static final String RMF_SPORT = "http://www.rmf24.pl/sport/feed";
    private static final String RMF_NAUKA = "http://www.rmf24.pl/nauka/feed";

    //Polsat News
    private static final String POLSAT_WSZYSTKIE = "http://www.polsatnews.pl/rss/wszystkie.xml";
    private static final String POLSAT_KRAJ = "http://www.polsatnews.pl/rss/kraj.xml";
    private static final String POLSAT_SWIAT = "http://www.polsatnews.pl/rss/swiat.xml";
    private static final String POLSAT_BIZNES = "http://www.polsatnews.pl/rss/biznes.xml";
    private static final String POLSAT_SPORT = "http://www.polsatnews.pl/rss/sport.xml";
    private static final String POLSAT_TECHNOLOGIE_I_MEDYCYNA ="http://www.polsatnews.pl/rss/technologie.xml";
    private static final String POLSAT_ROZRYWKA = "http://www.polsatnews.pl/rss/rozrywka.xml";

    //TVN24
    private static final String TVN24_NAJNOWSZE = "https://www.tvn24.pl/najnowsze.xml";
    private static final String TVN24_NAJWAZNIEJSZE = "https://www.tvn24.pl/najwazniejsze.xml";
    private static final String TVN24_SPORT = "https://sport.tvn24.pl/sport,81,m.xml";
    private static final String TVN24_POLSKA =  "https://www.tvn24.pl/wiadomosci-z-kraju,3.xml";
    private static final String TVN24_SWIAT = "https://www.tvn24.pl/wiadomosci-ze-swiata,2.xml";
    private static final String TVN24_BIZNES= "https://www.tvn24.pl/biznes-gospodarka,6.xml";
    private static final String TVN24_TECH = "https://www.tvn24.pl/internet-hi-tech-media,40.xml";


    private final RssService rssService;

    @Autowired
    public RssController(RssService rssService) {
        this.rssService = rssService;
    }

    @RequestMapping(value = "bySite/{site}", method = RequestMethod.GET)
    public ResponseEntity<RssDto> retrieveRSS(@PathVariable("site") String site) {

        String url;
        LOGGER.info("### Otrzymano zadanie pobrania kanalu RSS dla {}",site);

        switch (site){
            case "interia_fakty":
                url = INTERIA_FAKTY;
                break;
            case "interia_swiat":
                url = INTERIA_SWIAT;
                break;
            case "interia_polska":
                url = INTERIA_POLSKA;
                break;
            case "interia_nauka":
                url = INTERIA_NAUKA;
                break;
            case "interia_eurosport":
                url = INTERIA_EUROSPORT;
                break;
            case "interia_ekstraklasa":
                url = INTERIA_EKSTRAKLASA;
                break;
            case "interia_nowe_technologie":
                url = INTERIA_NOWE_TECHNOLOGIE;
                break;
            case "rmf_fakty":
                url = RMF_FAKTY;
                break;
            case "rmf_polska":
                url= RMF_POLSKA;
                break;
            case "rmf_swiat":
                url = RMF_SWIAT;
                break;
            case "rmf_kultura":
                url = RMF_KULTURA;
                break;
            case "rmf_ekonomia":
                url = RMF_EKONOMIA;
                break;
            case "rmf_sport":
                url = RMF_SPORT;
                break;
            case "rmf_nauka":
                url = RMF_NAUKA;
                break;
            case "polsat_wszystkie":
                url = POLSAT_WSZYSTKIE;
                break;
            case "polsat_kraj":
                url = POLSAT_KRAJ;
                break;
            case "polsat_swiat":
                url = POLSAT_SWIAT;
                break;
            case "polsat_biznes":
                url = POLSAT_BIZNES;
                break;
            case "polsat_sport":
                url = POLSAT_SPORT;
                break;
            case "polsat_technologie_i_medycyna":
                url = POLSAT_TECHNOLOGIE_I_MEDYCYNA;
                break;
            case "polsat_rozrywka":
                url = POLSAT_ROZRYWKA;
                break;
            case "tvn24_najnowsze":
                url = TVN24_NAJNOWSZE;
                break;
            case "tvn24_najwazniejsze":
                url = TVN24_NAJWAZNIEJSZE;
                break;
            case "tvn24_sport":
                url = TVN24_SPORT;
                break;
            case "tvn24_polska":
                url = TVN24_POLSKA;
                break;
            case "tvn24_swiat":
                url = TVN24_SWIAT;
                break;
            case "tvn24_biznes":
                url = TVN24_BIZNES;
                break;
            case "tvn24_tech":
                url = TVN24_TECH;
                break;


            default:
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }


        final RssDto rssDto = rssService.getRssFeed(url);

        if (rssService.isRight()) {
            LOGGER.info("### Poprawnie wczytano wszystkie pozycje");
            return new ResponseEntity<>(rssDto, HttpStatus.OK);
        } else {
            LOGGER.info("### Nie znaleziono artykulow na kanale RSS !");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}