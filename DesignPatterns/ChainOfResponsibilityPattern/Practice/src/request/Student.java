package request;

public class Student {
    private String name;
    private int eventLevel = 0;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEvent(int eventLevel) {
        this.eventLevel = eventLevel;
    }

    public int getEventLevel() {
        return this.eventLevel;
    }
}
