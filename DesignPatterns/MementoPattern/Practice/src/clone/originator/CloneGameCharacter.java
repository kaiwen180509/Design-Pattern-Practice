package clone.originator;

public class CloneGameCharacter implements Cloneable {
    private CloneGameCharacter backup;
    private String stateLevel;
    private String stateMoney;

    // 設定等級狀態
    public void setStateLevel(String stateLevel) {
        this.stateLevel = stateLevel;
    }

    // 設定金錢狀態
    public void setStateMoney(String stateMoney) {
        this.stateMoney = stateMoney;
    }

    // 取得等級狀態
    public String getStateLevel() {
        return stateLevel;
    }

    // 取得金錢狀態
    public String getStateMoney() {
        return stateMoney;
    }

    // 取得全部的狀態
    public String getAllState() {
        return "等級：" + stateLevel + " 金錢：" + stateMoney;
    }

    // 建立備份
    public void createRecord() {
        this.backup = this.clone();
    }

    // 恢復備份
    public void restoreRecord() {
        this.setStateLevel(this.backup.getStateLevel());
        this.setStateMoney(this.backup.getStateMoney());
    }

    // 複製自己
    @Override
    protected CloneGameCharacter clone() {
        try {
            return (CloneGameCharacter) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
