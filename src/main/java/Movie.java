import java.util.*;

public class Movie {
    private String name;
    private String synopsis;
    private String classification;
    private Date releaseDate;
    private String director;
    private String cast;
    private List<String> upcomingTimes;
    private String screenSize;

    public Movie(String name, String synopsis, String classification, Date releaseDate, String director, String cast, List<String> upcomingTimes, String screenSize) {
        this.name = name;
        this.synopsis = synopsis;
        this.classification = classification;
        this.releaseDate = releaseDate;
        this.director = director;
        this.cast = cast;
        this.upcomingTimes = upcomingTimes;
        this.screenSize = screenSize;
    }

    public String getName() {
        return name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getClassification() {
        return classification;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }

    public List<String> getUpcomingTimes() {
        return upcomingTimes;
    }

    public String getScreenSize() {
        return screenSize;
    }

}
