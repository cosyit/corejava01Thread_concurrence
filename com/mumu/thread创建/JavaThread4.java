package com.mumu.thread创建;

public class JavaThread4 {
	public static void main(String[] args) {
		//new Thread的子类
		new Thread(){
			public void run(){
				for(;;){
					System.out.println("mumu");
				}
			}
		}.start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(;;){
					System.out.println("hello");
				}
			}
		}){
			public void run(){
				for(;;){
					System.out.println("world");
				}
			}
		}.start();
		
	}
}
