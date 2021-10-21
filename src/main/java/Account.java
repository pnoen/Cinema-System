public class Account {
    private String username;
    private String password;
//    private String cardNumber = null;
//    private int cardPin = -1;
    private int perm;

    public Account(String username, String password, int perm) {
        this.username = username;
        this.password = password;
        this.perm = perm;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPerm(){
        return perm;
    }
}
