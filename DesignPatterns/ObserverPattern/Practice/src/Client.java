import observer.PhotographerA;
import observer.PhotographerB;
import subject.IAnimal;
import subject.Tiger;

import java.util.Observable;
import java.util.Observer;

public class Client {
    public static void main(String[] args) {
        // 有一隻老虎
        Observable tiger = new Tiger();
        // 觀察者出現
        Observer photographerA = new PhotographerA();
        Observer photographerB = new PhotographerB();
        // 讓觀察者開始觀察被觀察者
        tiger.addObserver(photographerA);
        tiger.addObserver(photographerB);

        // 老虎開始各種行動
        IAnimal animal = (IAnimal) tiger;
        animal.drink();
        animal.hunting();
        animal.run();
    }
}