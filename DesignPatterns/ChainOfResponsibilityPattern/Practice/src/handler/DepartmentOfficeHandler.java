package handler;

import request.Student;

public class DepartmentOfficeHandler extends Handler {
    @Override
    protected int getEventLevel() {
        return EventLevel.COURSE_SELECTION;
    }

    @Override
    protected void Response(Student student) {
        System.out.println("-----系辦公室的回覆-----");
        System.out.println(student.getName() + " 的安排完成");
    }
}