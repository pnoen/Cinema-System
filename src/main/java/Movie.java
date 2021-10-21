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
    private List<String> cinemaRooms;

    public Movie(String name, String synopsis, String classification, Date releaseDate, String director,
                 String cast, List<String> upcomingTimes, String screenSize, List<String> cinemaRooms) {
        this.name = name;
        this.synopsis = synopsis;
        this.classification = classification;
        this.releaseDate = releaseDate;
        this.director = director;
        this.cast = cast;
        this.upcomingTimes = upcomingTimes;
        this.screenSize = screenSize;
        this.cinemaRooms = cinemaRooms;
    }

//    public String getName() {
//        return name;
//    }
//
//    public String getSynopsis() {
//        return synopsis;
//    }
//
//    public String getClassification() {
//        return classification;
//    }
//
//    public Date getReleaseDate() {
//        return releaseDate;
//    }
//
//    public String getDirector() {
//        return director;
//    }
//
//    public String getCast() {
//        return cast;
//    }
//
//    public List<String> getUpcomingTimes() {
//        return upcomingTimes;
//    }

    public String getScreenSize() {
        return screenSize;
    }

    public List<String> getCinemaRooms() {
        return cinemaRooms;
    }

    public String getMovieInformation(){
        String upcomingTimes = String.join(", ", this.upcomingTimes);
        String cinemaRooms = String.join(", ", this.cinemaRooms);

        String info = String.format("%s\n" +
                        "Classification: %s\n" +
                        "Synopsis: %s\n" +
                        "Release date: %s\n" +
                        "Director: %s\n" +
                        "Cast: %s\n" +
                        "Screen size: %s\n" +
                        "Upcoming times: %s\n" +
                        "Cinema rooms: %s\n", this.name, this.classification, this.synopsis,
                this.releaseDate, this.director, this.cast, this.screenSize, upcomingTimes, cinemaRooms);
        return info;
    }

}
