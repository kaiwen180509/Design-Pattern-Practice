package decorator;

import component.Pen;

public abstract class Decorator extends Pen {
    private Pen pen;

    // 建構子負責傳遞筆
    public Decorator(Pen pen) {
        this.pen = pen;
    }

    @Override
    public void write() {
        this.pen.write();
    }
}
