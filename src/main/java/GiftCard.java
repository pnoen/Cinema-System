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

    public int getRedeemed() {
        return redeemed;
    }
    public void setRedeemed(int redeemed){
        this.redeemed = redeemed;
    }

    public String getGCInfo(){
        return String.format("Gift card number: %s\n" +
                        "Redeemed State(0-Not Redeemed, 1-Redeemed): %s\n", this.number, this.redeemed);

    }

}
