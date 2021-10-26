import java.util.ArrayList;
import java.util.List;

public class Account {
    private String username;
    private String password;
    private boolean hasCard;
//    private String cardNumber = null;
//    private int cardPin = -1;
    private int perm;
    private List<Transaction> transactions = new ArrayList<Transaction>();

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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(int ind) {
        transactions.remove(ind);
    }


    public void setHasCard(boolean hasCard){
        this.hasCard = hasCard;
    }

    public boolean getHasCard(){
        return this.hasCard;
    }
}
