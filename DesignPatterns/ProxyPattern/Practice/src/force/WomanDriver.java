package force;

public class WomanDriver implements IWomanDriver {
    private String name;
    private IWomanDriver proxy = null;

    public WomanDriver(String name) {
        this.name = name;
    }

    @Override
    public void board() {
        if (this.isProxy()) {
            System.out.println(this.name + "上車");
        } else {
            System.out.println("請讓指定的代理來");
        }
    }

    @Override
    public void leave() {
        if (this.isProxy()) {
            System.out.println(this.name + "下車");
        } else {
            System.out.println("請讓指定的代理來");
        }
    }

    @Override
    public void drive() {
        if (this.isProxy()) {
            System.out.println(this.name + "在開車");
        } else {
            System.out.println("請讓指定的代理來");
        }
    }

    @Override
    public void park() {
        if (this.isProxy()) {
            System.out.println(this.name + "在停車");
        } else {
            System.out.println("請讓指定的代理來");
        }
    }

    @Override
    public void horn() {
        if (this.isProxy()) {
            System.out.println(this.name + "按喇叭");
        } else {
            System.out.println("請讓指定的代理來");
        }
    }

    @Override
    public IWomanDriver getProxy() {
        this.proxy = new ProxyWomanDriver(this);
        return this.proxy;
    }

    // 檢查是不是代理
    private boolean isProxy() {
        if (this.proxy == null) {
            return false;
        }
        return true;
    }
}
