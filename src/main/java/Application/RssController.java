package Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/")
public class RssController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RssController.class);

    @RequestMapping(value = "rssINTERIA", method = RequestMethod.GET)
    public ResponseEntity<DocumentDto> retrieveRSS() {
        LOGGER.info("### Otrzymano zadanie pobrania kanalu RSS !");








        //final DocumentDto documentDto = new DocumentDto(result);
        return new ResponseEntity<>(/*documentDto,*/HttpStatus.OK);
    }

}
