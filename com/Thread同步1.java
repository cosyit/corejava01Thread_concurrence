package com;
//数据共享的问题。
//多线程情况下经常会出现数据不一致。那么我们应该如何去做？
public class Thread同步1 {
	public static void main(String[] args) {
		
	}
}

class Data implements Runnable{
	private int i;
	
	@Override
	public void run() {
		int h;
		for(int j=1;j<=10;j++){
			h=i+1;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			i=h;
		}
		System.out.println(i);
	}
}