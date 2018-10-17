package com.codefans.interview;

/**
 * Created by Administrator on 2017/2/28.
 *
 * 有一天小猴子摘下了若干个桃子，当即吃掉一半，还觉得不过瘾，又多吃了一个。
 * 第二天接着吃了剩下的桃子中的一半，仍不过瘾，又多吃了一个。
 * 以后每天都是吃尚存桃子的一半零一个。
 * 到第10天早上小猴子再去吃桃子时，看到只剩下一个桃子了。
 * 问小猴子第一天共摘下了多少个桃子。
 *
 * 具体思路如下：
 第10天剩余桃数：1
 第9天剩余桃数：（1+1）*2=4
 第8天剩余桃数：（4+1）*2=10
 。。。
 规律很明显，即每天剩余的桃数是第二天桃数加1的两倍。

 */
public class MonkeyEatPeach {

    public static void main(String[] args) {
        int sum = 0;
        int remain = 1;
        for(int i = 9; i >= 1; i --) {
            sum = (remain + 1) * 2;
            remain = sum;
            System.out.println("第" + i + "天还剩" + remain + "个桃子。");
        }
        System.out.println("小猴子第1天总共摘了" + sum + "个桃子。");
    }


}
