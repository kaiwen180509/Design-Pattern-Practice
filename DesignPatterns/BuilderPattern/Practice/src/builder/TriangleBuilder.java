package builder;

import product.ToyBrick;
import product.TriangleToyBrick;

import java.util.ArrayList;

public class TriangleBuilder extends ToyBrickBuilder {
    private ToyBrick toyBrick = new TriangleToyBrick();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.toyBrick.setSequence(sequence);
    }

    @Override
    public ToyBrick getToyBrick() {
        return this.toyBrick;
    }
}
