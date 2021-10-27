import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.String;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Cinema {
    private List<Movie> movies;
    private List<String> screenSizes;
    private List<Account> accounts;
    private List<GiftCard> giftCards;
    private boolean loggedIn = false;
    private File accountsFile = new File("src/main/resources/accounts.csv");
    private File moviesFile = new File("src/main/resources/movies.csv");
    private File giftCardFile = new File("src/main/resources/giftcards.csv");
    private String creditCardFile = "src/main/resources/credit_cards.json";
    private Account currAcc;
    private List<String> allCinemaRooms;
    private List<String> allRatings;
    private List<Transaction> transactions = new ArrayList<Transaction>();
    private JSONArray cards;
    private int checkPaymentVal = 5;

    public Cinema() {
        this.movies = new ArrayList<Movie>();
        this.screenSizes = Arrays.asList("Bronze", "Silver", "Gold");
        this.accounts = new ArrayList<Account>();
        this.allCinemaRooms = Arrays.asList("1", "2", "3");
        this.giftCards = new ArrayList<GiftCard>();
        this.allRatings = Arrays.asList("G", "PG", "M", "MA15+", "R18+");
    }

    /**
     * In charge of creating the proper movie objects
     * helper method
     */
    public Movie createMovie(String[] details){
        Date release_date = null;
        List<String> upcoming_times = null;
        List<String> cinema_rooms = null;
        List<List<Integer>> allSeats = new ArrayList<List<Integer>>();

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            release_date = sdf.parse(details[3]);

            upcoming_times = Arrays.asList(details[6].split(","));

            cinema_rooms = Arrays.asList(details[8].split(","));

            List<Integer> seats = new ArrayList<Integer>();
            seats.add(Integer.valueOf(details[9]));
            seats.add(Integer.valueOf(details[10]));
            seats.add(Integer.valueOf(details[11]));
            for (int i = 0; i < upcoming_times.size(); i++) {
                allSeats.add(seats);
            }
        }
        catch(Exception e){
            System.out.println("Error: Failed to load database");
            System.exit(0);
        }

        Movie movie = new Movie(details[0], details[1], details[2], release_date, details[4], details[5], upcoming_times, details[7], cinema_rooms, allSeats);
        return movie;
    }

    public Account createNewAccount(String username, String password, int perms){

        return new Account(username, password, perms);
    }

    public void setMovies(File movie_file){
        this.moviesFile = movie_file;
    }

    public void setAccountsFile(File accountsFile) {
        this.accountsFile = accountsFile;
    }

    public void setGiftCardFile(File giftCardFile) {
        this.giftCardFile = giftCardFile;
    }

    public void createGiftCards() throws FileNotFoundException {

        Scanner sc = null;
        try {
            sc = new Scanner(this.giftCardFile);
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error: Could not load the database.");
        }
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] details = line.split(",");
            GiftCard newCard = createNewGiftCard(details[0], Integer.parseInt(details[1]));
            this.giftCards.add(newCard);
        }

    }

    public void createAccounts() throws FileNotFoundException {

        Scanner sc = null;
        try {
            sc = new Scanner(this.accountsFile);
        }
        catch (FileNotFoundException e) {
//            System.out.println("Error: Could not load the database.");
//            System.exit(0);
            throw new FileNotFoundException("Error: Could not load the database.");
        }
        //skipping formatting line
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] details = line.split(",");

            Account account = createNewAccount(details[0], details[1], Integer.parseInt(details[2]));
            this.accounts.add(account);
        }

    }

    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * In charge of reading/parsing movie csv into list of movies
     * @return List of movie objects
     */
    public List<Movie> getMovies() {

        Scanner sc = null;
        try {
            sc = new Scanner(moviesFile);
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

//    public List<String> getScreenSizes() {
//        return screenSizes;
//    }

    // FEEL FREE TO CHANGE THE RETURN TYPE IF NECESSARY OR REMOVE THE METHOD
//    public void readCSV() {
//
//    }

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

    public boolean filterMovies(Scanner userInput) {
//        Scanner userInput = new Scanner(System.in);
//        boolean filterComplete = false;
//        while (!filterComplete) {
        String selections = userInput.nextLine();
        System.out.println("Select the options that you would like to filter.\n" +
                "(To select multiple options, split by comma. E.g. 1,2)\n" +
                "Movie Screen Sizes:\n" +
                "  1. Bronze\n" +
                "  2. Silver\n" +
                "  3. Gold\n" +
                "Cinema Rooms:\n" +
                "  4. Room 1\n" +
                "  5. Room 2\n" +
                "  6. Room 3\n" +
                "Movie Ratings:\n" +
                "  7. G\n" +
                "  8. PG\n" +
                "  9. M\n" +
                "  10. MA15+\n" +
                "  11. R18+\n"
        );
        selections = userInput.nextLine();

        // Split the selection input and convert to int
        List<Integer> filters = new ArrayList<Integer>();
//            boolean filterInvalid = false;
        for (String s : selections.split(",")) {
            try {
                filters.add(Integer.parseInt(s.trim()));
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid option entered.\n");
//                    filterInvalid = true;
                return false;
            }
        }
//            if (filterInvalid) {
//                continue;
//            }

        List<String> screenSizeFilters = new ArrayList<String>();
        List<String> cinemaRoomFilters = new ArrayList<String>();
        List<String> ratingFilters = new ArrayList<String>();

        // Check if all the selected options are valid
        for (int filter : filters) {
            if (filter >= 1 && filter <= 3) {
                screenSizeFilters.add(this.screenSizes.get(filter - 1));
            }
            else if (filter >= 4 && filter <= 6) {
                cinemaRoomFilters.add(this.allCinemaRooms.get(filter - 4));
            }
            else if (filter >= 7 && filter <= 11) {
                ratingFilters.add(this.allRatings.get(filter - 7));
            }
            else {
                System.out.println("Error: Invalid option selected.\n");
//                    filterInvalid = true;
                return false;
            }
        }
//            if (filterInvalid) {
//                continue;
//            }

        if (screenSizeFilters.size() == 0) {
            screenSizeFilters = this.screenSizes;
        }
        if (cinemaRoomFilters.size() == 0) {
            cinemaRoomFilters = this.allCinemaRooms;
        }
        if (ratingFilters.size() == 0) {
            ratingFilters = this.allRatings;
        }

        List<Movie> displayMovies = new ArrayList<Movie>();
        for (Movie movie : this.movies) {
            if (screenSizeFilters.contains(movie.getScreenSize()) && ratingFilters.contains(movie.getClassification())) {
                boolean contains = false;
                for (String room : movie.getCinemaRooms()) {
                    if (cinemaRoomFilters.contains(room)) {
                        contains = true;
                        break;
                    }
                }
                if (contains && !displayMovies.contains(movie)) {
                    displayMovies.add(movie);
                }
            }
        }

        for (Movie movie : displayMovies) {
            System.out.println(movie.getMovieInformation());
        }
//            filterComplete = true;
//        }
        return true;
    }

    /**
     * Displays a customer registration prompt
     * Allows the user to register as a customer
     * Stores the user's customer details into a local database
     */
    public void registerCustomer() {
        String username;
        String password;
        while (true) { // The while loop ensures continual prompt in the case passwords do not match
            System.out.println("Please enter a username and password to register as a new Fancy Cinemas customer.");
            System.out.print("Username: ");
            Scanner name = new Scanner(System.in);
            username = name.nextLine();
            password = PasswordMasker.readPassword("Password: ");
            String againPassword = PasswordMasker.readPassword("Confirm password: ");

            try {
                BufferedReader customersReader = new BufferedReader(new FileReader(this.accountsFile));
                String line;
                boolean unique = true;
                while ((line = customersReader.readLine()) != null) {
                    String[] ls = line.split(",");
                    if (ls[0].equals(username)) {
                        System.out.println("\nThis username is already taken. Please try again.\n");
                        unique = false;
                        break;
                    }
                    else if (username.matches(".*\\s.*")) {
                        System.out.println("\nUsername cannot contain spaces. Please try again.\n");
                        unique = false;
                        break;
                    }
                    else if (username.length() == 0) {
                        System.out.println("\nUsername cannot be empty. Please try again.\n");
                        unique = false;
                        break;
                    }
                }
                if (!unique) {
                    continue;
                }
            } catch (IOException e) {
                System.out.println("Error: couldn't update accounts.csv");
                break;
            }

            if (!password.equals(againPassword)) {
                System.out.println("\nPasswords do not match. Please try again.\n");
                continue;
            } else if (password.matches(".*\\s.*")) {
                System.out.println("\nPassword cannot contain spaces. Please try again.\n");
                continue;
            }
            else if (password.length() == 0) {
                System.out.println("\nPassword cannot be empty. Please try again.\n");
                continue;
            }
            else {
//                name.close();
                break;
            }
        }

        // Opens the local customer database to store customer's details
        try {
            FileWriter csvWriter = new FileWriter(this.accountsFile, true);
            BufferedWriter bw = new BufferedWriter(csvWriter);
            bw.write(String.format("%s,%s,%s\n", username, password, "0"));
            bw.close();
            System.out.println("\nYou have successfully registered as a new customer. Welcome to Fancy Cinemas!");
            System.out.println("You will return to the main menu logged into your new account.\n");
        }
        catch (IOException e) {
            System.out.println("Error: couldn't update accounts.csv");
        }

        Account acc = createNewAccount(username, password,0);
        this.accounts.add(acc);
        currAcc = acc;
        this.loggedIn = true;

    }

    public void loginUser() {
        Scanner userInput = new Scanner(System.in);
        while(!this.loggedIn){
            System.out.println("Please enter your valid username and password.");
            System.out.print("Username: ");
            String username = userInput.nextLine();
            for(Account a : this.accounts){
                if (username.equals(a.getUsername())){
                    String password = PasswordMasker.readPassword("Password: ");
                    if (password.equals(a.getPassword())){
                        System.out.println("Success");
                        currAcc = a;
                        this.loggedIn = true;
                    }
                    else{
                        System.out.println("Password entered is incorrect");
                    }
                }

            }
        }
//        userInput.close();



    }

//    /**
//     * In the login method, pass the username and password into the checkRole.
//     * @param username
//     * @param password
//     * @return
//     */
//    public String checkRole(String username, String password) {
//        String staffFileName = "src/main/resources/staff.csv";
//        File staffFile = new File(staffFileName);
//        try{
//            Scanner csvStaffInput = new Scanner(staffFile);
//            while(csvStaffInput.hasNext()){
//                String staffInfo = csvStaffInput.next();
//                String[] staffUserInfo = staffInfo.split(",");
//                if(Objects.equals(username, staffUserInfo[0])){
//                    if(Objects.equals(password, staffUserInfo[1])){
//                        return "S";
//                    }
//                }
//            }
//        } catch (FileNotFoundException e){
//            System.out.println("Error: could not find staff.csv");
//        }
//
//        String managerFileName = "src/main/resources/managers.csv";
//        File managerFile = new File(managerFileName);
//        try{
//            Scanner csvManagerInput = new Scanner(managerFile);
//            while(csvManagerInput.hasNext()){
//                String managerInfo = csvManagerInput.next();
//                String[] managerUserInfo = managerInfo.split(",");
//                if(Objects.equals(username, managerUserInfo[0])){
//                    if(Objects.equals(password, managerUserInfo[1])){
//                        return "M";
//                    }
//                }
//            }
//        } catch (FileNotFoundException e){
//            System.out.println("Error: could not find managers.csv");
//        }
//
//        return "Guest";
//
//
//    }

    public boolean getLogged(){
        return this.loggedIn;
    }

    public Account getCurrAcc() {
        return this.currAcc;
    }

    public void setLogged(boolean state){
        this.loggedIn = state;
    }

    public void customerLoginLogic(Scanner userInput){
        userInput.nextLine();
//        Scanner userInput = new Scanner(System.in);
        while(loggedIn){
            System.out.println("Select the page you would like to visit:\n" +
                    "  1. All movies\n" +
                    "  2. Filter movies\n" +
                    "  3. Book\n" +
                    "  4. Cancel a booking\n" +
                    "  5. Logout");

            int logged = 0;
            if (userInput.hasNextInt()) {
                logged = userInput.nextInt();
            }

            if (logged == 1){
                displayMovies();
            }
            else if (logged == 2) {
                filterMovies(userInput);
            }
            else if (logged == 3) {
                bookMovie(userInput);
            }
            else if (logged == 4) {
                cancelBooking(userInput);
            }
            else if (logged == 5) {
                this.loggedIn = false;
                System.out.println("You have logged out");
            }
            else {
                System.out.println("Error: Not a valid option.");
                userInput.nextLine();
            }

        }
//        userInput.close();
    }

    /**
     * Returns movie object based off title
     * @param title
     * @return
     */
    public Movie getMovie(String title){
        //need to do error checking
        Movie found_movie = null;
        for (Movie movie: movies){
            if (movie.getTitle() == title){
                found_movie = movie;
            }
        }
        return found_movie;
    }

    /***
     * Logic to deal with adding new shows
     * for cinema staff and manager
     */
    public void addNewShows() {

        Map<Integer, Movie> movie_ref = new HashMap<Integer, Movie>();

        //filling out hashmap with movies and references
        int i = 0;
        for (Movie movie : movies) {
            movie_ref.put(i, movie);
            i += 1;
        }

        Iterator iterator = movie_ref.entrySet().iterator();

        //printing out movies and references to the user
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            System.out.println("Reference Number: " + (pair.getKey()));
            Movie movie1 = (Movie) pair.getValue();
            System.out.println(movie1.getCondensedMovieInformation());
        }
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the reference number of the film you would like to add a show for\n");

        //going to have to add error checking to make sure
        //all input that is given is correct ofc

        int refnum = 100;
        if (userInput.hasNextInt()) {
            refnum = userInput.nextInt();
            System.out.println("Adding show for the following movie:\n");
            System.out.println(movie_ref.get(refnum).getCondensedMovieInformation());
        }

        Scanner userInput2 = new Scanner(System.in);
        System.out.println("Enter the show time you would like to add (In form 'hh:mm:'):\n");
        Boolean time_given = false;
        String time = "";

        if (userInput2.hasNext()) {
            time = userInput2.nextLine();
            System.out.println("Entering " + time + " into the system.");
            time_given = true;
        }

        String updated_times;

        //if we have given a time, can save it into the system
        if (time_given) {

            try {
                BufferedReader movieReader = new BufferedReader(new FileReader(this.moviesFile));
                StringBuffer stringBuffer = new StringBuffer();

                String line;

                //getting relevant line
                while ((line = movieReader.readLine()) != null) {

                    String[] ls = line.split(";");

                    if (ls[0].equals(movie_ref.get(refnum).getTitle())) {

                        //updating upcoming times
                        updated_times = ls[6] + "," + time;
                        ls[6] = updated_times;
                        String new_line = String.join(";", ls);
                        line = new_line;
                        System.out.println("\n" + movie_ref.get(refnum).getTitle());
                        System.out.println("Updated Movie Upcoming Times: \n");
                        System.out.println(updated_times + "\n");
                        System.out.println("Successfully updated. Update will be visible once system is restarted.\n");
                    }

                    stringBuffer.append(line);
                    stringBuffer.append('\n');

                }
                movieReader.close();

                FileOutputStream fileOut = new FileOutputStream(this.moviesFile);
                fileOut.write(stringBuffer.toString().getBytes());
                fileOut.close();

            } catch (IOException e) {
                System.out.println("Error: couldn't update accounts.csv");
            }

        }
    }

    public void setCurrAcc(Account account) {
        this.currAcc = account;
    }

    public void bookMovie(Scanner userInput) {
        // list all movie names
        // check if the input is in range of 0 to length of movies array
        // display upcoming time for movie selected
        // check if it is in range of the length of the movies upcoming time array
        // check number of seats
        // generate random transaction id
        // create the transaction
        // add to a list of transactions in cinema
        // add to a list of bookings in account

        userInput.nextLine();
        List<String> movieNames = new ArrayList<String>();
        for (Movie movie : movies) {
            movieNames.add(movie.getName());
        }

//        Scanner userInput = new Scanner(System.in);
        boolean bookedMovie = false;
        while (!bookedMovie) {
            System.out.println("Select a movie you would like to book:");
            for (int i = 0; i < movieNames.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + movieNames.get(i));
            }
            System.out.println("  " + (movieNames.size() + 1) + ". Back to home page");

            int entered = 0;
            if (userInput.hasNextInt()) {
                entered = userInput.nextInt();
            } else {
                System.out.println("Error: Not a valid option.\n");
                userInput.nextLine();
                continue;
            }

            if (entered > 0 && entered <= movieNames.size() + 1) {
                if (entered == movieNames.size() + 1) {
                    System.out.println("Returning back to customer home page.\n");
                    return;
                }

                pickBookingTime(userInput, entered - 1);
                bookedMovie = true;
            } else {
                System.out.println("Error: Not a valid option.\n");
                continue;
            }
        }
    }

    public void pickBookingTime(Scanner userInput, int movieIdx) {
        userInput.nextLine();

        Movie movie = movies.get(movieIdx);
        List<String> times = movie.getUpcomingTimes();

//        Scanner userInput = new Scanner(System.in);
        boolean choseTime = false;
        while (!choseTime) {
            System.out.println("Select a time to book for the movie:");
            for (int i = 0; i < times.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + times.get(i));
            }
            System.out.println("  " + (times.size() + 1) + ". Back to home page");

            int entered = 0;
            if (userInput.hasNextInt()) {
                entered = userInput.nextInt();
            } else {
                System.out.println("Error: Not a valid option.\n");
                userInput.nextLine();
                continue;
            }

            if (entered > 0 && entered <= times.size() + 1) {
                if (entered == times.size() + 1) {
                    System.out.println("Returning back to customer home page.\n");
                    return;
                }

//                pickBookingSeat(movie, entered - 1);
                bookingNumOfSeats(userInput, movie, entered - 1);
                choseTime = true;
            } else {
                System.out.println("Error: Not a valid option.\n");
                continue;
            }
        }
    }

    public void bookingNumOfSeats(Scanner userInput, Movie movie, int timeIdx) {
        userInput.nextLine();
//        Scanner userInput = new Scanner(System.in);
        boolean choseNumSeats = false;
        while (!choseNumSeats) {
            System.out.println("Enter the number of seats you would like book.\n" +
                    "(Required to enter for all options. Split by comma. E.g. 0,0,1,1)\n" +
                    "Child (under 12):\n" +
                    "Student:\n" +
                    "Adult:\n" +
                    "Senior/Pensioner:\n" +
                    "(Enter 'cancel' to return to the home page)");
            String selections = userInput.nextLine();

            // Split the selection input and convert to int
            List<Integer> numOfSeats = new ArrayList<Integer>();
            boolean invalid = false;
            int totalSeats = 0;
            for (String s : selections.split(",")) {
                try {
                    int input = Integer.parseInt(s.trim());
                    if (input < 0) {
                        invalid = true;
                        break;
                    }
                    numOfSeats.add(input);
                    totalSeats += input;
                } catch (NumberFormatException e) {
                    if (s.equals("cancel")) {
                        System.out.println("Returning back to customer home page.\n");
                        return;
                    }
                    invalid = true;
                }
            }
            if (invalid) {
                System.out.println("Error: Invalid option entered.\n");
                continue;
            }

            if (numOfSeats.size() != 4) {
                System.out.println("Error: Invalid option entered.\n");
                continue;
            }

            if (totalSeats <= 0) {
                System.out.println("Error: Invalid option entered.\n");
                continue;
            }

            pickBookingSeat(userInput, movie, timeIdx, totalSeats);
            choseNumSeats = true;

        }
    }

    public void pickBookingSeat(Scanner userInput, Movie movie, int timeIdx, int numOfBookingSeats) {
        userInput.nextLine();
        List<String> times = movie.getUpcomingTimes();
//        List<Integer> seats = movie.getSeats(timeIdx);

//        Scanner userInput = new Scanner(System.in);
        boolean choseSeat = false;
        while (!choseSeat) {
            System.out.println("Select the seat you would like:\n" +
                    "  1. Front\n" +
                    "  2. Middle\n" +
                    "  3. Rear\n" +
                    "  4. Back to home page");

            int entered = 0;
            if (userInput.hasNextInt()) {
                entered = userInput.nextInt();
            } else {
                System.out.println("Error: Not a valid option.\n");
                userInput.nextLine();
                continue;
            }

            if (entered > 0 && entered <= 4) {
                if (entered == 4) {
                    System.out.println("Returning back to customer home page.\n");
                    return;
                }

                boolean validSeat = movie.setSeats(timeIdx, entered - 1, numOfBookingSeats);
                if (!validSeat) {
                    continue;
                }
                // generate transaction id
                int payment = checkPayment(userInput);

                String cancelReason = "";
                if (payment == 1) {
                    cancelReason = "card payment failed";
                }

                String randIdChars = "abcdefghijklmnopqrstuvwyxz";
                randIdChars += randIdChars.toUpperCase();
                randIdChars += "1234567890";
                String transactionId = "";
                boolean unique = false;
                while (!unique) {
                    transactionId = "";
                    Random rand = new Random();
                    for (int i = 0; i < 6; i++){
                        int num = rand.nextInt(randIdChars.length());
                        transactionId += randIdChars.charAt(num);
                    }

                    for (Transaction transaction : transactions) {
                        if (transaction.getId().equals(transactionId)) {
                            continue;
                        }
                    }
                    unique = true;
                }

                String[] cinemaSeats = {"Front", "Middle", "Rear"};

                Transaction transaction = new Transaction(transactionId, movie, times.get(timeIdx), cinemaSeats[entered - 1], numOfBookingSeats, cancelReason);
                transactions.add(transaction);
                currAcc.addTransaction(transaction);

                if (payment == 2){
                    System.out.println("----------------------\n" +
                            transaction.getTransactionInformation() +
                            "----------------------\n");
                }
                choseSeat = true;

            } else {
                System.out.println("Error: Not a valid option.\n");
                continue;
            }
        }
    }

//    public void setCreditCardFile(String file) {
//        this.creditCardFile = file;
//    }

    public int checkPayment(Scanner userInput){
        JSONParser parser = new JSONParser();
        boolean cardFound = false;
//        Scanner userInput = new Scanner(System.in);
//        userInput.nextLine();
        try {
            cards = (JSONArray) parser.parse(new FileReader(this.creditCardFile));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


        while(this.checkPaymentVal == 5) {
            System.out.println("Select the payment option you would like\n" +
                    "  1. Credit Card\n" +
                    "  2. Gift Card\n" +
                    "  3. Return to seat selection");


            int entered = 0;
            if (userInput.hasNextInt()) {
                entered = userInput.nextInt();
            } else {
                System.out.println("Error: Not a valid option.\n");
                userInput.nextLine();
                continue;
            }

            if (entered > 0 && entered <= 4) {
                if (entered == 1) {
                    if(currAcc.getHasCard()){
                        return 2;
                    }
                    System.out.println("Please enter the card holders name and number");
                    System.out.print("Card holders name: ");
                    userInput.nextLine();
                    String name = userInput.nextLine();
                    String number = PasswordMasker.readPassword("Card number: ");
//                    String number = userInput.nextLine();
//                    System.out.print("Card number: ");

                    for (Object n : cards) {

                        if (((JSONObject) n).get("name").equals(name) && ((JSONObject) n).get("number").equals(number)){
                            System.out.println("Transaction was successful");
                            currAcc.setHasCard(true);
                            cardFound = true;
                            return 2;
                        }

                    }
                    if (cardFound == false){
                        System.out.println("Card details were incorrect, transaction was unsuccessful");
                        return 1;
                    }


                }
                else if (entered == 2){
                    System.out.println("Please enter the 16 digit gift card number followed by 'GC'");
                    System.out.print("Card number: ");
                    userInput.nextLine();
                    String cardNo = userInput.nextLine();

                    for (GiftCard c : this.giftCards){

                        if(cardNo.equals(c.getNumber())){
                            if(c.getRedeemed() == 1){
                                System.out.println("\nGift card has already been redeemed, try again.\n");
                                cardFound = true;
                                continue;
                            }
                            System.out.println("\nCongratulations gift card has successfully been redeemed!\n");
                            cardFound = true;
                            updateGiftCardStateCSV(c);
                            return 2;
                        }
                    }
                    if (cardFound == false){
                        System.out.println("Card details were incorrect, transaction was unsuccessful");
                        return 1;
                    }

                }
                else if (entered == 3) {
                    System.out.println("Returning back to customer home page.\n");
                    return 0;
                }
            }



        }
        return 0;
    }

    public void cancelBooking(Scanner userInput) {
//        Scanner userInput = new Scanner(System.in);
        boolean cancelledBooking = false;
        while (!cancelledBooking) {
            System.out.println("Select the booking you would like to cancel:");
            for (int i = 0; i < currAcc.getTransactions().size(); i++) {
                Transaction transaction = currAcc.getTransactions().get(i);
                System.out.println((i + 1) + ".");
                System.out.println(transaction.getTransactionInformation());
            }
            System.out.println((currAcc.getTransactions().size() + 1) + ". Back to home page");

            int entered = 0;
            if (userInput.hasNextInt()) {
                entered = userInput.nextInt();
            } else {
                System.out.println("Error: Not a valid option.\n");
                userInput.nextLine();
                continue;
            }

            if (entered > 0 && entered <= currAcc.getTransactions().size() + 1) {
                if (entered == currAcc.getTransactions().size() + 1) {
                    System.out.println("Returning back to customer home page.\n");
                    return;
                }

                Transaction transaction = currAcc.getTransactions().get(entered - 1);
                currAcc.removeTransaction(entered - 1);

                List<String> cinemaSeats = Arrays.asList("Front", "Middle", "Rear");
                int seat = cinemaSeats.indexOf(transaction.getSeat());
                transaction.setCancelReason("user cancelled");

                transaction.getMovie().addSeats(transaction.getMovieTime(), seat, transaction.getNumOfSeats());
                System.out.println("Booking removed.\n");
                cancelledBooking = true;

            } else {
                System.out.println("Error: Not a valid option.\n");
                continue;
            }

        }


    }

    public void staffLoginLogic(){
        Scanner userInput = new Scanner(System.in);
        while(loggedIn){
            System.out.println("Select the page you would like to visit:\n" +
                    "  1. Movie report\n" +
                    "  2. Summary of Bookings\n" +
                    "  3. Movie Management\n" +
                    "  4. Add New Shows for Next Week\n" +
                    "  5. Giftcard Management\n" +
                    "  6. Logout");

            int logged = 0;
            if (userInput.hasNextInt()) {
                logged = userInput.nextInt();
            }

            if (logged == 4){
                addNewShows();
//                break;
            }
            else if (logged == 5) {
                giftCardManage();
            }
            else if (logged == 6) {
                this.loggedIn = false;
                System.out.println("You have logged out");
            }
            else {
                System.out.println("Error: Not a valid option.");
                userInput.nextLine();
            }

        }
//        userInput.close();
    }

    public void updateGiftCardStateCSV(GiftCard card){
        card.setRedeemed(1);
        List<List<String>> cardsDetails = new ArrayList<>();

        for (GiftCard c : this.giftCards){
            List<String> gc = new ArrayList<>(2);
            for(int i = 0; i < 2;i++){
                gc.add("");
            }
            String cardNum = c.getNumber();
            String cardState = String.valueOf(c.getRedeemed());

            gc.set(0,cardNum);
            gc.set(1,cardState);

            cardsDetails.add(gc);
        }
        try {
            FileWriter csvWriter = new FileWriter(this.giftCardFile);
            // Write all the details of the cards into the .csv file, having each card as a new entry on a new line
            for (List<String> c : cardsDetails) {
                csvWriter.append(String.join(",", c));  // Combine all elements in the list and separate by a comma
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error: Couldn't update the database");
        }



    }

    public void giftCardDelete(String GCNumber) {
        String[] data = new String[99];
        int count = 0;
        int rowCount = 0;
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(this.giftCardFile));
            String row;

            while ((row = csvReader.readLine()) != null) {
                if(row.split(",")[0].equals(GCNumber)){
                    count++;
                    continue;
                }

                data[rowCount] = row;
                rowCount++;
                count++;


            }
        }catch(IOException e){

        }
        try {
            FileWriter csvWriter = new FileWriter(this.giftCardFile, false);
            BufferedWriter bw = new BufferedWriter(csvWriter);
            for (int j = 0; j < rowCount; j++) {
                bw.append(String.valueOf(data[j]));
                bw.append("\n");
            }
            bw.close();
            System.out.println("\nYou have successfully updated the gift card database.");

        }catch(IOException e){

        }
    }

    public void giftCardView() {
//        try {
//            BufferedReader csvReader = new BufferedReader(new FileReader(this.giftCardFile));
//            String row;
            System.out.println("Current gift cards:\n");
//            while ((row = csvReader.readLine()) != null) {
//                    System.out.println(row+"\n");
//                }
//            }
//        catch(IOException e){
//
//        }
            for(GiftCard c : this.giftCards){
                System.out.println(c.getGCInfo());
            }

    }
    public void giftCardManage(){
        Scanner userInput = new Scanner(System.in);
        String code = null;
        boolean cont = true;

        while (cont) {
            System.out.println("Would you like to add/delete or view gift cards?\n" +
                    "  1. Add\n" +
                    "  2. Delete\n" +
                    "  3. View\n" +
                    "  4. Exit");
            int logged = 0;
            if (userInput.hasNextInt()) {
                logged = userInput.nextInt();
            }
            switch (logged) {
                case 1: giftCardCreate(userInput);
                        break;
                case 2: System.out.println("Please input the gift card code to delete.");
                        if (userInput.hasNextLine()) {
                            code = userInput.nextLine();
                        }
                        giftCardDelete(code);
                        break;
                case 3: giftCardView();
                        break;

                case 4: cont = false;
                        break;


                default: System.out.println("Error: Not a valid option.");
                         userInput.nextLine();

            }

        }

    }

    public void giftCardCreate(Scanner userInput) {
        boolean correct = false;
        String number;

        while (true) { // The while loop ensures continual prompt in the case bad input is given
            System.out.println("Please create a new gift card by entering the code.");
            System.out.print("Code: ");
            number = userInput.nextLine();
            try {
                BufferedReader giftCardReader = new BufferedReader(new FileReader(this.giftCardFile));
                String line;
                boolean unique = true;
                while ((line = giftCardReader.readLine()) != null) {
                    String[] ls = line.split(",");
                    if (ls[0].equals(number)) {
                        System.out.println("\nThis gift card has already been created. Please try again.\n");
                        unique = false;
                        break;
                    } else if (number.matches(".*\\s.*")) {
                        System.out.println("\nGift card code cannot contain spaces. Please try again.\n");
                        unique = false;
                        break;
                    } else if (number.length() != 18){
                        System.out.println("\nGift card code has to be a 16 digit number followed by 'GC' suffix. Please try again.\n");
                        unique = false;
                        break;
                    } else if (number.length() == 0) {
                        System.out.println("\nGift card code cannot be empty. Please try again.\n");
                        unique = false;
                        break;
                    } else if (number.length() == 18){
                        for(int count = 0; count < 15; count++){
                            if(Character.isDigit(number.charAt(count)) == true){
                                correct = true;
                            } else {
                                correct = false;
                                unique = false;
                                break;
                            }

                        }
                        if(correct == true){
                            if(number.charAt(16) == 'G'){
                                if(number.charAt(17) == 'C'){
                                    correct = true;
                                } else{
                                    System.out.println("\nGift card code has to be a 16 digit number followed by 'GC' suffix. Please try again.\n");
                                    correct = false;
                                    unique = false;
                                    break;
                                }
                            } else{
                                System.out.println("\nGift card code has to be a 16 digit number followed by 'GC' suffix. Please try again.\n");
                                correct = false;
                                unique = false;
                                break;
                            }
                        }
                    }
                }


                if (!unique) {
                    continue;
                }
                break;
            } catch (IOException e) {
                System.out.println("Error: couldn't update giftcards.csv");
                break;
            }

        }
        try {
            FileWriter csvWriter = new FileWriter(this.giftCardFile, true);
            BufferedWriter bw = new BufferedWriter(csvWriter);
            bw.write(String.format("%s,%s\n", number, "0"));
            bw.close();
            System.out.println("\nYou have successfully created a new gift card.");
        } catch (IOException e) {
            System.out.println("Error: couldn't update giftcards.csv");
        }

        GiftCard newCard = createNewGiftCard(number, 0);
        this.giftCards.add(newCard);
    }

    public GiftCard createNewGiftCard(String number, int redeemed){
        return new GiftCard(number, redeemed);
    }

    public void staffHire(int runVersion){
        String username;
        String password;
        String againPassword;
        while (true) { // The while loop ensures continual prompt in the case passwords do not match
            System.out.println("Please enter a username and password for the new staff.");
            System.out.print("Username: ");
            Scanner name = new Scanner(System.in);
            username = name.nextLine();
            if(runVersion == 1){
                System.out.println("Password: ");
                password = name.nextLine();
                System.out.println("Confirm password: ");
                againPassword = name.nextLine();
            } else {
                password = PasswordMasker.readPassword("Password: ");
                againPassword = PasswordMasker.readPassword("Confirm password: ");
            }

            try {
                BufferedReader customersReader = new BufferedReader(new FileReader(this.accountsFile));
                String line;
                boolean unique = true;
                while ((line = customersReader.readLine()) != null) {
                    String[] ls = line.split(",");
                    if (ls[0].equals(username)) {
                        System.out.println("\nThis username is already taken. Please try again.\n");
                        unique = false;
                        break;
                    }
                    else if (username.matches(".*\\s.*")) {
                        System.out.println("\nUsername cannot contain spaces. Please try again.\n");
                        unique = false;
                        break;
                    }
                    else if (username.length() == 0) {
                        System.out.println("\nUsername cannot be empty. Please try again.\n");
                        unique = false;
                        break;
                    }
                }
                if (!unique) {
                    continue;
                }
            } catch (IOException e) {
                System.out.println("Error: couldn't update accounts.csv");
                break;
            }

            if (!password.equals(againPassword)) {
                System.out.println("\nPasswords do not match. Please try again.\n");
                continue;
            } else if (password.matches(".*\\s.*")) {
                System.out.println("\nPassword cannot contain spaces. Please try again.\n");
                continue;
            }
            else if (password.length() == 0) {
                System.out.println("\nPassword cannot be empty. Please try again.\n");
                continue;
            }
            else {
                break;
            }
        }

        // Opens the local customer database to store customer's details
        try {
            FileWriter csvWriter = new FileWriter(this.accountsFile, true);
            BufferedWriter bw = new BufferedWriter(csvWriter);
            bw.write(String.format("%s,%s,%s\n", username, password, "1"));
            bw.close();
            System.out.println("\nYou have successfully hired a new staff member to Fancy Cinemas!");
            System.out.println("You will return to the staff management menu.\n");
        }
        catch (IOException e) {
            System.out.println("Error: couldn't update accounts.csv");
        }

        Account acc = createNewAccount(username, password,0);
        this.accounts.add(acc);

    }
    public void staffFire(String userName){
        String[] data = new String[99];
        int count = 0;
        int rowCount = 0;
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(this.accountsFile));
            String row;
            while ((row = csvReader.readLine()) != null) {
                if(row.split(",")[0].equals(userName)){
                    count++;
                    continue;
                }

                data[rowCount] = row;
                rowCount++;
                count++;
            }
        }catch(IOException e){

        }
        try {
            FileWriter csvWriter = new FileWriter(this.accountsFile, false);
            BufferedWriter bw = new BufferedWriter(csvWriter);
            for (int j = 0; j < rowCount; j++) {
                bw.append(String.valueOf(data[j]));
                bw.append("\n");
            }
            bw.close();
            System.out.println("\nYou have successfully removed that staff member.");

        }catch(IOException e){

        }

    }

    public void staffView(){
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(this.accountsFile));
            String row;
            System.out.println("Current staff members:\n");
            while ((row = csvReader.readLine()) != null) {
                if(row.split(",")[2].equals("1")) {
                    System.out.println(row.split(",")[0] + "\n");
                }
            }
        }
        catch(IOException e){

        }
    }

    public void staffManage(int runVersion){
        Scanner userInput = new Scanner(System.in);
        String code = null;
        boolean cont = true;

        while (cont) {
            System.out.println("Would you like to hire/fire a staff member?\n" +
                    "  1. Hire\n" +
                    "  2. Fire\n" +
                    "  3. View\n" +
                    "  4. Exit");
            int choice = 0;
            if (userInput.hasNextInt()) {
                choice = userInput.nextInt();
            }
            if(choice == 1) {
                staffHire(runVersion);
                break;
            } else if (choice == 2) {
                System.out.println("Please input the staff username to remove:");
                Scanner newInput = new Scanner(System.in);
                if (newInput.hasNextLine()) {
                    code = newInput.nextLine();
                }
                staffFire(code);
                break;
            } else if (choice == 3) {
                staffView();
                break;
            } else if (choice == 4) {
                cont = false;
                break;
            } else{
                System.out.println("Error: Not a valid option.");
                userInput.nextLine();
            }

        }


    }

    public void managerLoginLogic(){
        Scanner userInput = new Scanner(System.in);
        while(loggedIn){
            System.out.println("Select the page you would like to visit:\n" +
                    "  1. Movie report\n" +
                    "  2. Summary of Bookings\n" +
                    "  3. Movie Management\n" +
                    "  4. Add New Shows for Next Week\n" +
                    "  5. Gift Card Management\n" +
                    "  6. Staff management\n" +
                    "  7. Transaction management\n" +
                    "  8. Logout");

            int logged = 0;
            if (userInput.hasNextInt()) {
                logged = userInput.nextInt();
            }
            switch (logged) {
                case 4: addNewShows();
                        break;
                case 5: giftCardManage();
                        break;
                case 6: staffManage(2);
                        break;
                case 8: this.loggedIn = false;
                        System.out.println("You have logged out");
                        break;



                default: System.out.println("Error: Not a valid option.");
                         userInput.nextLine();
            }

        }
//        input.close();
    }

    // MAIN LOOP WILL BE HERE RATHER THAN IN MAIN.
    // DATABASE FILES WILL BE LOCATED IN 'src/main/resources'.
    // movies.csv IS SEMICOLON DELIMITED.
    public void run() throws FileNotFoundException {
        boolean running = true;
        getMovies();
        createAccounts();
        createGiftCards();

        Scanner userInput = new Scanner(System.in);
        while (running) {

            //this is to test the view movie functionality
            System.out.println("Select the page you would like to visit:\n" +
                    "  1. All movies\n" +
                    "  2. Filter movies\n" +
                    "  3. Register as Fancy Cinemas Customer\n" +
                    "  4. Login\n" +
                    "  5. Exit");
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
                filterMovies(userInput);
            }
            else if (entered == 3) {
                registerCustomer();
            }
            else if (entered == 4) {
                loginUser();
            }
            else if (entered == 5) { // CHANGE THE NUMBER OF THIS WHEN MORE OPTIONS ARE ADDED
                running = false;
            }
            else {
                System.out.println("Error: Not a valid option.\n");
                continue;
            }

            //logged in customer screen
            if (this.loggedIn) {
                if(currAcc.getPerm() == 0){
                    customerLoginLogic(userInput);

                }

                else if(currAcc.getPerm() == 1){
                    staffLoginLogic();
                }

                else if(currAcc.getPerm() == 2){
                    managerLoginLogic();
                }



            }
        }

        userInput.close();
    }





    public static void main(String[] args) throws FileNotFoundException {
        Cinema cinema = new Cinema();
        cinema.run();
    }

}
