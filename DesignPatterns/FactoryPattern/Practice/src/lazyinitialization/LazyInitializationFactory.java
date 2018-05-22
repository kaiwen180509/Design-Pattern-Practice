package lazyinitialization;

import common.ChocolateCookies;
import common.ICookies;
import common.FruitCookies;
import common.MilkCookies;

import java.util.HashMap;
import java.util.Map;

public class LazyInitializationFactory {
    private static Map<String, ICookies> map = new HashMap<>();

    public static synchronized ICookies make(String type) {
        ICookies cookies = null;
        // 先檢查是否存在實例
        if (map.containsKey(type)) {
            System.out.println(type + " 已存在，從庫存中取出");
            cookies = map.get(type);
        } else {
            // 不存在就建立實例並保存
            if (type.equals("milk")) {
                cookies = new MilkCookies();
            } else if (type.equals("fruit")) {
                cookies = new FruitCookies();
            } else {
                cookies = new ChocolateCookies();
            }
            map.put(type, cookies);
        }
        return cookies;
    }
}
