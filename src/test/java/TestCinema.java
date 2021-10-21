import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TestCinema {
    //this cinema is a specific one made for testing
    private Cinema cinema_1_movie;
    //one below is the regular one
    private Cinema cinema;

    @BeforeEach
    public void setup() throws ParseException{
        cinema_1_movie = new Cinema();
        //reading in specific new movie csv
        cinema = new Cinema();
        File movie_file = new File("src/test/resources/movies_test.csv");
        cinema_1_movie.setMovies(movie_file);

        File accounts_file = new File("src/test/resources/accounts_test.csv");
        cinema_1_movie.setAccountsFile(accounts_file);
    }

<<<<<<< HEAD
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
                "Cinema rooms: 1, 2";

        assertEquals(expected, outputStream.toString().trim());
    }
=======
//    @Test
//    void testDisplayMovies(){
//        cinema_1_movie.getMovies();
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        cinema_1_movie.displayMovies();
//
//        String expected = "The Shawshank Redemption\n" +
//                "Classification: MA15+\n" +
//                "Synopsis: Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.\n" +
//                "Release date: Thu Feb 16 00:00:00 AEDT 1995\n" +
//                "Director: Frank Darabont\n" +
//                "Cast: Tim Robbins, Morgan Freeman, Bob Gunton\n" +
//                "Screen size: Gold\n" +
//                "Upcoming times: 10:45, 14:00\n" +
//                "Cinema rooms: 1, 2\n\n";
//
//        assertEquals(expected, outputStream.toString());
//    }
>>>>>>> 5157388 (A2-5 Finished writing test case structure for customerRegistration)



    @Test
    void testCustomerLoginLogic() {
        //Cinema cinema = new Cinema();
        assertEquals(false, cinema.getLogged());
        cinema.setLogged(true);

        String userInput = "2 5";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        //catching output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema.customerLoginLogic();

        String expected = "You have logged out";
        String[] output = outputStream.toString().split("\n");
        String actual = output[output.length-1];
        assertEquals(expected, actual.trim());


    }

//    @Test
<<<<<<< HEAD
//    void testCustomerRegistration(){
//        Cinema cinema = new Cinema();
//
=======
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
>>>>>>> 5157388 (A2-5 Finished writing test case structure for customerRegistration)
//    }

    @Test
    void badLogicInput(){
        cinema.setLogged(true);

        String userInput = "1236 5";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        //catching output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        cinema.customerLoginLogic();

        String expected = "You have logged out";
        String[] output = outputStream.toString().split("\n");
        String actual = output[output.length-1];
        assertEquals(expected, actual.trim());
    }

    @Test
    void testStaffLoginLogic(){
        cinema.setLogged(true);

        String userInput = "2 6";
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

        String userInput = "2 8";
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
        Scanner scanner = new Scanner("\n10");
        assertFalse(cinema.filterMovies(scanner));

        scanner = new Scanner("\n-3");
        assertFalse(cinema.filterMovies(scanner));

        scanner = new Scanner("\n3,800000");
        assertFalse(cinema.filterMovies(scanner));

        scanner.close();
    }





}
