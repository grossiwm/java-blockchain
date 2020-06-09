package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ObjectUtils {

    public static HashMap objectMapper(Object obj) {
        HashMap<String, Object> attributes = new HashMap<String, Object>();

        try {
            Method[] methods = obj.getClass().getDeclaredMethods();
            Field[] fields = obj.getClass().getDeclaredFields();

            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];

                if (isFieldGetter(method, fields)) {
                    method.setAccessible(true);
                    attributes.put(method.getName().replace("get", "").toLowerCase(), method.invoke(obj));
                }

            }

            return attributes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    private static boolean isFieldGetter(Method method, Field[] fields){
        if (method.getName().startsWith("get")) {

            for (Field field : fields) {
                if (field.getName().toLowerCase().equals(method.getName().replace("get", "").toLowerCase())) {
                    return true;
                }
            }

        }

        return false;
    }
}
