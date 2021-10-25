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

public class Cinema {
    private List<Movie> movies;
    private List<String> screenSizes;
    private List<Account> accounts;
    private List<GiftCard> giftCards;
    private boolean loggedIn = false;
    private File accountsFile = new File("src/main/resources/accounts.csv");
    private File moviesFile = new File("src/main/resources/movies.csv");
    private File giftCardFile = new File("src/main/resources/giftcards.csv");
    private Account currAcc;
    private List<String> allCinemaRooms;

    public Cinema() {
        this.movies = new ArrayList<Movie>();
        this.screenSizes = Arrays.asList("Bronze", "Silver", "Gold");
        this.accounts = new ArrayList<Account>();
        this.allCinemaRooms = Arrays.asList("1", "2", "3");
        this.giftCards = new ArrayList<GiftCard>();
    }

    /**
     * In charge of creating the proper movie objects
     * helper method
     */
    public Movie createMovie(String[] details){
        Date release_date = null;
        List<String> upcoming_times = null;
        List<String> cinema_rooms = null;

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            release_date = sdf.parse(details[3]);

            upcoming_times = Arrays.asList(details[6].split(","));

            cinema_rooms = Arrays.asList(details[8].split(","));
        }
        catch(Exception e){
            System.out.println("Error: Failed to load database");
        }

        Movie movie = new Movie(details[0], details[1], details[2], release_date, details[4], details[5], upcoming_times, details[7], cinema_rooms);
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
                "  6. Room 3\n"
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

        // Check if all the selected options are valid
        for (int filter : filters) {
            if (filter >= 1 && filter <= 3) {
                screenSizeFilters.add(this.screenSizes.get(filter - 1));
            }
            else if (filter >= 4 && filter <= 6) {
                cinemaRoomFilters.add(this.allCinemaRooms.get(filter - 4));
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

        List<Movie> displayMovies = new ArrayList<Movie>();
        for (Movie movie : this.movies) {
            if (screenSizeFilters.contains(movie.getScreenSize())) {
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

    public void setLogged(boolean state){
        this.loggedIn = state;
    }

    public void customerLoginLogic(){
        Scanner userInput = new Scanner(System.in);
        while(loggedIn){
            System.out.println("Select the page you would like to visit:\n" +
                    "  1. All movies\n" +
                    "  2. Filter movies\n" +
                    "  3. Book\n" +
                    "  4. Cancel Booking\n" +
                    "  5. Logout");

            int logged = 0;
            if (userInput.hasNextInt()) {
                logged = userInput.nextInt();
            }


            if (logged == 5) {
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
            switch (logged) {
                case 5: giftCardManage();
                        break;
                case 6: this.loggedIn = false;
                        System.out.println("You have logged out");

                default: System.out.println("Error: Not a valid option.");
                         userInput.nextLine();


            }

        }
//        userInput.close();
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
    public void giftCardManage(){
        Scanner userInput = new Scanner(System.in);
        String code = null;
        boolean cont = true;

        while (cont) {
            System.out.println("Would you like to add or delete a gift card?\n" +
                    "  1. Add\n" +
                    "  2. Delete\n" +
                    "  3. Exit");
            int logged = 0;
            if (userInput.hasNextInt()) {
                logged = userInput.nextInt();
            }
            switch (logged) {
                case 1: giftCardCreate();
                        break;
                case 2: System.out.println("Please input the gift card code to delete.");
                        Scanner newInput = new Scanner(System.in);
                        if (newInput.hasNextLine()) {
                            code = newInput.nextLine();
                        }
                        giftCardDelete(code);
                        break;
                case 3: cont = false;
                        break;


                default: System.out.println("Error: Not a valid option.");
                         userInput.nextLine();
            }

        }

    }

    public void giftCardCreate() {
        boolean correct = false;
        String number;

        while (true) { // The while loop ensures continual prompt in the case bad input is given
            System.out.println("Please create a new gift card by entering the code.");
            System.out.print("Code: ");
            Scanner code = new Scanner(System.in);
            number = code.nextLine();
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
                        System.out.println("\nGift card code has to be a 16 digit number followed by 'GC' prefix. Please try again.\n");
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
                                    System.out.println("\nGift card code has to be a 16 digit number followed by 'GC' prefix. Please try again.\n");
                                    correct = false;
                                    unique = false;
                                    break;
                                }
                            } else{
                                System.out.println("\nGift card code has to be a 16 digit number followed by 'GC' prefix. Please try again.\n");
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
                case 5: giftCardManage();
                        break;
                case 8: this.loggedIn = false;
                        System.out.println("You have logged out");


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
                    customerLoginLogic();

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