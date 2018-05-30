package adaptee;

public class SoundSourceLine implements ISoundSourceLine{

    @Override
    public void voiceSignal() {
        System.out.println("傳輸音訊效果...");
    }
}
