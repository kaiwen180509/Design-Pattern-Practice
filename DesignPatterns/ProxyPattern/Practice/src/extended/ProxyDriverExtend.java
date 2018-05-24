package extended;

import normal.Driver;
import normal.IDriver;

public class ProxyDriverExtend implements IDriver, IProxy {
    private Driver driver;

    @Override
    public void fee() {
        System.out.println("代理司機費用為：1200 元");
    }

    public ProxyDriverExtend(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void board() {
        prepare();
        this.driver.board();
    }

    @Override
    public void leave() {
        this.driver.leave();
        after();
    }

    @Override
    public void drive() {
        before();
        this.driver.drive();
    }

    @Override
    public void park() {
        before();
        this.driver.park();
    }

    @Override
    public void horn() {
        before();
        this.driver.horn();
    }

    // 準備工作
    private void prepare() {
        System.out.println("代理司機準備代替駕駛");
    }

    // 預處理
    private void before() {
        System.out.print("代理司機代替");
    }

    // 後處理
    private void after() {
        System.out.println("代理司機離開");
    }
}
