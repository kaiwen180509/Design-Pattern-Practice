package subsystem;

public interface Chef {
    // 廚師備料
    void prepareMaterial();

    // 廚師製作菜品
    void make(String name);
}
