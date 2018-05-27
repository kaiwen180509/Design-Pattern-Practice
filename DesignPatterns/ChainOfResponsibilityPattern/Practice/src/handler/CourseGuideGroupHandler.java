package handler;

import request.Student;

public class CourseGuideGroupHandler extends Handler {
    @Override
    protected int getEventLevel() {
        return EventLevel.FINANCIAL_AFFAIRS;
    }

    @Override
    protected void Response(Student student) {
        System.out.println("-----課指組的回覆-----");
        System.out.println(student.getName() + " 的申請通過");
    }
}