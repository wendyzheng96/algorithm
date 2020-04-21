package com.zyf.algorithm.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射相关方法使用测试
 */
public class Test {

    public static void main(String[] args){
        AccountBean bean = new AccountBean();
        Class clazz1 = bean.getClass();
        System.out.println(clazz1.getName());

        Class clazz2 = AccountBean.class;
        System.out.println(clazz1 == clazz2);

        try {
            Class clazz3 = Class.forName("com.zyf.algorithm.reflect.AccountBean");
            System.out.println(clazz3 == clazz2);

            Constructor[] consArray = clazz3.getDeclaredConstructors();
            for (Constructor c : consArray) {
                System.out.println(c);
            }

            System.out.println();
            Field[] fieldArray = clazz3.getDeclaredFields();
            for (Field f : fieldArray) {
                System.out.println(f);
            }

            System.out.println();
            Object obj1 = clazz3.newInstance();
            Field f = clazz3.getDeclaredField("id");
            f.set(obj1, "1");
            System.out.println("obj1.id = " + ((AccountBean) obj1).id);

            Constructor con = clazz3.getDeclaredConstructor(String.class);
            con.setAccessible(true);
            Object obj2 = con.newInstance("12");

            System.out.println();
            Method[] methodArray = clazz3.getDeclaredMethods();
            for (Method m : methodArray) {
                System.out.println(m);
            }
            System.out.println();
            Method method1 = clazz3.getDeclaredMethod("show");
            method1.invoke(obj1);

            Method method2 = clazz3.getDeclaredMethod("show2", String.class);
            method2.invoke(obj2, "21");

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
