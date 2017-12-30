package com.mumu.thread创建;

public class JavaThread2 {
	public static void main(String[] args) {
		
		MyThread t1=new MyThread();
		
		t1.start();

	}
}

class MyThread extends Thread{

	@Override
	public void run() {
		for(;;){
			System.out.println("hello");
		}
	}
}

class YouThread extends Thread{
	@Override
	public void run() {
		System.out.println("world");
	}
}
