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
    private List<List<Integer>> seats; // [[front, middle, rear], [front, middle, rear]]

    public Movie(String name, String synopsis, String classification, Date releaseDate, String director,
                 String cast, List<String> upcomingTimes, String screenSize, List<String> cinemaRooms,
                 List<List<Integer>> seats) {
        this.name = name;
        this.synopsis = synopsis;
        this.classification = classification;
        this.releaseDate = releaseDate;
        this.director = director;
        this.cast = cast;
        this.upcomingTimes = upcomingTimes;
        this.screenSize = screenSize;
        this.cinemaRooms = cinemaRooms;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }
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
    public List<String> getUpcomingTimes() {
        return upcomingTimes;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public List<String> getCinemaRooms() {
        return cinemaRooms;
    }

    public String getMovieInformation(){
        String upcomingTimes = String.join(", ", this.upcomingTimes);
        String cinemaRooms = String.join(", ", this.cinemaRooms);

        List<Integer> sumOfSeats = new ArrayList<Integer>();
        for (List<Integer> movieSeats : seats) {
            int sum = 0;
            sum += movieSeats.get(0);
            sum += movieSeats.get(1);
            sum += movieSeats.get(2);
            sumOfSeats.add(sum);
        }

        String availableSeats = "";
        for (int i = 0; i < sumOfSeats.size(); i++) {
            availableSeats += sumOfSeats.get(i);
            if (i != sumOfSeats.size() - 1) {
                availableSeats += ", ";
            }
        }

        String info = String.format("%s\n" +
                        "Classification: %s\n" +
                        "Synopsis: %s\n" +
                        "Release date: %s\n" +
                        "Director: %s\n" +
                        "Cast: %s\n" +
                        "Screen size: %s\n" +
                        "Upcoming times: %s\n" +
                        "Cinema rooms: %s\n" +
                        "Available seats: %s\n", this.name, this.classification, this.synopsis,
                this.releaseDate, this.director, this.cast, this.screenSize, upcomingTimes, cinemaRooms,
                availableSeats);
        return info;
    }

    /**
     * Used to get condensed info
     * @return movie title, upcoming times
     */
    public String getCondensedMovieInformation(){
        String upcomingTimes = String.join(", ", this.upcomingTimes);
        String info = String.format("Title: %s\n" + "Upcoming Times: %s\n", this.name, upcomingTimes);
        return info;
    }

    // need to do error checking to make sure formatting given in is correct
    public void addTime(String time){
        this.upcomingTimes.add(time);
    }

    public String getTitle() {
        return this.name;
    }


    public List<Integer> getSeats(int upcomingTimeIdx) {
        return seats.get(upcomingTimeIdx);
    }

    public boolean setSeats(int upcomingTimeIdx, int seat, int numOfBookingSeats) {
        int numSeats = seats.get(upcomingTimeIdx).get(seat);
        if (numSeats < 0) {
            System.out.println("There are no available seats in this row.\n");
            return false;
        }
        else if (numSeats < numOfBookingSeats) {
            System.out.println("There aren't enough seats in this row.\n");
            return false;
        }
        seats.get(upcomingTimeIdx).set(seat, numSeats - numOfBookingSeats);
        return true;
    }

    public void addSeats(String upcomingTime, int seat, int numOfSeats) {
        int ind = upcomingTimes.indexOf(upcomingTime);
        int currSeats = seats.get(ind).get(seat);
        currSeats += numOfSeats;
        seats.get(ind).set(seat, currSeats);
    }

}
