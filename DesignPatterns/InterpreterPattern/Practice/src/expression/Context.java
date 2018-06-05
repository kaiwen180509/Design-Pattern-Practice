package expression;

public class Context {
    // 需要處理的語句
    private String input;

    public Context(String input) {
        this.input = input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
