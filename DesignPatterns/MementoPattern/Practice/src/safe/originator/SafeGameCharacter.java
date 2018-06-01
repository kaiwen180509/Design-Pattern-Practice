package safe.originator;

import safe.IRecord;

public class SafeGameCharacter {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    // 建立備份
    public SafeRecord createRecord() {
        return new SafeRecord(this.state);
    }

    // 恢復備份
    public void restoreRecord(IRecord record) {
        this.setState(((SafeRecord) record).getState());
    }

    // 建立紀錄的內部類別
    private class SafeRecord implements IRecord {
        private String state;

        public SafeRecord(String state) {
            this.state = state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }
}
