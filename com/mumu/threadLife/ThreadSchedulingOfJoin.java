package com.mumu.threadLife;
/**
 * 线程调度：使用join方法实现2个线程间的数据传递。
 * @author mumu
 */
public class ThreadSchedulingOfJoin {
	public static void main(String[] args) throws InterruptedException {
		YouThread t=new YouThread();
		t.start();
		t.join();
		System.out.println("val1:"+t.val1);
		System.out.println("val2:"+t.val2);
	}
}

class YouThread extends Thread{
	//本案例为了方便访问，去掉getter,setter，和private修饰。
	 String val1;
	 String val2;
	@Override
	public void run() {
		val1="值1赋值动作完成";
		val2="值2赋值动作完成";
	}
}