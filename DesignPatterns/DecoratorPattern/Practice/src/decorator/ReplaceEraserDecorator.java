package decorator;

import component.Pen;

public class ReplaceEraserDecorator extends Decorator {
    public ReplaceEraserDecorator(Pen pen) {
        super(pen);
    }

    // 換上上橡皮擦
    private void replaceEraser() {
        System.out.println("幫筆尾換上新橡皮擦...");
    }

    // 先換上橡皮擦才開始寫字
    @Override
    public void write() {
        replaceEraser();
        super.write();
    }
}
