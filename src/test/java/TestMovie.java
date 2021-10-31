import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestMovie {
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
        movie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms, allSeats);
    }

    @Test
    public void testGetScreenSize() {
        assertEquals(movie.getScreenSize(), "Bronze");
    }

    @Test
    public void testGetCinemaRooms() {
        assertEquals(movie.getCinemaRooms().get(0), "1");
        assertEquals(movie.getCinemaRooms().get(1), "2");
    }

    @Test
    public void testSetName() {
        movie.setName("Test");
        assertEquals(movie.getName(), "Test");
    }

    @Test
    public void testSynopsis() {
        movie.setSynopsis("Test");
        assertEquals(movie.getSynopsis(), "Test");
    }

    @Test
    public void testSetClassification() {
        movie.setClassification("PG");
        assertEquals(movie.getClassification(), "PG");
    }

    @Test
    public void testSetDirector() {
        movie.setDirector("Test");
        assertEquals(movie.getDirector(), "Test");
    }

    @Test
    public void testSetCast() {
        movie.setCast("Test");
        assertEquals(movie.getCast(), "Test");
    }

    @Test
    public void testSetScreenSize() {
        movie.setScreenSize("Silver");
        assertEquals(movie.getScreenSize(), "Silver");
    }

    @Test
    public void testSetReleaseDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = sdf.parse("28/09/2021");
        movie.setReleaseDate("28/09/2021");
        assertEquals(movie.getReleaseDate(), date);
    }

    @Test
    public void testSetSeats_Invalid() {
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
        seats.add(0);
        seats.add(1);
        seats.add(1);
        allSeats.add(seats);
        allSeats.add(seats);
        Movie emptySeatMovie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms, allSeats);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        emptySeatMovie.setSeats(0, 0, 1);

        String expected = "There are no available seats in this row.\n";

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
