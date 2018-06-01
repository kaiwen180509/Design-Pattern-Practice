package manybackup.caretaker;

import normal.memento.Record;

import java.util.HashMap;

public class ManyBackupRecordManager {
    private HashMap<String, Record> recordHashMap = new HashMap<>();

    // 取得紀錄
    public Record getRecord(String index) {
        return this.recordHashMap.get(index);
    }

    // 保存紀錄
    public void setRecord(String index, Record record) {
        this.recordHashMap.put(index, record);
    }
}
