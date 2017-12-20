package Application;

import Application.service.RssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/")
public class RssController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RssController.class);

    @Autowired
    private RssService rssService;

    @RequestMapping(value = "rssINTERIA", method = RequestMethod.GET)
    public ResponseEntity<RssDto> retrieveRSS() {
        LOGGER.info("### Otrzymano zadanie pobrania kanalu RSS");

        final RssDto rssDto = rssService.getRssFeed();

        if (rssService.isRight()) {
            LOGGER.info("### Poprawnie wczytano wszystkie pozycje");
            return new ResponseEntity<>(rssDto, HttpStatus.OK);
        } else {
            LOGGER.info("### Nie znaleziono artykulow na kanale RSS !");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}