package composite;

import component.Component;

import java.util.ArrayList;

public class SalesArea extends Component {
    private ArrayList<Component> list = new ArrayList<>();

    public SalesArea(String name) {
        super(name);
    }

    @Override
    public String getInfo() {
        return this.name + " 販售區";
    }

    public void add(Component product) {
        product.setParent(this);
        this.list.add(product);
    }

    public void remove(Component product) {
        this.list.remove(product);
    }

    public ArrayList<Component> getComponents() {
        return this.list;
    }
}
