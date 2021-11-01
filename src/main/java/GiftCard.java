public class GiftCard {
    private String number;
    private int value;

    public GiftCard(String number, int value) {
        this.number = number;

        this.value = value;
    }

    public String getNumber() {
        return number;
    }

    public int getValue() {
        return this.value;
    }
    public void setValue(int value){
        this.value = value;
    }

    public String getGCInfo(){
        return String.format("Gift card number: %s\n" +
                        "Remaining value: $%s\n", this.number, this.value);

    }

}
