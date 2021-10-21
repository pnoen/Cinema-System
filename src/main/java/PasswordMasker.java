import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Thread;

class PasswordMasker {

    public static String readPassword (String prompt) {
        Masker masker = new Masker(prompt);
        Thread m = new Thread(masker);
        m.start();

        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        String pass = "";

        try {
            pass = inp.readLine();
            System.out.print("\b");
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

    public Masker (String prompt) {
        System.out.print(prompt);
    }

    public void run () {
        this.isMasking = true;
        while (isMasking) {
            System.out.print("\b ");
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