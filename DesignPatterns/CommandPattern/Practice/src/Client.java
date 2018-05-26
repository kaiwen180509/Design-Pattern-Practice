import command.DanceCommand;
import command.SingCommand;
import command.SweepCommand;
import invoker.Host;
import receiver.Reboot;

public class Client {
    public static void main(String[] args) {
        // 有一個主人跟機器人
        Host host = new Host();
        Reboot reboot = new Reboot();

        // 主人要機器人唱歌跳舞
        host.action(new SingCommand(reboot));
        host.action(new DanceCommand(reboot));

        // 主人要機器人掃地
        host.action(new SweepCommand(reboot));
        // 主人取消命令
        host.cancel();
    }
}