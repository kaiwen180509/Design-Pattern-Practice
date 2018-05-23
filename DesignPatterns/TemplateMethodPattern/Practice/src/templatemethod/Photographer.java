package templatemethod;

public abstract class Photographer {
    // 調整光圈
    protected abstract void adjustAperture();

    // 設定感光度
    protected abstract void setISO();

    // 設定快門速度
    protected abstract void setShutterSpeed();

    // 設定倒數計時
    protected abstract void setTimer();

    // 對焦
    protected abstract void focusLens();

    // 按快門
    protected abstract void pressShutter();

    // 拍照
    final public void snap() {
        this.adjustAperture();
        this.setISO();
        this.setShutterSpeed();
        this.setTimer();
        this.focusLens();
        this.pressShutter();
    }
}
