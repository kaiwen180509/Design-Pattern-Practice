package srp;

public class Person implements IPersonBehavior, IPersonInfo {
    private String name;
    private int year = 0;
    private float height = 0f;
    private float weight = 0f;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public void walk() {
        System.out.println(name + "走路");
    }

    @Override
    public void jump() {
        System.out.println(name + "跳");
    }

    @Override
    public void eat() {
        System.out.println(name + "吃東西");
    }

    @Override
    public void sleep() {
        System.out.println(name + "睡覺");
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
