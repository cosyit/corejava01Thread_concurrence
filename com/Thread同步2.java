package com;

import java.util.ArrayList;
import java.util.List;

//数据共享的问题。
//多线程情况下经常会出现数据不一致。那么我们应该如何去做？
//通过同步块（锁和钥匙的机制），保证一段代码同时被一个线程来访问。
/**
 * 普通方法+同步块，锁就是当前对象。
 * 因为普通的方法如何调用肯定是对象来调。
 * 那么正在调用这个方法的对像就不是当前对象this吗？
 * @author Administrator
 *
 */
public class Thread同步2 {
	public static void main(String[] args) {
		Data2 data1=new Data2();
		Thread t1=new Thread(data1);
		Thread t2=new Thread(data1);
		t1.start();
		t2.start();
		
	List al=new ArrayList<>();
	//所有的List集合都是可以加null值的。
	al.add(null);
	}
}

class Data2 implements Runnable{
	private int i;
	//静态方法同步块也是有锁的，锁是当前类的类类型。
	
	@Override
	public void run() {
		int h;
		for(int j=1;j<10;j++){
			synchronized (this) {
				h=i+1;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				i=h;//我们应该保证我们的线程在把赋值完成之后才能进来。
			}
			}//出同步块就会释放锁的钥匙。
		System.out.println(i);
	}
}