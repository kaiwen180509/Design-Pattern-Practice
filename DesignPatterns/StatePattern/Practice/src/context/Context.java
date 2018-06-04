package context;

import state.IceState;
import state.State;
import state.SteamState;
import state.WaterState;

public class Context {
    // 定義各種狀態
    public final static WaterState WATER_STATE = new WaterState();
    public final static IceState ICE_STATE = new IceState();
    public final static SteamState STEAM_STATE = new SteamState();
    // 定義目前的狀態
    private State currentState;

    // 設定目前的狀態
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
        this.currentState.setContext(this);
    }

    // 加熱
    public void heating() {
        System.out.print("加熱...");
        this.currentState.heating();
    }

    // 冷卻
    public void cooling() {
        System.out.print("冷卻...");
        this.currentState.cooling();
    }

    // 取得狀態
    public void getState() {
        this.currentState.getState();
    }
}
