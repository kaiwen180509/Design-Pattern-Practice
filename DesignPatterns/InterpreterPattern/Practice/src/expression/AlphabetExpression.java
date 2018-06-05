package expression;

/* NonterminalExpression */
public class AlphabetExpression extends Expression {
    // 明文
    private final String plaintext = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // 密文
    private final String Ciphertext = "CRAZYBDEFGHIJKLMNOPQSTUVWX";

    @Override
    public String interpreter(String input) {
        return decryption(input);
    }

    // 解密
    private String decryption(String input) {
        String result;
        int index;
        boolean isLower = false;

        // 判斷原輸入是否為小寫
        if (Character.isLowerCase(input.charAt(0))) {
            isLower = true;
        }

        // 找出對應的位置並取得明文
        index = Ciphertext.indexOf(input.toUpperCase());
        result = plaintext.charAt(index) + "";

        // 原輸入是小寫，因此轉換輸出為小寫
        if (isLower) {
            return result.toLowerCase();
        }
        return result;
    }
}
