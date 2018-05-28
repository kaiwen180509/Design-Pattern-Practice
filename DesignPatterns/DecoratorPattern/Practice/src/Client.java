import component.MechanicalPencil;
import component.Pen;
import decorator.RefillLeadDecorator;
import decorator.ReplaceEraserDecorator;

public class Client {
    public static void main(String[] args) {
        // 有一隻自動鉛筆
        Pen pen = new MechanicalPencil();
        System.out.println("-----修飾前-----");
        // 用自動鉛筆寫字
        pen.write();

        System.out.println("-----修飾後-----");
        // 裝上新的筆芯與橡皮擦再繼續寫
        pen = new ReplaceEraserDecorator(pen);
        pen = new RefillLeadDecorator(pen);
        pen.write();
    }
}