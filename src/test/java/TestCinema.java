import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class TestCinema {
    //this cinema is a specific one made for testing
    private Cinema cinema_1_movie;
    //one below is the regular one
    private Cinema cinema;

    @BeforeEach
    public void setup() {
        cinema_1_movie = new Cinema();
        //reading in specific new movie csv
        cinema = new Cinema();
        File movie_file = new File("src/test/resources/movies_test.csv");
        cinema_1_movie.setMovies(movie_file);

        File accounts_file = new File("src/test/resources/accounts_test.csv");
        cinema_1_movie.setAccountsFile(accounts_file);

        File giftcards_file = new File("src/test/resources/giftcards_test.csv");
        cinema_1_movie.setGiftCardFile(giftcards_file);
    }

    @BeforeEach
    public void resetGiftCardCSV() {
        List<List<String>> cardsDetails = new ArrayList<List<String>>();
        cardsDetails.add(Arrays.asList("1838281828382818GC", "1"));
        cardsDetails.add(Arrays.asList("1092380138203801GC", "0"));
        cardsDetails.add(Arrays.asList("6123876381763821GC", "0"));
        cardsDetails.add(Arrays.asList("1212121212121212GC", "0"));

        try {
            FileWriter csvWriter = new FileWriter("src/test/resources/giftcards_test.csv");

            for (List<String> c : cardsDetails) {
                csvWriter.append(String.join(",", c));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }

    @Test
    void testDisplayMovies(){
        cinema_1_movie.getMovies();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        cinema_1_movie.displayMovies();

        String expected = "The Shawshank Redemption\n" +
                "Classification: MA15+\n" +
                "Synopsis: Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.\n" +
                "Release date: Thu Feb 16 00:00:00 AEDT 1995\n" +
                "Director: Frank Darabont\n" +
                "Cast: Tim Robbins, Morgan Freeman, Bob Gunton\n" +
                "Screen size: Gold\n" +
                "Upcoming times: 10:45, 14:00\n" +
                "Cinema rooms: 1, 2\n" +
                "Available seats: 6, 6\n";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

//    @Test
//    void testCustomerLoginLogic() {
//        //Cinema cinema = new Cinema();
//        assertEquals(false, cinema.getLogged());
//        cinema.setLogged(true);
//
//        String userInput = "5\n";
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(inputStream);
//
//        //catching output
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(outputStream);
//        System.setOut(printStream);
//
//        cinema.customerLoginLogic();
//
//        String expected = "You have logged out";
//        String[] output = outputStream.toString().split("\n");
//        String actual = output[output.length-1];
//        assertEquals(expected, actual.trim());
//    }

//    void testCorrectCustomerRegistrationLogic(){
//        File accountsFile = new File("src/test/resources/accounts_test.csv");
//        cinema.setAccounts(accountsFile);
//
//        //catching input
//        String userInput = String.format("bradpitt%sfightclub%sfightclub",
//                System.lineSeparator(), System.lineSeparator());
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(inputStream);
//
//        //catching output
//        String expected = String.format("%sYou have successfully registered as a new customer. Welcome to Fancy Cinemas!%s" +
//                "You will return to the main menu logged into your new account.%s",
//                System.lineSeparator(), System.lineSeparator(), System.lineSeparator());
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(outputStream);
//        System.setOut(printStream);
//
//        //running method
//        cinema.registerCustomer();
//        String[] output = outputStream.toString().split(System.lineSeparator());
//        String actual = output[output.length - 1];
//        assertEquals(expected, actual.trim());
//    }

//    @Test
//    void badLogicInput(){
//        cinema.setLogged(true);
//
//        String userInput = "1236\n5\n";
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(inputStream);
//
//        //catching output
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(outputStream);
//        System.setOut(printStream);
//
//        cinema.customerLoginLogic();
//
//        String expected = "You have logged out";
//        String[] output = outputStream.toString().split("\n");
//        String actual = output[output.length-1];
//        assertEquals(expected, actual.trim());
//    }

    @Test
    void testStaffLoginLogic(){
        cinema.setLogged(true);

        String userInput = "2\n6\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        //catching output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema.staffLoginLogic();

        String expected = "You have logged out";
        String[] output = outputStream.toString().split("\n");
        String actual = output[output.length-1];
        assertEquals(expected, actual.trim());
    }

    @Test
    void testManagerLoginLogic(){
        cinema.setLogged(true);

        String userInput = "2\n8\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        //catching output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema.managerLoginLogic();

        String expected = "You have logged out";
        String[] output = outputStream.toString().split("\n");
        String actual = output[output.length-1];
        assertEquals(expected, actual.trim());
    }

    @Test
    public void testValidFilterMovies() {
        Scanner scanner = new Scanner("\n1");
        assertTrue(cinema.filterMovies(scanner));

        scanner = new Scanner("\n2");
        assertTrue(cinema.filterMovies(scanner));

        scanner = new Scanner("\n3");
        assertTrue(cinema.filterMovies(scanner));

        scanner = new Scanner("\n4");
        assertTrue(cinema.filterMovies(scanner));

        scanner = new Scanner("\n5");
        assertTrue(cinema.filterMovies(scanner));

        scanner = new Scanner("\n6");
        assertTrue(cinema.filterMovies(scanner));

        scanner = new Scanner("\n6  ");
        assertTrue(cinema.filterMovies(scanner));

        scanner.close();
    }

    @Test
    public void testEmptyFilterMovies() {
        Scanner scanner = new Scanner("\n\n");
        assertFalse(cinema.filterMovies(scanner));

        scanner.close();
    }

    @Test
    public void testValidMultipleOptionsFilterMovies() {
        Scanner scanner = new Scanner("\n1,5");
        assertTrue(cinema.filterMovies(scanner));

        scanner = new Scanner("\n1,2");
        assertTrue(cinema.filterMovies(scanner));

        scanner = new Scanner("\n4,6");
        assertTrue(cinema.filterMovies(scanner));

        scanner = new Scanner("\n1,2,3");
        assertTrue(cinema.filterMovies(scanner));

        scanner = new Scanner("\n 4, 6");
        assertTrue(cinema.filterMovies(scanner));

        scanner = new Scanner("\n1,2,3,4,5,6");
        assertTrue(cinema.filterMovies(scanner));

        scanner.close();
    }

    @Test
    public void testCatchNumberFormatExceptionFilterMovies() {
        Scanner scanner = new Scanner("\na");
        assertFalse(cinema.filterMovies(scanner));

        scanner = new Scanner("\n1,a");
        assertFalse(cinema.filterMovies(scanner));

        scanner.close();
    }

    @Test
    public void testNumberInvalidOptionFilterMovies() {
        Scanner scanner = new Scanner("\n20");
        assertFalse(cinema.filterMovies(scanner));

        scanner = new Scanner("\n-3");
        assertFalse(cinema.filterMovies(scanner));

        scanner = new Scanner("\n3,800000");
        assertFalse(cinema.filterMovies(scanner));

        scanner.close();
    }

    @Test
    public void testCreateAccounts_Valid() throws FileNotFoundException {
        cinema_1_movie.createAccounts();
        List<Account> accounts = cinema_1_movie.getAccounts();

        Account customer = accounts.get(0);
        assertEquals("john", customer.getUsername());
        assertEquals("smith", customer.getPassword());
        assertEquals(0, customer.getPerm());

        Account staff = accounts.get(1);
        assertEquals("tony", staff.getUsername());
        assertEquals("pit", staff.getPassword());
        assertEquals(1, staff.getPerm());

        Account manager = accounts.get(2);
        assertEquals("bob", manager.getUsername());
        assertEquals("jones", manager.getPassword());
        assertEquals(2, manager.getPerm());
    }

    @Test
    public void testCreateAccounts_ThrowFileNotFound() {
        Cinema cinema_not_found = new Cinema();
        File accounts_file = new File("src/test/resources/not_found.csv");
        cinema_not_found.setAccountsFile(accounts_file);
        boolean caught = false;
        try {
            cinema_not_found.createAccounts();
        } catch (FileNotFoundException e) {
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    public void testRun_Exit() throws FileNotFoundException {
        InputStream sysInBackup = System.in;

        String userInput = "5";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        cinema.run();

        String expected = "Select the page you would like to visit:\n" +
                "  1. All movies\n" +
                "  2. Filter movies\n" +
                "  3. Register as Fancy Cinemas Customer\n" +
                "  4. Login\n" +
                "  5. Exit";

        assertEquals(expected, outputStream.toString().trim());
        System.setIn(sysInBackup);
    }

    @Test
    public void testRun_NumberNotAnOption() throws FileNotFoundException {
        InputStream sysInBackup = System.in;

        String userInput = "7\n5\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        cinema.run();

        String expected = "Select the page you would like to visit:\n" +
                "  1. All movies\n" +
                "  2. Filter movies\n" +
                "  3. Register as Fancy Cinemas Customer\n" +
                "  4. Login\n" +
                "  5. Exit\n" +
                "Error: Not a valid option.\n" +
                "\n" +
                "Select the page you would like to visit:\n" +
                "  1. All movies\n" +
                "  2. Filter movies\n" +
                "  3. Register as Fancy Cinemas Customer\n" +
                "  4. Login\n" +
                "  5. Exit";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
        System.setIn(sysInBackup);
    }

    @Test
    public void testRun_WrongDataType() throws FileNotFoundException {
        InputStream sysInBackup = System.in;

        String userInput = "a\n5\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        cinema.run();

        String expected = "Select the page you would like to visit:\n" +
                "  1. All movies\n" +
                "  2. Filter movies\n" +
                "  3. Register as Fancy Cinemas Customer\n" +
                "  4. Login\n" +
                "  5. Exit\n" +
                "Error: Not a valid option.\n" +
                "\n" +
                "Select the page you would like to visit:\n" +
                "  1. All movies\n" +
                "  2. Filter movies\n" +
                "  3. Register as Fancy Cinemas Customer\n" +
                "  4. Login\n" +
                "  5. Exit";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
        System.setIn(sysInBackup);
    }

    @Test
    public void testRun_DisplayMovies() throws FileNotFoundException {
        InputStream sysInBackup = System.in;

        String userInput = "1\n5\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        cinema_1_movie.run();

        String expected = "Select the page you would like to visit:\n" +
                "  1. All movies\n" +
                "  2. Filter movies\n" +
                "  3. Register as Fancy Cinemas Customer\n" +
                "  4. Login\n" +
                "  5. Exit\n" +
                "The Shawshank Redemption\n" +
                "Classification: MA15+\n" +
                "Synopsis: Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.\n" +
                "Release date: Thu Feb 16 00:00:00 AEDT 1995\n" +
                "Director: Frank Darabont\n" +
                "Cast: Tim Robbins, Morgan Freeman, Bob Gunton\n" +
                "Screen size: Gold\n" +
                "Upcoming times: 10:45, 14:00\n" +
                "Cinema rooms: 1, 2\n" +
                "Available seats: 6, 6\n" +
                "\n" +
                "Select the page you would like to visit:\n" +
                "  1. All movies\n" +
                "  2. Filter movies\n" +
                "  3. Register as Fancy Cinemas Customer\n" +
                "  4. Login\n" +
                "  5. Exit";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
        System.setIn(sysInBackup);
    }

    @Test
    public void testRun_FilterMovies() throws FileNotFoundException {
        InputStream sysInBackup = System.in;

        String userInput = "2\n3\n5\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        cinema_1_movie.run();

        String expected = "Select the page you would like to visit:\n" +
                "  1. All movies\n" +
                "  2. Filter movies\n" +
                "  3. Register as Fancy Cinemas Customer\n" +
                "  4. Login\n" +
                "  5. Exit\n" +
                "Select the options that you would like to filter.\n" +
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
                "  11. R18+\n" +
                "\n" +
                "The Shawshank Redemption\n" +
                "Classification: MA15+\n" +
                "Synopsis: Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.\n" +
                "Release date: Thu Feb 16 00:00:00 AEDT 1995\n" +
                "Director: Frank Darabont\n" +
                "Cast: Tim Robbins, Morgan Freeman, Bob Gunton\n" +
                "Screen size: Gold\n" +
                "Upcoming times: 10:45, 14:00\n" +
                "Cinema rooms: 1, 2\n" +
                "Available seats: 6, 6\n" +
                "\n" +
                "Select the page you would like to visit:\n" +
                "  1. All movies\n" +
                "  2. Filter movies\n" +
                "  3. Register as Fancy Cinemas Customer\n" +
                "  4. Login\n" +
                "  5. Exit";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
        System.setIn(sysInBackup);
    }

    @Test
    public void testMain_Exit() throws FileNotFoundException {
        InputStream sysInBackup = System.in;

        String userInput = "5";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Cinema.main(new String[0]);

        String expected = "Select the page you would like to visit:\n" +
                "  1. All movies\n" +
                "  2. Filter movies\n" +
                "  3. Register as Fancy Cinemas Customer\n" +
                "  4. Login\n" +
                "  5. Exit";

        assertEquals(expected, outputStream.toString().trim());
        System.setIn(sysInBackup);
    }

    @Test
    public void TestCustomerLoginLogic_BookMovieValid() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\n3\n1\n1\n1,0,0,0\n\n1\n2\n1092380138203801GC\n5\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.customerLoginLogic(scanner);

        String expected = "Select the page you would like to visit:\n" +
                "    1. All movies\n" +
                "    2. Filter movies\n" +
                "    3. Book\n" +
                "    4. Cancel a booking\n" +
                "    5. Logout\n" +
                "    Select a movie you would like to book:\n" +
                "    1. The Shawshank Redemption\n" +
                "    2. Back to home page\n" +
                "    Select a time to book for the movie:\n" +
                "    1. 10:45\n" +
                "    2. 14:00\n" +
                "    3. Back to home page\n" +
                "    Enter the number of seats you would like book.\n" +
                "    (Required to enter for all options. Split by comma. E.g. 0,0,1,1)\n" +
                "    Child (under 12):\n" +
                "    Student:\n" +
                "    Adult:\n" +
                "    Senior/Pensioner:\n" +
                "    (Enter 'cancel' to return to the home page)\n" +
                "    Select the seat you would like:\n" +
                "    1. Front\n" +
                "    2. Middle\n" +
                "    3. Rear\n" +
                "    4. Back to home page\n" +
                "    Select the payment option you would like\n" +
                "    1. Credit Card\n" +
                "    2. Gift Card\n" +
                "    3. Return to seat selection\n" +
                "    Please enter the 16 digit gift card number followed by 'GC'\n" +
                "    Card number:\n" +
                "    Congratulations gift card has successfully been redeemed!\n" +
                "\n" +
                "    ----------------------\n" +
                "    Transaction ID: " + cinema_1_movie.getCurrAcc().getTransactions().get(0).getId() + "\n" +
                "    Movie: The Shawshank Redemption\n" +
                "    Showing time: 10:45\n" +
                "    Seat: Front\n" +
                "    Number of seats: 1\n" +
                "    ----------------------\n" +
                "\n" +
                "    Select the page you would like to visit:\n" +
                "    1. All movies\n" +
                "    2. Filter movies\n" +
                "    3. Book\n" +
                "    4. Cancel a booking\n" +
                "    5. Logout\n" +
                "    You have logged out";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestBookMovie_invalidDataType() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\nhjk\n" + (movies.size() + 1) + "\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.bookMovie(scanner);

        String expected = "Select a movie you would like to book:\n" +
                "    1. The Shawshank Redemption\n" +
                "    2. Back to home page\n" +
                "    Error: Not a valid option.\n" +
                "\n" +
                "    Select a movie you would like to book:\n" +
                "    1. The Shawshank Redemption\n" +
                "    2. Back to home page\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestBookMovie_invalidPositiveNumber() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\n100\n" + (movies.size() + 1) + "\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.bookMovie(scanner);

        String expected = "Select a movie you would like to book:\n" +
                "    1. The Shawshank Redemption\n" +
                "    2. Back to home page\n" +
                "    Error: Not a valid option.\n" +
                "\n" +
                "    Select a movie you would like to book:\n" +
                "    1. The Shawshank Redemption\n" +
                "    2. Back to home page\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestBookMovie_invalidNegativeNumber() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\n-100\n" + (movies.size() + 1) + "\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.bookMovie(scanner);

        String expected = "Select a movie you would like to book:\n" +
                "    1. The Shawshank Redemption\n" +
                "    2. Back to home page\n" +
                "    Error: Not a valid option.\n" +
                "\n" +
                "    Select a movie you would like to book:\n" +
                "    1. The Shawshank Redemption\n" +
                "    2. Back to home page\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestPickBookingTime_invalidDataType() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\nasd\n" + (movies.get(0).getUpcomingTimes().size() + 1) + "\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.pickBookingTime(scanner, 0);

        String expected = "Select a time to book for the movie:\n" +
                "    1. 10:45\n" +
                "    2. 14:00\n" +
                "    3. Back to home page\n" +
                "    Error: Not a valid option.\n" +
                "\n" +
                "    Select a time to book for the movie:\n" +
                "    1. 10:45\n" +
                "    2. 14:00\n" +
                "    3. Back to home page\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestPickBookingTime_invalidPositiveNumber() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\n100\n" + (movies.get(0).getUpcomingTimes().size() + 1) + "\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.pickBookingTime(scanner, 0);

        String expected = "Select a time to book for the movie:\n" +
                "    1. 10:45\n" +
                "    2. 14:00\n" +
                "    3. Back to home page\n" +
                "    Error: Not a valid option.\n" +
                "\n" +
                "    Select a time to book for the movie:\n" +
                "    1. 10:45\n" +
                "    2. 14:00\n" +
                "    3. Back to home page\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestPickBookingTime_invalidNegativeNumber() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\n-100\n" + (movies.get(0).getUpcomingTimes().size() + 1) + "\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.pickBookingTime(scanner, 0);

        String expected = "Select a time to book for the movie:\n" +
                "    1. 10:45\n" +
                "    2. 14:00\n" +
                "    3. Back to home page\n" +
                "    Error: Not a valid option.\n" +
                "\n" +
                "    Select a time to book for the movie:\n" +
                "    1. 10:45\n" +
                "    2. 14:00\n" +
                "    3. Back to home page\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestBookingNumOfSeats_invalidNegativeNumber() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\n-100\ncancel\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.bookingNumOfSeats(scanner, movies.get(0), 0);

        String expected = "Enter the number of seats you would like book.\n" +
                "    (Required to enter for all options. Split by comma. E.g. 0,0,1,1)\n" +
                "    Child (under 12):\n" +
                "    Student:\n" +
                "    Adult:\n" +
                "    Senior/Pensioner:\n" +
                "    (Enter 'cancel' to return to the home page)\n" +
                "    Error: Invalid option entered.\n" +
                "\n" +
                "    Enter the number of seats you would like book.\n" +
                "    (Required to enter for all options. Split by comma. E.g. 0,0,1,1)\n" +
                "    Child (under 12):\n" +
                "    Student:\n" +
                "    Adult:\n" +
                "    Senior/Pensioner:\n" +
                "    (Enter 'cancel' to return to the home page)\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestBookingNumOfSeats_invalidString() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\nasdf\ncancel\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.bookingNumOfSeats(scanner, movies.get(0), 0);

        String expected = "Enter the number of seats you would like book.\n" +
                "    (Required to enter for all options. Split by comma. E.g. 0,0,1,1)\n" +
                "    Child (under 12):\n" +
                "    Student:\n" +
                "    Adult:\n" +
                "    Senior/Pensioner:\n" +
                "    (Enter 'cancel' to return to the home page)\n" +
                "    Error: Invalid option entered.\n" +
                "\n" +
                "    Enter the number of seats you would like book.\n" +
                "    (Required to enter for all options. Split by comma. E.g. 0,0,1,1)\n" +
                "    Child (under 12):\n" +
                "    Student:\n" +
                "    Adult:\n" +
                "    Senior/Pensioner:\n" +
                "    (Enter 'cancel' to return to the home page)\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestBookingNumOfSeats_NotEnoughValues() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\n1,0,0\ncancel\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.bookingNumOfSeats(scanner, movies.get(0), 0);

        String expected = "Enter the number of seats you would like book.\n" +
                "    (Required to enter for all options. Split by comma. E.g. 0,0,1,1)\n" +
                "    Child (under 12):\n" +
                "    Student:\n" +
                "    Adult:\n" +
                "    Senior/Pensioner:\n" +
                "    (Enter 'cancel' to return to the home page)\n" +
                "    Error: Invalid option entered.\n" +
                "\n" +
                "    Enter the number of seats you would like book.\n" +
                "    (Required to enter for all options. Split by comma. E.g. 0,0,1,1)\n" +
                "    Child (under 12):\n" +
                "    Student:\n" +
                "    Adult:\n" +
                "    Senior/Pensioner:\n" +
                "    (Enter 'cancel' to return to the home page)\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestBookingNumOfSeats_NoSeats() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\n0,0,0,0\ncancel\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.bookingNumOfSeats(scanner, movies.get(0), 0);

        String expected = "Enter the number of seats you would like book.\n" +
                "    (Required to enter for all options. Split by comma. E.g. 0,0,1,1)\n" +
                "    Child (under 12):\n" +
                "    Student:\n" +
                "    Adult:\n" +
                "    Senior/Pensioner:\n" +
                "    (Enter 'cancel' to return to the home page)\n" +
                "    Error: Invalid option entered.\n" +
                "\n" +
                "    Enter the number of seats you would like book.\n" +
                "    (Required to enter for all options. Split by comma. E.g. 0,0,1,1)\n" +
                "    Child (under 12):\n" +
                "    Student:\n" +
                "    Adult:\n" +
                "    Senior/Pensioner:\n" +
                "    (Enter 'cancel' to return to the home page)\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestPickBookingSeat_InvalidDataType() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\nasd\n4\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.pickBookingSeat(scanner, movies.get(0), 0, 1);

        String expected = "Select the seat you would like:\n" +
                "    1. Front\n" +
                "    2. Middle\n" +
                "    3. Rear\n" +
                "    4. Back to home page\n" +
                "    Error: Not a valid option.\n" +
                "\n" +
                "    Select the seat you would like:\n" +
                "    1. Front\n" +
                "    2. Middle\n" +
                "    3. Rear\n" +
                "    4. Back to home page\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestPickBookingSeat_InvalidNegativeNumber() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\n-10\n4\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.pickBookingSeat(scanner, movies.get(0), 0, 1);

        String expected = "Select the seat you would like:\n" +
                "    1. Front\n" +
                "    2. Middle\n" +
                "    3. Rear\n" +
                "    4. Back to home page\n" +
                "    Error: Not a valid option.\n" +
                "\n" +
                "    Select the seat you would like:\n" +
                "    1. Front\n" +
                "    2. Middle\n" +
                "    3. Rear\n" +
                "    4. Back to home page\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestPickBookingSeat_InvalidPositiveNumber() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\n10\n4\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.pickBookingSeat(scanner, movies.get(0), 0, 1);

        String expected = "Select the seat you would like:\n" +
                "    1. Front\n" +
                "    2. Middle\n" +
                "    3. Rear\n" +
                "    4. Back to home page\n" +
                "    Error: Not a valid option.\n" +
                "\n" +
                "    Select the seat you would like:\n" +
                "    1. Front\n" +
                "    2. Middle\n" +
                "    3. Rear\n" +
                "    4. Back to home page\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }

    @Test
    public void TestPickBookingSeat_FrontNotEnoughSeats() throws FileNotFoundException {
        List<Movie> movies = cinema_1_movie.getMovies();
        Account account = new Account("testing", "testing", 0);
        cinema_1_movie.setCurrAcc(account);
        cinema_1_movie.setLogged(true);
        cinema_1_movie.createGiftCards();

        Scanner scanner = new Scanner("\n1\n4\n");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema_1_movie.pickBookingSeat(scanner, movies.get(0), 0, 100);

        String expected = "Select the seat you would like:\n" +
                "    1. Front\n" +
                "    2. Middle\n" +
                "    3. Rear\n" +
                "    4. Back to home page\n" +
                "    There aren't enough seats in this row.\n" +
                "\n" +
                "    Select the seat you would like:\n" +
                "    1. Front\n" +
                "    2. Middle\n" +
                "    3. Rear\n" +
                "    4. Back to home page\n" +
                "    Returning back to customer home page.";

        String[] output = outputStream.toString().trim().split("\n");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
        }
        String actual = String.join("\n", output);

        String[] expectedArr = expected.trim().split("\n");
        for (int i = 0; i < expectedArr.length; i++) {
            expectedArr[i] = expectedArr[i].trim();
        }
        expected = String.join("\n", expectedArr);

        assertEquals(expected, actual);
    }
}
