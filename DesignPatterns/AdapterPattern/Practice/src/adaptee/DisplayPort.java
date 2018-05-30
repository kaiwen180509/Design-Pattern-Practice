package adaptee;

public class DisplayPort implements IDisplayPort {
    @Override
    public void transportVideoSignal() {
        System.out.println("傳輸視訊畫面...");
    }

    @Override
    public void transportVoiceSignal() {
        System.out.println("傳輸音訊效果...");
    }

    @Override
    public void transportUSB() {
        System.out.println("傳輸USB資料...");
    }
}
