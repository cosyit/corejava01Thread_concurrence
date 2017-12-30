package com.mumu.同步.钥匙锁机制;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author mumu
 *
 */
public class Demo1 {
	public static void main(String[] args) {
		Output output=new Output();
		
		//线程宝宝1
		new Thread(new Runnable() {
			/**
			 * 一个字符一个字符打印，在在打印过程中没有打完，线程就被切换走了。
			 */
			@Override
			public void run() {
				while(true){
					output.print("大家好，我是木木老师。");
				}
			}
		}).start();
		
		//线程宝宝2
		new Thread(new Runnable() {
			/**
			 * 一个字符一个字符打，很有可能在打印过程中没有打完，线程就被切换走了。
			 */
			@Override
			public void run() {
				while(true){
					output.print("Hello World!");
				}
				
			}
		}).start();
	}
}

/**
 * 一个将JVM中的数据输出到显示终端的类。
 * @author mumu
 *
 */
class Output{
	public void print(String name){
		//this这里面钥匙每个线程必须是同一把才行。不然不同的钥匙对应不同的锁。同步就没有效果了。
		synchronized(this){
			//将String类型的name一个字符一个字符打印出来。
			for(int i=0;i<name.length();i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		//打开锁，抽出钥匙。
	}
	public static void print2(String name){
		//this这里面钥匙每个线程必须是同一把才行。不然不同的钥匙对应不同的锁。同步就没有效果了。
		synchronized(Output.class){

		}
		//打开锁，抽出钥匙。
		
		//StringBuilder //源码没有没有synchronized，线程并发数据共享的时候可能会有这种问题。
		//StringBuffer 线程安全，性能要差一点。
		
		//ArrayList  Vector
	}
	
	Lock lock=new ReentrantLock();
	public void print3(String name){
		lock.lock();
		try {
			//将String类型的name一个字符一个字符打印出来。
			for(int i=0;i<name.length();i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();	
		}finally{
			lock.unlock();
		}

		
	}
}