import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.String;

public class Cinema {
    private List<Movie> movies;
    private List<Movie> displayMovies;
    private List<String> screenSizes;
    private List<Account> accounts;
    private boolean loggedIn = false;
    private File customers = new File("src/main/resources/customers.csv");

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
        //skipping formatting line
        sc.nextLine();
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
            System.out.println(" ");
        }

    }

    public void filterMovies() {

    }

    /**
     * Displays a customer registration prompt
     * Allows the user to register as a customer
     * Stores the user's customer details into a local database
     */
    public void registerCustomer() {
        String username = "";
        String password = "";
        while (true) { // The while loop ensures continual prompt in the case passwords do not match
            System.out.println("Please enter a username and password to register as a new Fancy Cinemas customer.");
            System.out.print("Username: ");
            Scanner name = new Scanner(System.in);
            username = name.nextLine();
            System.out.print("Password: ");
            Scanner pass = new Scanner(System.in);
            password = pass.nextLine();
            System.out.print("Confirm password: ");
            Scanner reEnterPassword = new Scanner(System.in);
            String confirm_password = reEnterPassword.nextLine();

            if (!password.equals(confirm_password)) {
                System.out.println("Passwords do not match. Please try again.\n");
                continue;
            } else {
                break;
            }
        }

        // Opens the local customer database to store customer's details
        try {
            FileWriter csvWriter = new FileWriter(this.customers, true);
            BufferedWriter bw = new BufferedWriter(csvWriter);
            bw.write(String.format("%s,%s\n", username, password));
            bw.close();
            System.out.println("\nYou have successfully registered as a new customer. Welcome to Fancy Cinemas!");
            System.out.println("Return to the login screen to log in as a customer.");
        }
        catch (IOException e) {
            System.out.println("Error: couldn't update customers.csv");
        }
    }

    public void loginUser() {

    }

    // MAIN LOOP WILL BE HERE RATHER THAN IN MAIN.
    // DATABASE FILES WILL BE LOCATED IN 'src/main/resources'.
    // movies.csv IS SEMICOLON DELIMITED.
    public void run() {
        boolean running = true;

        Scanner userInput = new Scanner(System.in);
        while (running) {

            //this is to test the view movie functionality
            System.out.println("Enter 1 to see movie details");
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
