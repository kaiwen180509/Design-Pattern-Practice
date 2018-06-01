package subsystem;

public class WhiteCase implements Chef {
    @Override
    public void prepareMaterial() {
        System.out.println("白案廚師準備材料...");
    }

    @Override
    public void make(String name) {
        System.out.println("白案廚師開始製作" + name);
    }
}
