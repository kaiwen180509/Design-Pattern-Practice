package normal.memento;

public class Record {
    private String state;

    public Record(String state) {
        this.state = state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
