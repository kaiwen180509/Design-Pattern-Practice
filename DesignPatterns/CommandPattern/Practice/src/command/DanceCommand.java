package command;

import receiver.Reboot;

public class DanceCommand implements Command {
    private Reboot reboot;

    public DanceCommand(Reboot reboot) {
        this.reboot = reboot;
    }

    @Override
    public void execute() {
        System.out.print("執行：");
        reboot.dance();
    }

    @Override
    public void undo() {
        System.out.print("撤銷：");
        reboot.unDance();
    }
}
