import colleague.Apprentice;
import colleague.Chef;
import colleague.Waiter;
import mediator.ConcreteMediator;
import mediator.Mediator;

public class Client {
    public static void main(String[] args) {
        // 餐廳有一個中介者
        Mediator mediator = new ConcreteMediator();
        // 廚師、學徒與服務生都知道中介者，且願意聽從指揮
        Chef chef = new Chef(mediator);
        Apprentice apprentice = new Apprentice(mediator);
        Waiter waiter = new Waiter(mediator);
        // 讓中介者知道廚師、學徒與服務生是誰
        mediator.setChef(chef);
        mediator.setApprentice(apprentice);
        mediator.setWaiter(waiter);

        // 服務生接到客人的訂單
        waiter.order();
    }
}