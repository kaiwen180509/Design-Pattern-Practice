import extended.DeepThing;
import extended.ShallowThing;
import prototype.Cake;
import prototype.Decorateur;

import java.util.ArrayList;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        System.out.println("----------一般原型模式----------");
        doNormalPrototype();
        System.out.println("----------淺拷貝與深拷貝----------");
        doExtendPrototype();
    }

    private static void doNormalPrototype() {
        // 建立蛋糕原型
        Cake cake = new Cake();

        // 給糕點師蛋糕原型，讓他製作100個巧克力蛋糕，並且好好保存
        Decorateur decorateur = new Decorateur(cake);
        ArrayList<Cake> cakes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Cake cakeClone = decorateur.makeCake("巧克力");
            cakes.add(cakeClone);
        }

        // 老闆隨機抽取5個蛋糕來檢查有沒有問題
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int number = random.nextInt(100);
            System.out.println(number + " 號蛋糕是" + cakes.get(number).getFlavor());
        }
    }

    private static void doExtendPrototype() {
        System.out.println("-----淺拷貝-----");
        // 產生一個淺拷貝原型的物件
        ShallowThing shallowThing = new ShallowThing();
        shallowThing.addContent("Hello, Shallow");
        // 複製一個淺拷貝的物件
        ShallowThing shallowThingClone = shallowThing.clone();
        shallowThingClone.addContent("Shallow world!");
        // 淺拷貝會共用私有的變數
        shallowThing.showContent();

        System.out.println("-----深拷貝-----");
        // 產生一個深拷貝原型的物件
        DeepThing deepThing = new DeepThing();
        deepThing.addContent("Hello, Deep");
        // 複製一個深拷貝的物件
        DeepThing deepThingClone = deepThing.clone();
        deepThingClone.addContent("Deep world!");
        // 深拷貝對私有變數也進行拷貝
        System.out.println("deepThing：");
        deepThing.showContent();
        System.out.println("deepThingClone：");
        deepThingClone.showContent();
    }
}