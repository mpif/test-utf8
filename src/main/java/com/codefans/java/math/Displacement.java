package com.codefans.java.math;

/**
 * Created By MyEclipse 8.6 M1
 * User: caisz
 * Date: 2012-9-3
 * Time: 下午1:19:42
 * To change this template use Window | Preferences | Java | Code Style | Code Templates
 */

/**
 * displacement 位移
 */
public class Displacement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displacement dis = new Displacement();
		int number = dis.addWithoutArithmetic(18, 32);
		System.out.println("number: " + number);
	}

	public int addWithoutArithmetic(int num1, int num2) {
		if (num2 == 0)
			return num1;// 没有进位的时候完成运算
		int sum, carry;
		sum = num1 ^ num2;// 完成第一步没有进位的加法运算
		carry = (num1 & num2) << 1;// 完成第二步进位并且左移运算
		return addWithoutArithmetic(sum, carry);// 进行递归，相加
	}

}

// AddWithoutArithmetic.cpp : 定义控制台应用程序的入口点。
//
// #include "stdafx.h"
// #include <iostream>
//
// using namespace std;
//
// int AddWithoutArithmetic(int num1,int num2)
// {
// if(num2==0) return num1;//没有进位的时候完成运算
// int sum,carry;
// sum=num1^num2;//完成第一步没有进位的加法运算
// carry=(num1&num2)<<1;//完成第二步进位并且左移运算
// return AddWithoutArithmetic(sum,carry);//进行递归，相加
// }
// int Add(int a,int b)
// {
// return b ? Add(a^b,(a&b)<<1) : a;
// }
//
// int negtive(int a) //取补码
// {
// return Add(~a, 1);
// }
// int Sub(int a, int b)
// {
// return Add(a, negtive(b));
// }
// //正数乘法运算
// int Pos_Multiply(int a,int b)
// {
// int ans = 0;
// while(b)
// {
// if(b&1)
// ans = Add(ans, a);
// a = (a<<1);
// b = (b>>1);
// }
// return ans;
// }
//
// //除法就是由乘法的过程逆推，依次减掉（如果x够减的）y^(2^31),y^(2^30),...y^8,y^4,y^2,y^1。减掉相应数量的y就在结果加上相应的数量。
// int Pos_Div(int x,int y)
// {
// int ans=0;
// for(int i=31;i>=0;i--)
// {
// //比较x是否大于y的(1<<i)次方，避免将x与(y<<i)比较，因为不确定y的(1<<i)次方是否溢出
// if((x>>i)>=y)
// {
// ans+=(1<<i);
// x-=(y<<i);
// }
// }
// return ans;
// }
// int _tmain(int argc, _TCHAR* argv[])
// {
// cout<<AddWithoutArithmetic(4,5)<<endl;
// cout<<Add(2,3)<<endl;
// cout<<Sub(3,1)<<endl;
// cout<<Pos_Multiply(2,3)<<endl;
// cout<<Pos_Div(6,3)<<endl;
// while (1)
// {
// }
// return 0;
// }