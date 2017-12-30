package com.mumu1.线程通讯;

import java.util.Random;

/**
 * 生产者，消费者简单模型，这里把生产者作为消费者的成员。
 * @author Administrator
 *
 */
public class ThreadDemo14 {
	public static void main(String[] args) {
		FlagSend send=new FlagSend();
		FlagRec rec=new FlagRec(send);
		Thread t1= new Thread(send);
		Thread t2=new Thread(rec);
		t1.start();
		t2.start();
	}
}
class FlagSend implements Runnable{
	int theValue;
	boolean flag;
	
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			while(flag){
				//如果旗子是true的话
				Thread.yield();
			}
			//这也是获得随机值的另一种方式。 本代码，相当于生产者制造食物。
			theValue=new Random().nextInt(1000);
			System.out.println("send the value is:"+theValue);
			//自己去等待，让食客去吃。
			flag=true;
		}
		
	}
}

class FlagRec implements Runnable{
	private FlagSend flagSend;
	
	
	public FlagRec(FlagSend flagSend) {
		super();
		this.flagSend = flagSend;
	}
	
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			while(!flagSend.flag){
				Thread.yield();
			}
			System.out.println("receiver the value is:"+flagSend.theValue);
			flagSend.flag=false;
		}
	}
	
}