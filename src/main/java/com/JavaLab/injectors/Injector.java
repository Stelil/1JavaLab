package com.JavaLab.injectors;

import com.JavaLab.annotations.AutoInjectable;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector  {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    public <T> T inject(T object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(AutoInjectable.class)) {
                    field.setAccessible(true);
                    String type = field.getType().getName();
                    System.out.println(type);
                    String sortString = properties(type);
                    Object sort = Class.forName(sortString).newInstance();
                    field.set(object, sort);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }

    private String properties(String type) {
        String res = "";
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
            res = prop.getProperty(type);
        } catch (Exception e) {
        }

        return res;
    }

}