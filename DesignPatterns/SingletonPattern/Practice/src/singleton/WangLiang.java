package singleton;

public class WangLiang {
    private static volatile WangLiang INSTANCE = null;

    private WangLiang() {
    }

    public static WangLiang getInstance() {
        if (INSTANCE == null) {
            // 雙重檢查鎖
            synchronized (WangLiang.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WangLiang();
                }
            }
        }
        return INSTANCE;
    }

    public static void send(ILetter letter) {
        System.out.println("自 " + letter.getSender() + " 送到 " + letter.getRecipientAddress() + " 的 " + letter.getRecipient() + " 手上");
    }
}
