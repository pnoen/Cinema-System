import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TestAccount {

    @Test
    void testAccountConstruct(){
        Account newAccount = new Account("bob.johnson", "password1", 2);
        assertEquals(newAccount.getPassword(), "password1");
        assertEquals(newAccount.getUsername(), "bob.johnson");
        assertEquals(newAccount.getPerm(), 2);
    }


}
