package iterator;

import java.util.Vector;

public class ConcreteIterator implements Iterator {
    private Vector vector;
    // 目前的指標的位置
    public int curse = 0;

    public ConcreteIterator(Vector vector) {
        this.vector = vector;
    }

    @Override
    public Object first() {
        Object object = null;
        if (!vector.isEmpty()) {
            object = vector.get(0);
        }
        return object;
    }

    @Override
    public Object next() {
        Object object = null;
        // 判斷還有沒有元素，有就取出元素，並且指向下一個
        if (this.hasNext()) {
            object = vector.get(curse);
            curse++;
        }
        return object;
    }

    @Override
    public boolean hasNext() {
        // 判斷還有沒有元素，沒有了就把指標歸零
        if (this.curse == this.vector.size()) {
            this.curse = 0;
            return false;
        } else {
            return true;
        }
    }
}
