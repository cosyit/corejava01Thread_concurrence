package com.mumu1.线程通讯;

import java.util.Random;

public class Demo16{
	public static void main(String[] args) {
		Business business=new Business();
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					business.send();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					business.rec();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		//t2线程开始默认一直while(ture)。所以只有食客消费者在的时候就结束线程。
		t2.setDaemon(true);
		
		t1.start();
		t2.start();
	}
}

class Business {
	private int theValue;
	private boolean flag;
	
	
	//模拟生产者,产出
	public void send() throws InterruptedException{
		for(int i=1;i<5;i++){
			synchronized(this){
				while(flag){
					this.wait();
				}
				theValue=new Random().nextInt();
				System.out.println("send the value is "+theValue);
				flag=true;
				this.notify();
			}
		}
	}
	//模拟消费者
	public void rec() throws InterruptedException{
		while(true){
			synchronized(this){
				while(!flag){
					this.wait();
				}
				System.out.println("receiver the value is" +theValue);
				flag=false;
				this.notify();
				
			}
		}
	}
}
