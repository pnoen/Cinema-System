public class GiftCard {
    private String number;
    private int amount;
    private int redeemed;

    public GiftCard(String number, int amount, int redeemed) {
        this.number = number;
        this.amount = amount;
        this.redeemed = redeemed;
    }

    public String getNumber() {
        return number;
    }

    public int getAmount() {
        return amount;
    }

    public int isRedeemed() {
        return redeemed;
    }
}
