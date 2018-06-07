package flyweight;

/* Flyweight */
public abstract class Cup {
    // 內部狀態 -> 容量
    private int capacity;

    // 外部狀態 -> 飲料名稱
    protected final String name;

    public Cup(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    // 杯子的行為
    public abstract void drink();
}
