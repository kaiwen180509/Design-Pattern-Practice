package extended;

public abstract class PhotographerExtend {
    // 調整光圈
    protected abstract void adjustAperture();

    // 設定感光度
    protected abstract void setISO();

    // 設定快門速度
    protected abstract void setShutterSpeed();

    // 設定倒數計時
    protected abstract void setTimer();

    // 擴展的掛鉤方法，預設：True
    protected boolean isTimer() {
        return true;
    }

    // 對焦
    protected abstract void focusLens();

    // 按快門
    protected abstract void pressShutter();

    // 拍照
    final public void snap() {
        this.adjustAperture();
        this.setISO();
        this.setShutterSpeed();
        if (isTimer()) {
            this.setTimer();
        }
        this.focusLens();
        this.pressShutter();
    }
}
