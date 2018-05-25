package mediator;

public class ConcreteMediator extends Mediator {
    public static final String HOT_POT = "HOT_POT";
    public static final String COOK = "COOK";
    public static final String PREPARE = "PREPARE";
    public static final String SERVE = "SERVE";
    public static final String WASHING = "WASHING";

    @Override
    public void execute(String key, Object... objects) {
        if (key.equals(HOT_POT)) {
            doHotPot();
        }
        if (key.equals(COOK)) {
            doCook();
        }
        if (key.equals(PREPARE)) {
            doPrepare();
        }
        if (key.equals(SERVE)) {
            doServe();
        }
        if (key.equals(WASHING)) {
            doWashing();
        }
    }

    private void doHotPot() {
        getChef().hotPot();
    }

    private void doCook() {
        getChef().cook();
    }

    private void doPrepare() {
        getApprentice().prepare();
    }

    private void doServe() {
        getWaiter().serve();
    }

    private void doWashing() {
        getApprentice().washing();
    }
}
