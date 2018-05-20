import singleton.ILetter;
import singleton.Letter;
import singleton.WangLiang;
import singleton.WangLiangHungry;

public class Client {
    public static void main(String[] args) {
        // 建立一封信
        ILetter letter = new Letter();
        letter.setSender("齊宣公姜積");
        letter.setRecipient("楚惠王熊章");
        letter.setRecipientAddress("楚國王宮");

        System.out.println("----------送信-懶漢方式----------");
        // 找到王良，把信給他去送
        WangLiang wangLiang = singleton.WangLiang.getInstance();
        wangLiang.send(letter);

        System.out.println("----------送信-餓漢方式----------");
        // 找到王良，把信給他去送
        WangLiangHungry wangLiang2 = WangLiangHungry.getInstance();
        wangLiang2.send(letter);
    }
}