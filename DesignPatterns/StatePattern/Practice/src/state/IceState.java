package state;

import context.Context;

public class IceState extends State {
    @Override
    public void heating() {
        // 設定下一個狀態並且查看狀態
        this.context.setCurrentState(Context.WATER_STATE);
        this.context.getState();
    }

    @Override
    public void cooling() {
        // 冰不會繼續冷卻了
        System.out.println("冰沒有反應");
    }

    @Override
    public void getState() {
        System.out.println("冰");
    }
}
