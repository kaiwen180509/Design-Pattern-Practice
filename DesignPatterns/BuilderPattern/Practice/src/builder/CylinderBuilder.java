package builder;

import product.CylinderToyBrick;
import product.ToyBrick;

import java.util.ArrayList;

public class CylinderBuilder extends ToyBrickBuilder {
    private ToyBrick toyBrick = new CylinderToyBrick();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.toyBrick.setSequence(sequence);
    }

    @Override
    public ToyBrick getToyBrick() {
        return this.toyBrick;
    }
}