package decorator;

import component.Pen;

public class RefillLeadDecorator extends Decorator {
    public RefillLeadDecorator(Pen pen) {
        super(pen);
    }

    // 裝上筆芯
    private void refillLead() {
        System.out.println("裝上新的筆芯...");
    }

    // 先裝上筆芯才能寫字
    @Override
    public void write() {
        refillLead();
        super.write();
    }
}
