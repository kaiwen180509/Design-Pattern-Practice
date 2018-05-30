package adapter;

import adaptee.IDSub;
import adaptee.ISoundSourceLine;
import target.IHDMI;

public class Adapter implements IHDMI {
    private IDSub dSub;
    private ISoundSourceLine soundSourceLine;

    public Adapter(IDSub dSub, ISoundSourceLine soundSourceLine) {
        this.dSub = dSub;
        this.soundSourceLine = soundSourceLine;
    }

    @Override
    public void videoSignal() {
        System.out.print("介面卡進行轉換...");
        dSub.videoSignal();
    }

    @Override
    public void voiceSignal() {
        System.out.print("介面卡進行轉換...");
        soundSourceLine.voiceSignal();
    }
}
