public class Transaction {
    private String id;
    private Movie movie;
    private String movieTime;
    private String seat;
    private int numOfSeats;

    public Transaction(String id, Movie movie, String movieTime, String seat, int numOfSeats) {
        this.id = id;
        this.movie = movie;
        this.movieTime = movieTime;
        this.seat = seat;
        this.numOfSeats = numOfSeats;
    }

    public String getId() {
        return id;
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
}
