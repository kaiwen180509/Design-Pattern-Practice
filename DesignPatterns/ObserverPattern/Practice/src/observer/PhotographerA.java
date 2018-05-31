package observer;

import java.util.Observable;
import java.util.Observer;

public class PhotographerA implements Observer, IPhotographer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("攝影師A觀察到變化");
        take(arg.toString());
    }

    @Override
    public void take(String action) {
        System.out.println("拍下：" + action + "的畫面");
    }
}
