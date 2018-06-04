package state;

import context.Context;

public class SteamState extends State {
    @Override
    public void heating() {
        // 水蒸氣不會繼續加熱了
        System.out.println("水蒸氣沒有反應");
    }

    @Override
    public void cooling() {
        // 設定下一個狀態並且查看狀態
        super.context.setCurrentState(Context.WATER_STATE);
        this.context.getState();
    }

    @Override
    public void getState() {
        System.out.println("水蒸氣");
    }
}
