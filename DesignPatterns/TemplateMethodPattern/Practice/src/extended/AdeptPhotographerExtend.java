package extended;

public class AdeptPhotographerExtend extends PhotographerExtend {
    private boolean isTimer = true;

    @Override
    protected void adjustAperture() {
        System.out.println("調整光圈為：f/22");
    }

    @Override
    protected void setISO() {
        System.out.println("設定感光度為：ISO-3200");
    }

    @Override
    protected void setShutterSpeed() {
        System.out.println("設定快門時間為：1/125s");
    }

    @Override
    protected void setTimer() {
        System.out.println("設定倒數計時為：10 秒");
    }

    @Override
    protected void focusLens() {
        System.out.println("讓鏡頭對焦");
    }

    @Override
    protected void pressShutter() {
        System.out.println("按下快門");
    }

    @Override
    protected boolean isTimer() {
        return this.isTimer;
    }

    public void useTimer(boolean isTimer) {
        this.isTimer = isTimer;
    }
}
