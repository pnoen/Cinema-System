public class GiftCard {
    private String number;

    private int redeemed;

    public GiftCard(String number, int redeemed) {
        this.number = number;

        this.redeemed = redeemed;
    }

    public String getNumber() {
        return number;
    }

    public int isRedeemed() {
        return redeemed;
    }
}
