package director;

import builder.CylinderBuilder;
import builder.RectangleBuilder;
import builder.SquareBuilder;
import builder.TriangleBuilder;
import product.ToyBrick;

import java.util.ArrayList;

public class ToyBrickFactoryDirector {
    private ArrayList<String> sequence = new ArrayList<>();
    private SquareBuilder squareBuilder = new SquareBuilder();
    private RectangleBuilder rectangleBuilder = new RectangleBuilder();
    private TriangleBuilder triangleBuilder = new TriangleBuilder();
    private CylinderBuilder cylinderBuilder = new CylinderBuilder();

    // 取得正方形積木
    public ToyBrick getSquareToyBrick() {
        this.sequence.clear();
        // 取得原料後切割打磨
        this.sequence.add(ToyBrick.GET_MATERIALS);
        this.sequence.add(ToyBrick.SCULPTURE_SHAPE);
        this.sequence.add(ToyBrick.GRINDING);
        this.squareBuilder.setSequence(this.sequence);
        return this.squareBuilder.getToyBrick();
    }

    // 取得長方形積木
    public ToyBrick getRectangleToyBrick() {
        this.sequence.clear();
        // 取得的原料就是長方形，所以不用切割，直接打磨
        this.sequence.add(ToyBrick.GET_MATERIALS);
        this.sequence.add(ToyBrick.GRINDING);
        this.rectangleBuilder.setSequence(this.sequence);
        return this.rectangleBuilder.getToyBrick();
    }

    // 取得三角形積木
    public ToyBrick getTriangleToyBrick() {
        this.sequence.clear();
        // 取得原料後切割打磨
        this.sequence.add(ToyBrick.GET_MATERIALS);
        this.sequence.add(ToyBrick.SCULPTURE_SHAPE);
        this.sequence.add(ToyBrick.GRINDING);
        this.triangleBuilder.setSequence(this.sequence);
        return this.triangleBuilder.getToyBrick();
    }

    // 取得圓柱形積木
    public ToyBrick getCylinderToyBrick() {
        this.sequence.clear();
        // 取得原料後切割打磨
        this.sequence.add(ToyBrick.GET_MATERIALS);
        this.sequence.add(ToyBrick.SCULPTURE_SHAPE);
        this.sequence.add(ToyBrick.GRINDING);
        this.cylinderBuilder.setSequence(this.sequence);
        return this.cylinderBuilder.getToyBrick();
    }
}
