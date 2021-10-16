public class GiftCard {
    private String number;
    private boolean redeemed = false;

    public GiftCard(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public boolean isRedeemed() {
        return redeemed;
    }
}
