import dynamic.DynamicProxyDriver;
import extended.ProxyDriverExtend;
import force.ProxyWomanDriver;
import force.WomanDriver;
import normal.Driver;
import normal.IDriver;
import normal.ProxyDriver;

public class Client {
    public static void main(String[] args) {
        System.out.println("----------普通代理模式----------");
        doNormalProxy();
        System.out.println("----------代理模式擴展----------");
        doExtendProxy();
        System.out.println("----------強制代理模式----------");
        doForceProxy();
        System.out.println("----------動態代理模式----------");
        doDynamicProxy();
    }

    private static void doNormalProxy() {
        // 有一個司機三毛
        Driver sanMao = new Driver("三毛");

        // 三毛現在準備酒駕
        System.out.println("-----喝醉的三毛開車-----");
        sanMao.board();
        sanMao.horn();
        sanMao.drive();
        sanMao.horn();
        sanMao.park();
        sanMao.horn();
        System.out.println("警察臨檢，三毛好傷心");
        sanMao.leave();

        // 找一個代理司機幫三毛駕駛
        System.out.println("-----代理司機代替三毛開車-----");
        IDriver proxy = new ProxyDriver(sanMao);

        // 代理司機開始行動
        proxy.board();
        proxy.drive();
        proxy.park();
        proxy.leave();
    }

    private static void doExtendProxy() {
        // 有一個司機 Grylls，找了一個代理司機幫他駕駛
        Driver grylls = new Driver("Grylls");
        ProxyDriverExtend proxy = new ProxyDriverExtend(grylls);

        // 代理司機開始行動
        proxy.board();
        proxy.drive();
        proxy.park();
        proxy.leave();
        proxy.fee();
    }

    private static void doForceProxy() {
        // 有一個女司機小花
        WomanDriver xiaoHua = new WomanDriver("小花");

        // 小花開車
        System.out.println("-----喝醉的小花開車-----");
        xiaoHua.drive();

        // 隨便找一個女代理司機
        System.out.println("-----不熟的女代理司機開車-----");
        ProxyWomanDriver proxy = new ProxyWomanDriver(xiaoHua);
        proxy.board();

        // 找小花指定的女代理司機來
        System.out.println("-----指定的女代理司機開車-----");
        ProxyWomanDriver assignProxy = (ProxyWomanDriver) xiaoHua.getProxy();
        assignProxy.board();
        assignProxy.drive();
        assignProxy.park();
        assignProxy.leave();
    }

    private static void doDynamicProxy() {
        // 一個司機叫做 Jhon
        Driver driver = new Driver("Jhon");
        // 動態產生一個代理者
        IDriver proxy = DynamicProxyDriver.newProxyInstance(driver);

        // 代理司機開始行動
        proxy.board();
        proxy.drive();
        proxy.horn();
        proxy.park();
        proxy.leave();
    }
}