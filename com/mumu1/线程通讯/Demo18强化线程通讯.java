package com.mumu1.线程通讯;

/**
 * 强化线程通讯
 * 先A线程运行10次，然后B线程运行20次，如此反复50次。
 * A线程生产食物需要循环10次，B线程消费食物需要循环20次。
 * @author mumu
 *
 */
public class Demo18强化线程通讯 {
	public static void main(String[] args) {
		//创建了10个线程，执行了10个任务。
		for(int i=0;i<20;i++){
			final int task=i;
			new Thread(new Runnable(){

				@Override
				public void run() {
					//运行结果证明了线程执行任务的顺便是随机的。
					System.out.println(task);
					
				}
				
			}).start();
		}
	}
}
