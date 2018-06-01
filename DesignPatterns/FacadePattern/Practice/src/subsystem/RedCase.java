package subsystem;

public class RedCase implements Chef {
    @Override
    public void prepareMaterial() {
        System.out.println("紅案廚師準備材料...");

    }

    @Override
    public void make(String name) {
        System.out.println("紅案廚師開始製作" + name);
    }
}
