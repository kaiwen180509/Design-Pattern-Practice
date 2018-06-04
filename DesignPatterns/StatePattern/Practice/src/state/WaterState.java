package state;

import context.Context;

public class WaterState extends State {
    @Override
    public void heating() {
        // 設定下一個狀態並且查看狀態
        this.context.setCurrentState(Context.STEAM_STATE);
        this.context.getState();
    }

    @Override
    public void cooling() {
        // 設定下一個狀態並且查看狀態
        this.context.setCurrentState(Context.ICE_STATE);
        this.context.getState();
    }

    @Override
    public void getState() {
        System.out.println("水");
    }
}
