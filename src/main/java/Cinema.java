import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Cinema {
    private List<Movie> movies;
    private List<String> screenSizes;
    private List<Account> accounts;
    private boolean loggedIn = false;

    public Cinema() {
        this.movies = new ArrayList<Movie>();
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
//        List<Movie> movies = getMovies();
        for (Movie movie: movies){
            System.out.println(movie.getMovieInformation());
//            System.out.println();
        }

    }

    public void filterMovies() {
        Scanner userInput = new Scanner(System.in);
        boolean filterComplete = false;
        while (!filterComplete) {
            System.out.println("Select the options that you would like to filter.\n" +
                    "(To select multiple options, split by comma. E.g. 1,2)\n" +
                    "Movie Screen Size:\n" +
                    "  1. Bronze\n" +
                    "  2. Silver\n" +
                    "  3. Gold\n"
            );
            String selections = userInput.nextLine();

            // Split the selection input and convert to int
            List<Integer> filters = new ArrayList<Integer>();
            boolean filterInvalid = false;
            for (String s : selections.split(",")) {
                try {
                    filters.add(Integer.parseInt(s.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid option entered.\n");
                    filterInvalid = true;
                    break;
                }
            }
            if (filterInvalid) {
                continue;
            }

            List<String> screenSizeFilters = new ArrayList<String>();

            // Check if all the selected options are valid
            for (int filter : filters) {
                if (filter >= 1 && filter <= 3) {
                    screenSizeFilters.add(this.screenSizes.get(filter - 1));
                }
                else {
                    System.out.println("Error: Invalid option selected.\n");
                    filterInvalid = true;
                    break;
                }
            }
            if (filterInvalid) {
                continue;
            }

            if (screenSizeFilters.size() == 0) {
                screenSizeFilters = this.screenSizes;
            }

            List<Movie> displayMovies = new ArrayList<Movie>();
            for (Movie movie : this.movies) {
                if (screenSizeFilters.contains(movie.getScreenSize()) && !displayMovies.contains(movie)) {
                    displayMovies.add(movie);
                }
            }

            for (Movie movie : displayMovies) {
                System.out.println(movie.getMovieInformation());
            }
            filterComplete = true;
        }
    }

    public void registerUser() {

    }

    public void loginUser() {

    }

    // MAIN LOOP WILL BE HERE RATHER THAN IN MAIN.
    // DATABASE FILES WILL BE LOCATED IN 'src/main/resources'.
    // movies.csv IS SEMICOLON DELIMITED.
    public void run() {
        boolean running = true;
        getMovies();

        Scanner userInput = new Scanner(System.in);
        while (running) {

            //this is to test the view movie functionality
            System.out.println("Select the page you would like to visit:\n" +
                    "  1. All movies\n" +
                    "  2. Filter movies\n" +
                    "  3. Exit\n");

            int entered = 0;
            if (userInput.hasNextInt()) {
                entered = userInput.nextInt();
            } else {
                System.out.println("Error: Not a valid option.\n");
                userInput.nextLine();
                continue;
            }

            if (entered == 1){
                displayMovies();
            }
            else if (entered == 2) {
                filterMovies();
            }
            else if (entered == 3) { // CHANGE THE NUMBER OF THIS WHEN MORE OPTIONS ARE ADDED
                running = false;
            }
            else {
                System.out.println("Error: Not a valid option.\n");
                continue;
            }
        }

        userInput.close();
    }





    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.run();
    }

}
