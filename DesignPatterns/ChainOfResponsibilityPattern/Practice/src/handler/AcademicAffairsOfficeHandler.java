package handler;

import request.Student;

public class AcademicAffairsOfficeHandler extends Handler {
    @Override
    protected int getEventLevel() {
        return EventLevel.LEAVE_SCHOOL;
    }

    @Override
    protected void Response(Student student) {
        System.out.println("-----教務處的回覆-----");
        System.out.println(student.getName() + " 的申請通過");
    }
}
