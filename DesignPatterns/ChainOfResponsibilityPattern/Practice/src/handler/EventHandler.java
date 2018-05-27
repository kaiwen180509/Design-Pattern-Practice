package handler;

import request.Student;

public class EventHandler {
    // 教務處處理者
    private Handler academicAffairsOfficeHandler = new AcademicAffairsOfficeHandler();
    // 課指組處理者
    private Handler courseGuideGroupHandler = new CourseGuideGroupHandler();
    // 系辦公室處理者
    private Handler departmentOfficeHandler = new DepartmentOfficeHandler();

    // 對責任鏈進行封裝
    public EventHandler() {
        createChain();
    }

    // 建立責任鏈
    private void createChain() {
        // 責任鏈的順序安排
        this.academicAffairsOfficeHandler.setNextHandler(this.courseGuideGroupHandler);
        this.courseGuideGroupHandler.setNextHandler(this.departmentOfficeHandler);
    }

    // 處理要求
    public void handle(Student student) {
        this.academicAffairsOfficeHandler.handle(student);
    }
}
