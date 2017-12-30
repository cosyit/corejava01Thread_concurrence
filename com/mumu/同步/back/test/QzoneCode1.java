package com.mumu.同步.back.test;

import java.util.Random;

import com.mumu.同步.back.thread.TestAccount;

public class QzoneCode1 {
	/**
	 * 案例：老九和他的女友灵灵共同有用一个账户，可以对同一个银行账户进行存取款的操作。
	 * 使用多线程模拟一下同时存取款的过程。
	 * 1.建文件写Medel实体模型类
	 * 2.定义2个取款任务的线程类。run方法里就是取款。
	 * 3.写一个运行程序的入口MainTest类。
	 */
	public static void main(String[] args) {
		//创建继承了Runable接口的线程类的对象。
		TestAccount r=new TestAccount();
		//把对象作为构造函数的参数 new出来。
		Thread one=new Thread(r);
		Thread two=new Thread(r);
		one.setName("老九");
		two.setName("灵灵");
		one.start();
		two.start();
	}
}
