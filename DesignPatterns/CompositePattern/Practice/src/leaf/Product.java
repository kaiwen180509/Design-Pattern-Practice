package leaf;

import component.Component;

public class Product extends Component {
    private int price;

    public Product(String name, int price) {
        super(name);
        this.price = price;
    }

    @Override
    public String getInfo() {
        return "產品名稱：" + this.name + "  價格：" + this.price + " 元";
    }
}
