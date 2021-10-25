public class Transaction {
    private String id;
    private Movie movie;
    private String movieTime;
    private String seat;

    public Transaction(String id, Movie movie, String movieTime, String seat) {
        this.id = id;
        this.movie = movie;
        this.movieTime = movieTime;
        this.seat = seat;
    }

    public String getId() {
        return id;
    }

    public String getTransactionInformation() {
        String ticket = String.format("Transaction ID: %s\n" +
                        "Movie: %s\n" +
                        "Showing time: %s\n" +
                        "Seat: %s\n", id, movie.getName(),
                movieTime, seat);
        return ticket;
    }
}
