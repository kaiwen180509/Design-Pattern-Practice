package mediator;

import colleague.Apprentice;
import colleague.Chef;
import colleague.Waiter;

public abstract class Mediator {
    // 定義同事類別
    protected Apprentice apprentice;
    protected Chef chef;
    protected Waiter waiter;

    public Apprentice getApprentice() {
        return apprentice;
    }

    public void setApprentice(Apprentice apprentice) {
        this.apprentice = apprentice;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    // 中介者的事件方法
    public abstract void execute(String key, Object... objects);

}
