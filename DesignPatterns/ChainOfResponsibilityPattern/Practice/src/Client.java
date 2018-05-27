import handler.EventHandler;
import handler.EventLevel;
import request.Student;

public class Client {
    public static void main(String[] args) {
        // 定義各處室的責任鏈
        EventHandler handler = new EventHandler();

        // 有一個叫 Jack 的學生要休學
        Student jack = new Student("Jack");
        jack.setEvent(EventLevel.LEAVE_SCHOOL);
        handler.handle(jack);

        // 有一個叫 Kata 的學生要申請獎學金
        Student kata = new Student("Kata");
        kata.setEvent(EventLevel.FINANCIAL_AFFAIRS);
        handler.handle(kata);

        // 有一個叫 Burton 的學生要選課
        Student burton = new Student("Burton");
        burton.setEvent(EventLevel.COURSE_SELECTION);
        handler.handle(burton);
    }
}