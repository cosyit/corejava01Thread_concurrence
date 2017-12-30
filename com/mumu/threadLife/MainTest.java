package com.mumu.threadLife;
public class MainTest{
	public static void main(String[] args) {
		//主线程
		for(int i=0;i<100;i++){
			//当主线程运行6次结束后，开始MyThread线程。
			if(i==6){
				MyThread t1=new MyThread("mumuTeacher");//这个是上面那个自定义Thread的对象。
				try {
					t1.start();
					t1.join();//把该线程用join插入到主线程前面。
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+"的第"+i+"次");
		}
	}
}

class MyThread extends Thread{

	public MyThread(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public void run(){
		for(int i=0;i<10;i++){
			//输出当前线程的名字。
			System.out.println(Thread.currentThread().getName()+"的第"+i+"次执行");
		}
	}
}