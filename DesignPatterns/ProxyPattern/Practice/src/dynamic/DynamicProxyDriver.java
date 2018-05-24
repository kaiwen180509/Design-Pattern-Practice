package dynamic;

import normal.Driver;

import java.lang.reflect.InvocationHandler;

public class DynamicProxyDriver extends DynamicProxy {
    public static <T> T newProxyInstance(Driver driver) {
        // 取得 ClassLoader
        ClassLoader classLoader = driver.getClass().getClassLoader();
        // 取得介面
        Class<?>[] interfaces = driver.getClass().getInterfaces();
        // 取得 Handler
        InvocationHandler handler = new DriverIH(driver);

        return newProxyInstance(classLoader, interfaces, handler);
    }
}
