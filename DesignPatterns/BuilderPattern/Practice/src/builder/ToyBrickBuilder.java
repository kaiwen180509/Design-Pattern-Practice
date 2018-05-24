package builder;

import product.ToyBrick;

import java.util.ArrayList;

public abstract class ToyBrickBuilder {
    public abstract void setSequence(ArrayList<String> sequence);

    public abstract ToyBrick getToyBrick();
}
