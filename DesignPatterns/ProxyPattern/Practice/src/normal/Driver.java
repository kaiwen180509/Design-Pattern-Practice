package normal;

public class Driver implements IDriver {
    private String name;

    public Driver(String name) {
        this.name = name;
    }

    @Override
    public void board() {
        System.out.println(this.name + "上車");
    }

    @Override
    public void leave() {
        System.out.println(this.name + "下車");
    }

    @Override
    public void drive() {
        System.out.println(this.name + "在開車");
    }

    @Override
    public void park() {
        System.out.println(this.name + "在停車");
    }

    @Override
    public void horn() {
        System.out.println(this.name + "按喇叭");
    }
}
