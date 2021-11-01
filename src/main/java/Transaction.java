import java.util.Date;

public class Transaction {
    private String id;
    private Movie movie;
    private String movieTime;
    private String seat;
    private int numOfSeats;
    private String cancelReason;
    private Date cancelDate = null;

    public Transaction(String id, Movie movie, String movieTime, String seat, int numOfSeats, String cancelReason) {
        this.id = id;
        this.movie = movie;
        this.movieTime = movieTime;
        this.seat = seat;
        this.numOfSeats = numOfSeats;
        this.cancelReason = cancelReason;
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getMovieName() {
        return this.movie.getTitle();
    }

    public String getMovieTime() {
        return movieTime;
    }

    public String getSeat() {
        return seat;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public String getTransactionInformation() {
        String ticket = String.format("Transaction ID: %s\n" +
                        "Movie: %s\n" +
                        "Showing time: %s\n" +
                        "Seat: %s\n" +
                        "Number of seats: %s\n", id, movie.getName(),
                movieTime, seat, numOfSeats);
        return ticket;
    }

    public void setCancelReason(String reason) {
        cancelReason = reason;
    }

    public void setCancelDate(Date date) {
        cancelDate = date;
    }

    public Date getCancelDate() {
        return cancelDate;
    }
}
