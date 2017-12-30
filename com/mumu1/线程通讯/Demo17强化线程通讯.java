package com.mumu1.线程通讯;

/**
 * 强化线程通讯
 * 先A线程运行10次，然后B线程运行20次，如此反复50次。
 * A线程生产食物需要循环10次，B线程消费食物需要循环20次。
 * @author mumu
 *
 */
@Deprecated
public class Demo17强化线程通讯 {
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(new Runnable(){

				@Override
				@Deprecated
				public void run() {
					//内部类访问局部变量必须是final来修饰的。
					//System.out.println(i); 这个i有问题的。
				
				}
				
			}).start();
		}
	}
}
