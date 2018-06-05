package expression;

public class TerminalExpression extends Expression {
    @Override
    public String interpreter(String input) {
        return input;
    }
}
