package extended;

import java.util.ArrayList;

public class ShallowThing implements Cloneable {
    private ArrayList<String> contentList = new ArrayList<>();

    @Override
    public ShallowThing clone() {
        ShallowThing thing = null;
        try {
            thing = (ShallowThing) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thing;
    }

    // 寫入內容
    public void addContent(String content) {
        this.contentList.add(content);
    }

    // 顯示內容
    public void showContent() {
        for (int i = 0; i < this.contentList.size(); i++) {
            System.out.println(this.contentList.get(i));
        }
    }
}
