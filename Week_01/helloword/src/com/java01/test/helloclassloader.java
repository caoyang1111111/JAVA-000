package com.java01.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class helloclassloader extends ClassLoader {


    protected Class < ? > findClass ( String name ) throws ClassNotFoundException {
        String filename = "C:\\project\\helloword\\src\\com\\java01\\test\\Hello.xlass";  // read file
        byte[] bytes = new byte[0]; //
        try (FileInputStream file = new FileInputStream(filename)) {
            int num = file.available();
            bytes = new byte[num];                                                        //定义字节数组，用来解析二进制文件
            file.read(bytes);
            file.close();
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);                                       //文件解析逻辑处理
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, bytes, 0, bytes.length); //返回   class hello
    }

    public static void main ( String[] args ) throws ClassNotFoundException {
        try {
            try {
                Class<?> Class_hello=new helloclassloader().findClass("Hello"); //加载类
                Object o=Class_hello.newInstance(); //实例化
                Method method=Class_hello.getMethod("hello"); //调用实例中的方法
                method.invoke(o); //反射调用类本身hello函数
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}