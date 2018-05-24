package product;

import java.util.ArrayList;

public abstract class ToyBrick {
    // 順序列表
    private ArrayList<String> sequence = new ArrayList<>();

    // 取得原料執行關鍵字
    public final static String GET_MATERIALS = "GET_MATERIALS";
    // 雕塑形狀執行關鍵字
    public final static String SCULPTURE_SHAPE = "SCULPTURE_SHAPE";
    // 打磨光滑執行關鍵字
    public final static String GRINDING = "GRINDING";

    // 取得原料
    protected abstract void getMaterials();

    // 雕塑形狀
    protected abstract void sculptureShape();

    // 打磨光滑
    protected abstract void grinding();

    // 設定順序
    final public void setSequence(ArrayList<String> sequence) {
        this.sequence = sequence;
    }

    // 製作積木
    final public void make() {
        for (int i = 0; i < sequence.size(); i++) {
            String key = sequence.get(i);
            if (key.equals(this.GET_MATERIALS)) {
                this.getMaterials();
            } else if (key.equals(this.SCULPTURE_SHAPE)) {
                this.sculptureShape();
            } else if (key.equals(this.GRINDING)) {
                this.grinding();
            }
        }
    }
}