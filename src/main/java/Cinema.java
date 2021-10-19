import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.String;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Cinema {
    private List<Movie> movies;
    private List<String> screenSizes;
    private List<Account> accounts;
    private boolean loggedIn = false;
    private File customers = new File("src/main/resources/customers.csv");

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
            password = PasswordMasker.readPassword("Password: ");
            String againPassword = PasswordMasker.readPassword("Confirm password: ");

            try {
                BufferedReader customersReader = new BufferedReader(new FileReader(this.customers));
                String line;
                Boolean unique = true;
                while ((line = customersReader.readLine()) != null) {
                    String[] ls = line.split(",");
                    if (ls[0].equals(username)) {
                        System.out.println("\nThis username is already taken. Please try again.\n");
                        unique = false;
                        break;
                    }
                }
                if (!unique) {
                    continue;
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("Error: couldn't update customers.csv");
                break;
            }
            catch (IOException e) {
                System.out.println("Error: couldn't update customers.csv");
                break;
            }

            if (!password.equals(againPassword)) {
                System.out.println("\nPasswords do not match. Please try again.\n");
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
            System.out.println("You will return to the main menu and you may log in as a customer.\n");
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
        getMovies();

        Scanner userInput = new Scanner(System.in);
        while (running) {

            //this is to test the view movie functionality
            System.out.println("Select the page you would like to visit:\n" +
                    "  1. All movies\n" +
                    "  2. Filter movies\n" +
                    "  3. Register as Fancy Cinemas Customer\n" +
                    "  4. Exit\n");
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
            else if (entered == 3) {
                registerCustomer();
            }
            else if (entered == 4) { // CHANGE THE NUMBER OF THIS WHEN MORE OPTIONS ARE ADDED
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
