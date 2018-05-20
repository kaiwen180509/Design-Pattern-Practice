package singleton;

public class WangLiangHungry {
    private final static WangLiangHungry INSTANCE = new WangLiangHungry();

    private WangLiangHungry() {
    }

    public static WangLiangHungry getInstance() {
        return INSTANCE;
    }

    public static void send(ILetter letter) {
        System.out.println("自 " + letter.getSender() + " 送到 " + letter.getRecipientAddress() + " 的 " + letter.getRecipient() + " 手上");
    }
}
