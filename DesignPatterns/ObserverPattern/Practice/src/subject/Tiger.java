package subject;

import java.util.Observable;

public class Tiger extends Observable implements IAnimal {
    @Override
    public void drink() {
        System.out.println("老虎喝水...");
        // 通知觀察者
        super.setChanged();
        super.notifyObservers("老虎喝水");
    }

    @Override
    public void hunting() {
        System.out.println("老虎狩獵...");
        // 通知觀察者
        super.setChanged();
        super.notifyObservers("老虎狩獵");
    }

    @Override
    public void run() {
        System.out.println("老虎奔跑...");
        // 通知觀察者
        super.setChanged();
        super.notifyObservers("老虎奔跑");
    }
}
