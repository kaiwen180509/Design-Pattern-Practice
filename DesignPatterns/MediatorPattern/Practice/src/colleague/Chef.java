package colleague;

import mediator.ConcreteMediator;
import mediator.Mediator;

public class Chef extends Colleague {
    public Chef(Mediator mediator) {
        super(mediator);
    }

    // 廚師預先熱鍋
    public void hotPot() {
        System.out.println("廚師開始熱鍋...");
    }

    // 廚師下廚
    public void cook() {
        System.out.println("廚師開始下廚...煮好食物了");
        mediator.execute(ConcreteMediator.SERVE);
        mediator.execute(ConcreteMediator.WASHING);
    }
}
