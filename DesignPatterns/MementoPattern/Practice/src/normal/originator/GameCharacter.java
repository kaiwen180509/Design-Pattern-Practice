package normal.originator;

import normal.memento.Record;

public class GameCharacter {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    // 建立備份
    public Record createRecord() {
        return new Record(this.state);
    }

    // 恢復備份
    public void restoreRecord(Record record) {
        this.setState(record.getState());
    }
}
