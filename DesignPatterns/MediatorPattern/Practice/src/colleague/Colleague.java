package colleague;

import mediator.Mediator;

public abstract class Colleague {
    protected Mediator mediator;

    // 通過建構子傳遞中介者
    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}
