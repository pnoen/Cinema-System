import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class PasswordMasker {

    public static String readPassword (String asker) {
        Masker masker = new Masker(asker);
        Thread m = new Thread(masker);
        m.start();

        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        String pass = "";

        try {
            pass = inp.readLine();
        }
        catch (IOException e) {
            System.out.println("Error: input not read");
        }
        masker.stopMasking();
        return pass;
    }
}

class Masker implements Runnable {
    private Boolean isMasking;

    public Masker (String word) {
        System.out.print(word);
    }

    public void run () {
        this.isMasking = true;
        while (isMasking) {
            System.out.print("\010*");
            try {
                Thread.currentThread().sleep(1);
            }
            catch (InterruptedException e) {
                System.out.println("Error: Masking failed");
            }
        }
    }

    public void stopMasking () {
        this.isMasking = false;
    }
}