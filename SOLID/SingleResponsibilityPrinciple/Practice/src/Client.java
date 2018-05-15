public class Client {
    public static void main(String[] args) {
        Person jhon = new Person("Jhon");
        // Jhon 走路
        jhon.walk();
        // Jhon 改名為 Jhon2
        jhon.setName("Jhon2");
        // Jhon2 開始動作
        jhon.jump();
        jhon.eat();
        jhon.sleep();
    }
}
