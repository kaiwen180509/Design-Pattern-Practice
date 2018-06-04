import context.Context;
import state.WaterState;

public class Client {
    public static void main(String[] args) {
        // 定義環境並且預設水是液態
        System.out.println("-----水-----");
        Context context = new Context();
        context.setCurrentState(new WaterState());

        // 進行各種動作
        context.heating();
        context.cooling();
        context.cooling();
        context.cooling();
        context.heating();
    }
}