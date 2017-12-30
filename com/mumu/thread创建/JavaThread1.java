package com.mumu.thread创建;

public class JavaThread1 {
	
	//如何创建线程:很简单。写个类继承Runnable接口，
	//然后把其对象，作为Thread 类的构造函数的参数。new一下就行。
	public static void main(String[] args) {
		
		A a=new A();
		
		Thread t1=new Thread(a);
		
		t1.start();//大家不要用t1直接调用run()，那就不是线程了。
		

		/**
		 * 观察运行结果整体来看，2个线程在同时运行。
		 */
		
		B b=new B();
		
		Thread t2=new Thread(b);
		//设置优先级
		t2.setPriority(5);
		
		t2.start();
		
		for(;;){
			System.out.println("我是主线程。");
		}
	}
	
}

class A implements Runnable{

	@Override
	public void run() {
		for(;;){
			System.out.println("hello");
		}
	}
	
}

class B implements Runnable{

	@Override
	public void run() {
		for(;;){
			System.out.println("B线程做的事情。数数JVM中可用的CPU个数"+Runtime.getRuntime().availableProcessors());
		}
		
	}
	
}