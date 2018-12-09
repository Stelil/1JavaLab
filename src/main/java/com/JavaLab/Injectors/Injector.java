package com.JavaLab.Injectors;

import com.JavaLab.Annotations.AutoInjectable;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector implements InterfaceInjector {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    public Object inject(Object object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(AutoInjectable.class)) {
                    field.setAccessible(true);
                    String sortString = properties();
                    Object sort = Class.forName(sortString).newInstance();
                    field.set(object, sort);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private String properties() {
        String res = "";
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
            res = prop.getProperty("sort");
        } catch (Exception e) {
        }

        return res;
    }

}