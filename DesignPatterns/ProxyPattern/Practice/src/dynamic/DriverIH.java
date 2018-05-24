package dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DriverIH implements InvocationHandler {
    // 被代理的物件
    private Object object = null;

    public DriverIH(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 代理的方法準備工作與預處理
        if (method.getName().equalsIgnoreCase("board")) {
            System.out.println("代理司機準備代替駕駛");
        } else if (!method.getName().equalsIgnoreCase("leave")) {
            System.out.print("代理司機代替");
        }

        Object result = method.invoke(this.object, args);

        // 代理的方法後處理
        if (method.getName().equalsIgnoreCase("leave")) {
            System.out.println("代理司機離開");
        }
        return result;
    }
}
