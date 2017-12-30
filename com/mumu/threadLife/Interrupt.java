package com.mumu.threadLife;
/**
 * 线程的调度方法。
 * @author Administrator
 *
 */
public class Interrupt {
	public static void main(String[] args) {
		MyThread t1=new Interrupt().new MyThread();
		
		t1.start();
	}

	private class MyThread extends Thread{
		
		@Override
		public void run() {
			for(int i=0;i<22;i++){
				
				System.out.println("模拟线程："+i);
				if(i==10){
					try {
						Thread.sleep(5000);
						System.out.println("线程宝宝"+this.currentThread().getName()+"：休息够了");
						Thread.interrupted();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
}

	
}
