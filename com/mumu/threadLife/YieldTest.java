package com.mumu.threadLife;

public class YieldTest {
	public static void main(String[] args) {
		YieldTest1 t1=new YieldTest1();
		t1.start();
		YieldTest2 yt=new YieldTest2();
		Thread t2=new Thread(yt);
		t2.start();
		System.out.println(t2.currentThread().getName());
	}
}


class YieldTest1 extends Thread{
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println("第一个线程的第"+(i+1)+"次运行");
			//大家注意到了，他和sleep()方法相同属于静态方法。join()方法属于动态方法，还需要创建对象来调用。
			Thread.yield();
		}
	}
}

class YieldTest2  implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<5;i++){
			System.out.println("第二个线程的第"+(i+1)+"次运行");
			Thread.yield();
		}
	}
	
}