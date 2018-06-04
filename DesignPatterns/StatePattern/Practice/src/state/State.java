package state;

import context.Context;

public abstract class State {
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    // 水的加熱行為
    public abstract void heating();

    // 水的冷卻行為
    public abstract void cooling();

    // 取得目前的狀態
    public abstract void getState();
}
