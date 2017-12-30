package com.mumu.thread创建;

public class JavaThread3 {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(;;){
					System.out.println("心静下来，好好学习。");
				}
				
			}
		}).start();
		
		
		for(;;){
			System.out.println("除了低头敲代码，还要抬头看看外面的世界。");
		}
	}
}
