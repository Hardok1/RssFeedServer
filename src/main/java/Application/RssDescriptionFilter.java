package Application;

public class RssDescriptionFilter {

    public static String filtr(String line){
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
}
