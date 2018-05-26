package invoker;

import command.Command;

import java.util.ArrayList;

public class Host {
    private ArrayList<Command> commandList = new ArrayList<>();

    // 主人下的命令
    public void action(Command command) {
        this.commandList.add(command);
        command.execute();
    }

    // 主人撤銷上一個命令
    public void cancel() {
        int commandCount = this.commandList.size() - 1;
        // 檢查是否有命令
        if (commandCount > 0) {
            Command command = this.commandList.get(commandCount);
            command.undo();
        } else {
            System.out.println("請先下命令");
        }
    }
}
