import director.ToyBrickFactoryDirector;

public class Client {
    public static void main(String[] args) {
        // 建立工廠指揮者
        ToyBrickFactoryDirector factoryDirector = new ToyBrickFactoryDirector();

        // 開始製造積木
        System.out.println("----------正方形積木製作----------");
        factoryDirector.getSquareToyBrick().make();
        System.out.println("----------長方形積木製作----------");
        factoryDirector.getRectangleToyBrick().make();
        System.out.println("----------三角形積木製作----------");
        factoryDirector.getTriangleToyBrick().make();
        System.out.println("----------圓柱形積木製作----------");
        factoryDirector.getCylinderToyBrick().make();
    }
}