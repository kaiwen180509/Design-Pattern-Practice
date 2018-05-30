package aggregate;

import iterator.ConcreteIterator;
import iterator.Iterator;

import java.util.Vector;

public class ConcreteAggregate implements Aggregate {
    // 保存元素的容器
    private Vector vector = new Vector();

    @Override
    public void add(Object object) {
        this.vector.add(object);
    }

    @Override
    public void remove(Object object) {
        this.vector.remove(object);
    }

    @Override
    public Iterator iterator() {
        // 回傳疊代器
        return new ConcreteIterator(this.vector);
    }
}
