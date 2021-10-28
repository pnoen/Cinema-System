import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestMovie {

    @Test
    public void testGetScreenSize() {
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

        Movie movie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms, allSeats);
        assertEquals(movie.getScreenSize(), "Bronze");
    }

    @Test
    public void testGetCinemaRooms() {
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

        Movie movie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms, allSeats);
        assertEquals(movie.getCinemaRooms().get(0), "1");
        assertEquals(movie.getCinemaRooms().get(1), "2");
    }

    @Test
    public void testSetName() {
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

        Movie movie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms, allSeats);

        movie.setName("Test");
        assertEquals(movie.getName(), "Test");
    }

    @Test
    public void testSynopsis() {
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

        Movie movie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms, allSeats);

        movie.setSynopsis("Test");
        assertEquals(movie.getSynopsis(), "Test");
    }

    @Test
    public void testSetClassification() {
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

        Movie movie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms, allSeats);

        movie.setClassification("PG");
        assertEquals(movie.getClassification(), "PG");
    }

    @Test
    public void testSetDirector() {
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

        Movie movie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms, allSeats);

        movie.setDirector("Test");
        assertEquals(movie.getDirector(), "Test");
    }

    @Test
    public void testSetCast() {
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

        Movie movie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms, allSeats);

        movie.setCast("Test");
        assertEquals(movie.getCast(), "Test");
    }

    @Test
    public void testSetScreenSize() {
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

        Movie movie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms, allSeats);

        movie.setScreenSize("Silver");
        assertEquals(movie.getScreenSize(), "Silver");
    }
}
