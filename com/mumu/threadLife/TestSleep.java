package com.mumu.threadLife;

public class TestSleep {
	public static void main(String[] args) {
		System.out.println("Waiting");
		Wait.runBySeconed(5);
		System.out.println("Start");
	}
}

class Wait {
	public static void runBySeconed( long s) {
		for(int i=0;i<s;i++){
			System.out.println(i+1+"秒");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}



