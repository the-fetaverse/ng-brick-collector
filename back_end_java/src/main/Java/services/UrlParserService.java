package services;

public class UrlParserService {
    public Integer extractIdFromURL(String url) {
        String[] splitString = url.split("/");
        return Integer.parseInt(splitString[1]);
    }
}
