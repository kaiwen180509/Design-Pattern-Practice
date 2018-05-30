package target;

public class HDMI implements IHDMI {
    @Override
    public void videoSignal() {
        System.out.println("傳輸視訊畫面...");
    }

    @Override
    public void voiceSignal() {
        System.out.println("傳輸音訊效果...");
    }
}
