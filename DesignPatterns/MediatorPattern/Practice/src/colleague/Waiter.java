package colleague;

import mediator.ConcreteMediator;
import mediator.Mediator;

public class Waiter extends Colleague {
    public Waiter(Mediator mediator) {
        super(mediator);
    }

    // 顧客點單完，讓廚師準備以及學徒備料
    public void order() {
        System.out.println("顧客點餐完畢，廚師預先熱鍋，學徒去準備食材");
        mediator.execute(ConcreteMediator.HOT_POT);
        mediator.execute(ConcreteMediator.PREPARE);
    }

    // 服務生上菜
    public void serve() {
        System.out.println("服務生開始上菜");
    }
}
