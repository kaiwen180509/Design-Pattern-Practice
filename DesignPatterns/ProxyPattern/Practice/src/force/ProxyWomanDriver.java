package force;

public class ProxyWomanDriver implements IWomanDriver {
    private WomanDriver womanDriver = null;

    public ProxyWomanDriver(WomanDriver womanDriver) {
        this.womanDriver = womanDriver;
    }

    @Override
    public void board() {
        prepare();
        this.womanDriver.board();
    }

    @Override
    public void leave() {
        this.womanDriver.leave();
        after();
    }

    @Override
    public void drive() {
        before();
        this.womanDriver.drive();
    }

    @Override
    public void park() {
        before();
        this.womanDriver.park();
    }

    @Override
    public void horn() {
        before();
        this.womanDriver.horn();
    }

    @Override
    public IWomanDriver getProxy() {
        return this;
    }

    // 準備工作
    private void prepare() {
        System.out.println("女代理司機準備代替駕駛");
    }

    // 預處理
    private void before() {
        System.out.print("女代理司機代替");
    }

    // 後處理
    private void after() {
        System.out.println("女代理司機離開");
    }
}
