package com.codefans.java.serial;

import java.io.*;

/**
 * Created by Administrator on 2017/3/11.
 */
public class ExternalizableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ExternalizableTest et = new ExternalizableTest();
        et.testWriteObject();
        et.testReadObject();
    }

    public void testWriteObject() throws IOException {
        ExternalizableObject eo = new ExternalizableObject();
        eo.setName("hello中国");
        eo.setPassword("hello密码");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("output.obj")));
        eo.writeExternal(oos);
        oos.flush();
        oos = null;
    }

    public void testReadObject() throws IOException, ClassNotFoundException {
        ExternalizableObject eo = new ExternalizableObject();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("output.obj")));
        eo.readExternal(ois);
        System.out.println("name:" + eo.getName());
        System.out.println("pawd:" + eo.getPassword());
        ois = null;
    }

}
