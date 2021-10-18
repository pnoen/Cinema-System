import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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

    /**
     * In charge of creating the proper movie objects
     * helper method
     */
    public Movie createMovie(String[] details){
        Date release_date = null;
        List<String> upcoming_times = null;

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            release_date = sdf.parse(details[3]);

            upcoming_times = Arrays.asList(details[6].split(","));
        }
        catch(Exception e){

        }

        Movie movie = new Movie(details[0], details[1], details[2], release_date, details[4], details[5], upcoming_times, details[7]);
        return movie;
    }

    /**
     * In charge of reading/parsing movie csv into list of movies
     * @return List of movie objects
     */
    public List<Movie> getMovies() {

        Scanner sc = null;
        try {
            sc = new Scanner(new File("src/main/resources/movies.csv"));
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Could not load the database.");
            System.exit(0);
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] details = line.split(";");

            Movie movie = createMovie(details);
            this.movies.add(movie);
        }
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

    /**
     * In charge of taking a list of movies and displaying
     * their information properly
     */
    public void displayMovies() {
        List<Movie> movies = getMovies();
        for (Movie movie: movies){
            System.out.println(movie.getMovieInformation());
        }

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

            //this is to test the view movie functionality
            System.out.println("enter 1 to see movie details");
            int entered = 0;
            if (userInput.hasNextInt()) {
                entered = userInput.nextInt();
            }
            if (entered == 1){
                displayMovies();
            }

            running = false;
        }

        userInput.close();
    }





    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.run();
    }

}
