import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestTransaction {
    private Transaction transaction;
    private Movie movie;

    @BeforeEach
    public void setup() {
        Date release_date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            release_date = sdf.parse("01/01/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String time = "10:30,21:12";
        List<String> upcomingTimes = Arrays.asList(time.split(","));

        String room = "1,2";
        List<String> cinemaRooms = Arrays.asList(room.split(","));

        List<List<Integer>> allSeats = new ArrayList<List<Integer>>();
        List<Integer> seats = new ArrayList<Integer>();
        seats.add(1);
        seats.add(1);
        seats.add(1);
        allSeats.add(seats);
        allSeats.add(seats);

        movie = new Movie("John", "Bob Smith", "G",
                release_date, "John Smith", "Smith Smith", upcomingTimes, "Bronze", cinemaRooms, allSeats);
        transaction = new Transaction("dE9i1X", movie, movie.getUpcomingTimes().get(0), "Front", 1, "");
    }

    @Test
    public void testGetID() {
        assertEquals("dE9i1X", transaction.getId());
    }

    @Test
    public void testGetMovie() {
        assertEquals(movie, transaction.getMovie());
    }

    @Test
    public void testGetMovieTime() {
        assertEquals("10:30", transaction.getMovieTime());
    }

    @Test
    public void testGetSeat() {
        assertEquals("Front", transaction.getSeat());
    }

    @Test
    public void testGetNumOfSeat() {
        assertEquals(1, transaction.getNumOfSeats());
    }

    @Test
    public void testGetCancelReason() {
        assertEquals("", transaction.getCancelReason());
    }

    @Test
    public void testGetTransactionInformation() {
        String outputStream = transaction.getTransactionInformation();

        String expected = "Transaction ID: dE9i1X\n" +
                "    Movie: John\n" +
                "    Showing time: 10:30\n" +
                "    Seat: Front\n" +
                "    Number of seats: 1";

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
    public void testSetCancelReason() {
        transaction.setCancelReason("testing");
        assertEquals("testing", transaction.getCancelReason());
    }

    @Test
    public void testSetCancelDate() {
        Date date = new Date(System.currentTimeMillis());
        transaction.setCancelDate(date);

        assertEquals(date, transaction.getCancelDate());
    }
}
