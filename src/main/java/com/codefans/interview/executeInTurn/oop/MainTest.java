package com.codefans.interview.executeInTurn.oop;

/**
 * Created by caishengzhi on 2017/3/8 14:42.
 *
 * 多线程技术: 两个线程交替打印奇数和偶数
 * http://www.cnblogs.com/study-everyday/p/6292738.html
 *
 * Java 多线程中。两个线程交替执行，一个输出偶数，一个输出奇数
 * http://blog.csdn.net/woainiwss/article/details/52013810
 *
 */
public class MainTest {

    public static void main(String[] args){
        Num num = new Num(); //声明一个资源

        PrintOdd printOdd = new PrintOdd(num);
        PrintEven printEven = new PrintEven(num);

        Thread aThread = new Thread(printOdd);
        Thread bThread = new Thread(printEven);

        aThread.start();
        bThread.start();
    }
}
