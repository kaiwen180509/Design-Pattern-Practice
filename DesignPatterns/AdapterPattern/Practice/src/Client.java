import adaptee.DSub;
import adaptee.SoundSourceLine;
import adapter.Adapter;
import adapter.DisplayPortAdapter;
import target.HDMI;
import target.IHDMI;

public class Client {
    public static void main(String[] args) {
        // HDMI 可以傳輸畫面與聲音
        IHDMI hdmi = new HDMI();
        hdmi.videoSignal();
        hdmi.voiceSignal();

        // 使用一個介面卡轉換 DSub 與音源線為 HDMI
        IHDMI newHdmi = new Adapter(new DSub(), new SoundSourceLine());
        newHdmi.videoSignal();
        newHdmi.voiceSignal();

        // 使用 DisplayPort 介面卡傳換為 HDMI
        IHDMI newHdmi2 = new DisplayPortAdapter();
        newHdmi2.videoSignal();
        newHdmi2.voiceSignal();
    }
}