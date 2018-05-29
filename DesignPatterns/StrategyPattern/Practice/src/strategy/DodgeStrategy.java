package strategy;

public class DodgeStrategy implements Strategy{
    @Override
    public void execute() {
        System.out.println("進行躲避行動...");
    }
}
