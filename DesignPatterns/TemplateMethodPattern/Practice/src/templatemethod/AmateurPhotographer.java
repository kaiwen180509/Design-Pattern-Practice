package templatemethod;

public class AmateurPhotographer extends Photographer {
    @Override
    protected void adjustAperture() {
        System.out.println("調整光圈為：f/2.8");
    }

    @Override
    protected void setISO() {
        System.out.println("設定感光度為：ISO-200");
    }

    @Override
    protected void setShutterSpeed() {
        System.out.println("設定快門時間為：1/8s");
    }

    @Override
    protected void setTimer() {
        System.out.println("設定倒數計時為：3 秒");
    }

    @Override
    protected void focusLens() {
        System.out.println("讓鏡頭對焦");
    }

    @Override
    protected void pressShutter() {
        System.out.println("按下快門");
    }
}
