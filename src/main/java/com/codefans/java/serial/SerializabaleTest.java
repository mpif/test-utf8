package com.codefans.java.serial;

import java.io.*;

/**
 * Created by Administrator on 2017/3/11.
 */
public class SerializabaleTest implements Serializable {


    private static final long serialVersionUID = -1030377719658733055L;

    private String name = "hello中国";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("result.obj"));
        SerializabaleTest test = new SerializabaleTest();
        // 试图将对象两次写入文件
        out.writeObject(test);
        out.flush();
        System.out.println(new File("result.obj").length());
        out.writeObject(test);
//        out.writeObject(new SerializabaleTest());
        out.close();
        System.out.println(new File("result.obj").length());
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream("result.obj"));
        // 从文件依次读出两个文件
        SerializabaleTest t1 = (SerializabaleTest) oin.readObject();
        SerializabaleTest t2 = (SerializabaleTest) oin.readObject();
        oin.close();
        // 判断两个引用是否指向同一个对象
        System.out.println(t1 == t2);


    }
}
