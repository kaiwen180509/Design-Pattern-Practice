package command;

import receiver.Reboot;

public class SingCommand implements Command {
    private Reboot reboot;

    public SingCommand(Reboot reboot) {
        this.reboot = reboot;
    }

    @Override
    public void execute() {
        System.out.print("執行：");
        reboot.sing();
    }

    @Override
    public void undo() {
        System.out.print("撤銷：");
        reboot.unSing();
    }
}
