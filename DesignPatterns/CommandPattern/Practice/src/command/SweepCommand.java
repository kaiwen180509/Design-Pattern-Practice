package command;

import receiver.Reboot;

public class SweepCommand implements Command {
    private Reboot reboot;

    public SweepCommand(Reboot reboot) {
        this.reboot = reboot;
    }

    @Override
    public void execute() {
        System.out.print("執行：");
        reboot.sweep();
    }

    @Override
    public void undo() {
        System.out.print("撤銷：");
        reboot.unSweep();
    }
}
