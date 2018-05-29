import context.Bear;
import strategy.DodgeStrategy;
import strategy.HuntingStrategy;
import strategy.ShelterStrategy;

public class Client {
    public static void main(String[] args) {
        // 貝爾出現
        Bear bear = new Bear();

        // 在荒野中建立一個庇護所以此維生
        System.out.println("-----在連綿不絕的雨林之中-----");
        bear.setStrategy(new ShelterStrategy());
        bear.action();

        // 在荒野中碰上毒蛇需要躲避
        System.out.println("-----前面有一條劇毒的毒蛇-----");
        bear.setStrategy(new DodgeStrategy());
        bear.action();

        // 在荒野中肚子飢餓，需要狩獵食物來進食
        System.out.println("-----連續兩天沒有食物可吃-----");
        bear.setStrategy(new HuntingStrategy());
        bear.action();
    }
}