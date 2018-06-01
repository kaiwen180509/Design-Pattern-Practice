package facade;

import subsystem.Chef;
import subsystem.RedCase;
import subsystem.WhiteCase;

public class Kitchen {
    // 被委託的廚師物件
    private Chef redCase = new RedCase();
    private Chef whiteCase = new WhiteCase();

    // 顧客點餐 -> 主食
    public void orderMainMeal(String name) {
        redCase.prepareMaterial();
        redCase.make(name);
    }

    // 顧客點餐 -> 點心
    public void orderDessert(String name) {
        whiteCase.prepareMaterial();
        whiteCase.make(name);
    }
}
