package prototype;

public class Cake implements Cloneable {
    private String flavor;

    public Cake() {
        System.out.println("製作蛋糕原型要90分鐘");
    }

    @Override
    protected Cake clone() {
        Cake cake = null;
        try {
            cake = (Cake) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cake;
    }

    // 設定蛋糕的味道
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    // 取得蛋糕的味道
    public String getFlavor() {
        return this.flavor + "口味";
    }
}
