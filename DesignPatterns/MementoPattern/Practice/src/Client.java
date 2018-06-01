import clone.originator.CloneGameCharacter;
import manybackup.caretaker.ManyBackupRecordManager;
import normal.caretaker.RecordManager;
import normal.originator.GameCharacter;
import safe.caretaker.SafeRecordManager;
import safe.originator.SafeGameCharacter;

public class Client {
    public static void main(String[] args) {
        System.out.println("----------一般備忘錄模式----------");
        doNormalMemento();
        System.out.println("----------Clone模式----------");
        doCloneMemento();
        System.out.println("----------多備份模式----------");
        doManyBackupMemento();
        System.out.println("----------安全封裝模式----------");
        doSafeMemento();
    }

    private static void doNormalMemento() {
        // 定義一個記錄管理員
        RecordManager recordManager = new RecordManager();
        // 有一個遊戲角色，現在10等
        System.out.println("-----最初的狀態-----");
        GameCharacter gameCharacter = new GameCharacter();
        gameCharacter.setState("Lv.10");
        System.out.println("遊戲角色等級：" + gameCharacter.getState());
        // 保存遊戲角色紀錄
        recordManager.setRecord(gameCharacter.createRecord());

        // 遊戲角色等級下降
        System.out.println("-----等級下降-----");
        gameCharacter.setState("Lv.9");
        System.out.println("遊戲角色等級：" + gameCharacter.getState());

        // 決定恢復紀錄
        System.out.println("-----恢復紀錄-----");
        gameCharacter.restoreRecord(recordManager.getRecord());
        System.out.println("遊戲角色等級：" + gameCharacter.getState());
    }

    private static void doCloneMemento() {
        // 有一個遊戲角色，現在87等，且身上有81000元
        System.out.println("-----最初的狀態-----");
        CloneGameCharacter gameCharacter = new CloneGameCharacter();
        gameCharacter.setStateLevel("Lv.87");
        gameCharacter.setStateMoney("81000");
        System.out.println("狀態 -> " + gameCharacter.getAllState());

        // 保存遊戲角色紀錄
        gameCharacter.createRecord();

        // 遊戲角色遇到平偉怪物，慘賠錢
        System.out.println("-----金錢下降-----");
        gameCharacter.setStateMoney("0");
        System.out.println("狀態 -> " + gameCharacter.getAllState());

        // 決定恢復紀錄
        System.out.println("-----恢復紀錄-----");
        gameCharacter.restoreRecord();
        System.out.println("狀態 -> " + gameCharacter.getAllState());
    }

    private static void doManyBackupMemento() {
        // 定義一個記錄管理員
        ManyBackupRecordManager recordManager = new ManyBackupRecordManager();
        // 有一個遊戲角色，現在75等
        System.out.println("-----最初的狀態-----");
        GameCharacter gameCharacter = new GameCharacter();
        gameCharacter.setState("Lv.75");
        System.out.println("狀態 -> " + gameCharacter.getState());
        // 保存目前遊戲角色狀態為1號紀錄
        recordManager.setRecord("1", gameCharacter.createRecord());

        // 遊戲角色等級升級
        System.out.println("-----等級上升-----");
        gameCharacter.setState("Lv.76");
        System.out.println("狀態 -> " + gameCharacter.getState());
        // 保存目前遊戲角色狀態為2號紀錄
        recordManager.setRecord("2", gameCharacter.createRecord());

        // 遊戲角色等級下降
        System.out.println("-----等級下降-----");
        gameCharacter.setState("Lv.75");
        System.out.println("狀態 -> " + gameCharacter.getState());

        // 決定恢復紀錄 -> 選擇2號紀錄
        System.out.println("-----恢復紀錄-----");
        gameCharacter.restoreRecord(recordManager.getRecord("2"));
        System.out.println("狀態 -> " + gameCharacter.getState());
    }

    private static void doSafeMemento() {
        // 定義一個記錄管理員
        SafeRecordManager recordManager = new SafeRecordManager();
        // 有一個遊戲角色，現在33等
        System.out.println("-----最初的狀態-----");
        SafeGameCharacter gameCharacter = new SafeGameCharacter();
        gameCharacter.setState("Lv.33");
        System.out.println("角色狀態 -> " + gameCharacter.getState());
        // 保存遊戲角色紀錄
        recordManager.setRecord(gameCharacter.createRecord());

        // 遊戲角色等級下降
        System.out.println("-----等級下降-----");
        gameCharacter.setState("Lv.32");
        System.out.println("角色狀態 -> " + gameCharacter.getState());

        // 決定恢復紀錄
        System.out.println("-----恢復紀錄-----");
        gameCharacter.restoreRecord(recordManager.getRecord());
        System.out.println("角色狀態 -> " + gameCharacter.getState());
    }
}