public class Account {
    private String username;
    private String password;
    private String cardNumber = null;
    private int cardPin = -1;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
