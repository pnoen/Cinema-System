public class Account {
    private String username;
    private String password;
    private String cardNumber = null;
    private int cardPin = -1;
    private String perms;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.perms = perms;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPerms(String whichRole) {
        this.perms = whichRole;
    }

    public String getPerms(){
        return perms;
    }
}
