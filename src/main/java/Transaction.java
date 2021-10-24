public class Transaction {
    private int id;
    private Movie movie;
    private String movieTime;

    public Transaction(int id, Movie movie, String movieTime) {
        this.id = id;
        this.movie = movie;
        this.movieTime = movieTime;
    }
}
