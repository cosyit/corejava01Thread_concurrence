package com.mumu.threadLife;

public class Demo1 {
	public static void main(String[] args) {
		//创建自定义的Thread的对象。因为在其他包中已经出现
		//MyThread的类了。为了大家看的清楚些。所以把MyThread的类成员。
		MyThread mt=new Demo1().new MyThread();
		//把自定义的线程对象 作为Thread的构造函数的参数传入，得到构造函数的返回值t。
		Thread t=new Thread(mt);
		//启动线程。
		t.start();
		//这是主线程。
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(i);
			if(i==5)
				//当循环到i=5的时候，就让t线程宝宝唤醒参与运行竞争。
			t.interrupt();
		}		
	}
	private class MyThread implements Runnable{
		@Override
		public void run() {
					try {
						Thread.sleep(100000);
					} catch (InterruptedException e) {
						System.out.println("线程中断");//e.printStackTrance();
					}
					System.out.println("helloworld");
		}
	}
}
