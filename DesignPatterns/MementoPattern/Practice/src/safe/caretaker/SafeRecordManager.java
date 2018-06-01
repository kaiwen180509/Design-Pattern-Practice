package safe.caretaker;

import safe.IRecord;

public class SafeRecordManager {
    private IRecord record;

    public IRecord getRecord() {
        return record;
    }

    public void setRecord(IRecord record) {
        this.record = record;
    }
}
