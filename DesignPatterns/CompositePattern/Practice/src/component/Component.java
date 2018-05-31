package component;

public abstract class Component {
    protected String name;

    private Component parent = null;

    public Component(String name) {
        this.name = name;
    }

    // 設置父節點
    public void setParent(Component parent) {
        this.parent = parent;
    }

    // 取得父節點
    public Component getParent() {
        return this.parent;
    }

    // 取得構件的資訊
    public abstract String getInfo();
}
