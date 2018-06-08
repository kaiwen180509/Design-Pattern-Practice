package abstraction;

import implementor.Container;

/* Abstraction */
public abstract class Factory {
    protected Container container;

    public Factory(Container container) {
        this.container = container;
    }

    // 工廠會生產
    public abstract void produce();

    // 工廠會回收
    public abstract void recycle();
}
