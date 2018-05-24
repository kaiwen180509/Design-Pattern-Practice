package builder;

import product.SquareToyBrick;
import product.ToyBrick;

import java.util.ArrayList;

public class SquareBuilder extends ToyBrickBuilder {
    private ToyBrick toyBrick = new SquareToyBrick();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.toyBrick.setSequence(sequence);
    }

    @Override
    public ToyBrick getToyBrick() {
        return this.toyBrick;
    }
}