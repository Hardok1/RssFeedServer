package Application.domain.dto;

import Application.Application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RssFilter {

    //Metoda sluzy oddzielaniu zbednego kodu xml'a od opisu
    public static String filterDescription(String line){
        String description;     //Do przechowywania opisu przefiltrowanego
        if (line.contains("</a>")) {
            int firstPosition = line.indexOf("</a>");
            description = line.substring(firstPosition);
            description = description.replace("</a>", "");
            int lastPosition = description.lastIndexOf("</p>");
            description = description.substring(0, lastPosition);
        }   //W przypadku gdyby wpis nie zawieral znacznika "</a>"
        else if (line.contains("<p>")) {
            int firstPosition = line.indexOf("<p>");
            description = line.substring(firstPosition);
            description = description.replace("<p>", "");
            int lastPosition = description.lastIndexOf("</p>");
            description = description.substring(0, lastPosition);
        } else {
            description = "Nie powiodlo sie poprawne zaladowanie opisu";
        }

        return description;
    }

    //Metoda sluzy do zmiany formatu daty
    public static String filterDate(Date pubDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(pubDate);
    }
}
