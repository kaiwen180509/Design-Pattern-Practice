package colleague;

import mediator.ConcreteMediator;
import mediator.Mediator;

public class Apprentice extends Colleague {
    public Apprentice(Mediator mediator) {
        super(mediator);
    }

    // 學徒備料
    public void prepare() {
        System.out.println("學徒準備食材中...完成");
        mediator.execute(ConcreteMediator.COOK);
    }

    // 學徒洗盤子
    public void washing() {
        System.out.println("學徒開始洗盤子");
    }
}
