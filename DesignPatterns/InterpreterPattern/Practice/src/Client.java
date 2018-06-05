import expression.*;

public class Client {
    public static void main(String[] args) {
        // I love you!! 的密文
        Context context = new Context("F ilty wls!!");

        // 交給直譯器處理
        Expression expression;
        for (char input : context.getInput().toCharArray()) {
            // 判斷需要哪個規則來解析
            if (Character.isLetter(input)) {
                expression = new AlphabetExpression();
            } else {
                expression = new TerminalExpression();
            }
            // 開始解析
            System.out.print(expression.interpreter("" + input));
        }
        System.out.println();
        System.out.println("轉換完成");
    }
}