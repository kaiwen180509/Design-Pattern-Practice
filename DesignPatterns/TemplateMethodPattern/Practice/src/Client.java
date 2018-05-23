import extended.AdeptPhotographerExtend;
import templatemethod.AdeptPhotographer;
import templatemethod.AmateurPhotographer;
import templatemethod.Photographer;

public class Client {
    public static void main(String[] args) {
        System.out.println("----------模板方法模式----------");
        doTemplateMethod();
        System.out.println("----------模板方法模式擴展----------");
        doExtended();
    }

    private static void doTemplateMethod() {
        System.out.println("-----專業攝影師-----");
        Photographer adeptPhotographer = new AdeptPhotographer();
        adeptPhotographer.snap();

        System.out.println("-----新手攝影師-----");
        Photographer amateurPhotographer = new AmateurPhotographer();
        amateurPhotographer.snap();
    }

    private static void doExtended() {
        System.out.println("-----專業攝影師-----");
        AdeptPhotographerExtend adept = new AdeptPhotographerExtend();
        // 專業攝影師不需要倒數
        adept.useTimer(false);
        adept.snap();

        System.out.println("-----新手攝影師-----");
        Photographer amateurPhotographer = new AmateurPhotographer();
        // 新手攝影師需要倒數
        adept.useTimer(true);
        amateurPhotographer.snap();
    }
}