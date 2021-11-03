import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public int numOfSeats() { return (this.seats.get(0).get(0) + this.seats.get(0).get(1) + this.seats.get(0).get(2)); }

    public List<String> getUpcomingTimes() {
        return upcomingTimes;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public List<String> getCinemaRooms() {
        return cinemaRooms;
    }

    public String truncateString(String sentence, int num){
        if(sentence.length() <= num){
            return sentence;
        }
        return sentence.substring(0,num) + "...";
    }
    public String truncateDate(Date date, int num){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        if(strDate.length() <= num){
            return strDate;
        }
        return strDate.substring(0,num);
    }
    public String getLongSingleInfo(){
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
        /**
         String info = String.format("%s\n" +
         "Classification: %s\n" +
         "Synopsis: %s\n" +
         "Release date: %s\n" +
         "Director: %s\n" +
         "Cast: %s\n" +
         "Screen size: %s\n" +
         "Upcoming times: %s\n" +
         "Cinema rooms: %s\n" +
         "Available seats: %s\n", this.name, this.classification, truncateString(this.synopsis, 30),
         this.releaseDate, this.director, this.cast, this.screenSize, upcomingTimes, cinemaRooms,
         availableSeats);
         */
        String info = String.format("%-25s" +
                "%-8s" +
                "%-20s" +
                "%-15s" +
                "%-20s" +
                "%-20s" +
                "%-15s" +
                "%-20s" +
                "%-15s\n",truncateString(this.name,20), this.classification, truncateString(this.synopsis, 15), truncateDate(this.releaseDate,10), truncateString(this.director,15), truncateString(this.cast,15), this.screenSize, truncateString(upcomingTimes,13), cinemaRooms);



        return info;
    }

    /**
     * Used to get condensed info
     * @return movie title, upcoming times
     */
    public String getCondensedMovieInformation(){
        String upcomingTimes = String.join(", ", this.upcomingTimes);
        String info = String.format(
                "%-25s" +
                "%-25s\n", truncateString(this.name,15), upcomingTimes);
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
        if (numSeats <= 0) {
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

    public void setName(String title){
        this.name = title;
    }

    public void setSynopsis(String synopsis){
        this.synopsis = synopsis;
    }

    public void setClassification(String classification){
        this.classification = classification;
    }

    public void setReleaseDate(String releaseDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        this.releaseDate = sdf.parse(releaseDate);
    }

    public void setSeatsModify(String frontSeats, String middleSeats, String rearSeats){
        List<List<Integer>> allSeats = new ArrayList<List<Integer>>();

        List<Integer> seats = new ArrayList<Integer>();
        seats.add(Integer.valueOf(frontSeats));
        seats.add(Integer.valueOf(middleSeats));
        seats.add(Integer.valueOf(rearSeats));
        for (int i = 0; i < this.upcomingTimes.size(); i++) {
            allSeats.add(seats);
        }
        this.seats = allSeats;

    }

    public void setDirector(String director){
        this.director = director;
    }

    public void setCast(String cast){
        this.cast = cast;
    }

    public void setUpcomingTimes(String upcomingTimes){
        this.upcomingTimes = Arrays.asList(upcomingTimes.split(","));
    }

    public void setScreenSize(String screenSize){
        this.screenSize = screenSize;
    }

    public void setCinemaRooms(String cinemaRooms){
        this.cinemaRooms = Arrays.asList(cinemaRooms.split(","));
    }

}
