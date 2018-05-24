package builder;

import product.RectangleToyBrick;
import product.ToyBrick;

import java.util.ArrayList;

public class RectangleBuilder extends ToyBrickBuilder {
    private ToyBrick toyBrick = new RectangleToyBrick();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.toyBrick.setSequence(sequence);
    }

    @Override
    public ToyBrick getToyBrick() {
        return this.toyBrick;
    }
}
