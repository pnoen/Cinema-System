import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Cinema {
    private List<Movie> movies;
    private List<Movie> displayMovies;
    private List<String> screenSizes;
    private List<Account> accounts;
    private boolean loggedIn = false;

    public Cinema() {
        this.movies = new ArrayList<Movie>();
        this.displayMovies = new ArrayList<Movie>();
        this.screenSizes = Arrays.asList("Bronze", "Silver", "Gold");
        this.accounts = new ArrayList<Account>();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Movie> getDisplayMovies() {
        return displayMovies;
    }

    public List<String> getScreenSizes() {
        return screenSizes;
    }

    // FEEL FREE TO CHANGE THE RETURN TYPE IF NECESSARY OR REMOVE THE METHOD
    public void readCSV() {

    }

    public void displayMovies() {

    }

    public void filterMovies() {

    }

    public void registerUser() {

    }

    public void loginUser() {

    }

    // MAIN LOOP WILL BE HERE RATHER THAN IN MAIN.
    // DATABASE FILES WILL BE LOCATED IN 'src/main/resources'
    // movies.csv IS SEMICOLON DELIMITED
    public void run() {
        boolean running = true;

        Scanner userInput = new Scanner(System.in);
        while (running) {

            running = false;
        }

        userInput.close();
    }





    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.run();
    }

}
