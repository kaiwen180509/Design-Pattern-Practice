package adapter;

import adaptee.DisplayPort;
import target.IHDMI;

public class DisplayPortAdapter extends DisplayPort implements IHDMI {
    @Override
    public void videoSignal() {
        System.out.print("DisplayPort 介面卡進行轉換...");
        super.transportVideoSignal();
    }

    @Override
    public void voiceSignal() {
        System.out.print("DisplayPort 介面卡進行轉換...");
        super.transportVoiceSignal();
    }
}
