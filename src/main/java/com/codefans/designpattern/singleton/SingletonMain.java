package com.codefans.designpattern.singleton;

/**
 * Created by Administrator on 2017/2/25.
 *
 * 参考资料
 * Java枚举实现单例模式
 * http://blog.csdn.net/horace20/article/details/37562513
 *
 * JAVA设计模式之单例模式
 * http://blog.csdn.net/jason0539/article/details/23297037/
 *
 *  23种设计模式（1）：单例模式
 *http://blog.csdn.net/zhengzhb/article/details/7331369
 *
 *  单例模式
 * http://blog.csdn.net/xiangzhihong8/article/details/52345020
 *
 *
 */
public class SingletonMain {

    public static void main(String[] args) {
        String name01 = EnumSingleton.instance.getName();
        String name02 = EnumSingleton.instance.getName();
        System.out.println("EnumSingleton:" + (name01 == name02)); //true

        String icsName01 = InnerClassSingleton.getInstance().getName();
        String icsName02 = InnerClassSingleton.getInstance().getName();
        System.out.println("InnerClassSingleton:" + (icsName01 == icsName02)); //true

        String lazyName01 = LazySingleton.getInstance().getName();
        String lazyName02 = LazySingleton.getInstance().getName();
        System.out.println("LazySingleton:" + (lazyName01 == lazyName02)); //true

        String hungryName01 = HungrySingleton.getInstance().getName();
        String hungryName02 = HungrySingleton.getInstance().getName();
        System.out.println("HungrySingleton:" + (hungryName01 == hungryName02)); //true


    }


}
