package abstractfactory;

import product.*;

public class MatchaDessertFactory implements DessertFactory {
    @Override
    public Candy makeCandy() {
        return new MatchaCandy();
    }

    @Override
    public Jelly makeJelly() {
        return new MatchaJelly();
    }
}
