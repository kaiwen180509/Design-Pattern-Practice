package handler;

import request.Student;

public abstract class Handler {
    private Handler nextHandler = null;

    // 每個處理者的層級
    protected abstract int getEventLevel();

    // 每個處理者的回應
    protected abstract void Response(Student student);

    // 設定下一個處理者
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // 實際的處理
    public final void handle(Student student) {
        // 判斷是否是自己能處理的事情
        if (student.getEventLevel() == this.getEventLevel()) {
            this.Response(student);
        } else {
            // 還有下一個處理者時，交給下一個處理者去處理
            if (this.nextHandler != null) {
                this.nextHandler.handle(student);
            } else {
                // 沒有處理者了
                System.out.println(student.getName() + " 的這個問題無法處理");
            }
        }
    }
}
