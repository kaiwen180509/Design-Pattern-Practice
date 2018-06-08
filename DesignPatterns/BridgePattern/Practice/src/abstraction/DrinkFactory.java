package abstraction;

import implementor.Container;

/* RefinedAbstraction */
public class DrinkFactory extends Factory {
    public DrinkFactory(Container container) {
        super(container);
    }

    @Override
    public void produce() {
        this.container.fill();
    }

    @Override
    public void recycle() {
        this.container.pour();
    }
}
