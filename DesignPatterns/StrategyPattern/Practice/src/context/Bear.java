package context;

import strategy.Strategy;

public class Bear {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void action() {
        this.strategy.execute();
    }
}
