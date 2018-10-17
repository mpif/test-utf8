package com.codefans.java.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 如何防止单例模式被JAVA反射攻击
 * http://www.importnew.com/22493.html
 * @author: caishengzhi
 * @date: 2016年12月6日下午4:12:21
 */
public class SingletonAttachByReflect {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {  
        Class<?> classType = Elvis.class;  
 
        Constructor<?> c = classType.getDeclaredConstructor(null);  
        c.setAccessible(true);  
        Elvis e1 = (Elvis)c.newInstance();  
        Elvis e2 = Elvis.getInstance();  
        System.out.println(e1==e2);  
        
        SingletonClass sgtcls = SingletonClass.INSTANCE;
        sgtcls.test();
        
        //枚举类的单例不会被侵犯,是因为下面四行侵犯代码会报java.lang.NoSuchMethodException
        Class<?> classType2 = SingletonClass.class;  
        Constructor<?> c2 = classType2.getDeclaredConstructor(null);  
        c2.setAccessible(true);  
        SingletonClass e12 = (SingletonClass)c2.newInstance();  
        
    }  
	
}

class Elvis  
{  
    private static boolean flag = false;  
 
    private Elvis(){  
    	//检查单例是否被侵犯
    	if(flag == false) {
    		flag = !flag;
    	} else {
//    		throw new RuntimeException("私有方法被外部调用,单例模式被侵犯！");
    	}
    }  
 
    private  static class SingletonHolder{  
        private static final Elvis INSTANCE = new Elvis();  
    }  
 
    public static Elvis getInstance()  
    {  
        return SingletonHolder.INSTANCE;  
    }  
 
    public void doSomethingElse()  
    {  
 
    }  
}

enum SingletonClass  
{  
    INSTANCE;  
	
	private SingletonClass() {
		System.out.println("hello world...");
	}
	
    public void test()  
    {  
        System.out.println("The Test!");  
    }  
}

