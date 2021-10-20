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

        Movie movie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms);
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

        Movie movie = new Movie("John", "John Smith", "G",
                release_date, "John Smith", "John Smith", upcomingTimes, "Bronze", cinemaRooms);
        assertEquals(movie.getCinemaRooms().get(0), "1");
        assertEquals(movie.getCinemaRooms().get(1), "2");
    }
}
