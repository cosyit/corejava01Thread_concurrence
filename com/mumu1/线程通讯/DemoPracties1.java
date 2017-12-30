package com.mumu1.线程通讯;

import java.util.Random;

public class DemoPracties1 {
	public static void main(String[] args) {
		OutputModel 产出模型=new OutputModel();
		ConsumeMedel 消耗模型=new ConsumeMedel(产出模型);
		
		Thread t1=new Thread(产出模型);
		Thread t2=new Thread(消耗模型);
		
		t2.setDaemon(true);
		t1.start();
		t2.start();
		
		
		
	}
}

// 产出模型
class OutputModel implements Runnable {
	boolean flag;
	int theValue;

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			synchronized (this) {
				// 当盘子满的，或者盘子是空的。当盘子是满的时候。
				while (flag) {
					try {
						this.wait();//有人会问你都等待了钥匙还没有释放啊！？，wait方法会释放同步的钥匙！！！
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// ——————————————— 盘子是空的情况 ———————————————————
				// 生产者生产食物的业务
				theValue = new Random().nextInt(1000);
				System.out.println("生产模型模拟一个业务:"+theValue);
				// 生产完了，把盘子的状态标识为满，把自己去等待
				flag = true;
				// 唤醒食客--直到放弃此对象上的锁定，也就是说，synchronized（对象）执行结束，才能继续执行被唤醒的线程
				this.notify();
			}

		}

	}

}

// 消费模型

class ConsumeMedel implements Runnable {
	// 需要用到生产者，把生产者作为消费者的成员。
	private OutputModel om;

	// 构造函数加上。
	public ConsumeMedel(OutputModel om) {
		super();
		this.om = om;
	}

	@Override
	public void run() {
		// 开始不知道生产者生产多少食物，那么就先消费完再说---死循环消耗，生产多少，消费多少，直到消耗完。
		while (true) {
			synchronized (om) {
				while (!om.flag) {//拿到他的flag看看要不要去等待。当盘子不是满的情况（空）他才要等待。
					try {
						om.wait();//wait方法会释放钥匙。
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//现在是打印一句话做一个事情的模拟，将来你可以干更多有意义的事情。
				System.out.println("消费食物！消耗模型模拟一个业务"+om.theValue);
				//消费完了之后：自己去等待。一定要是同一个对象上的。
				om.flag=false;
				om.notify();
				
			}
		
		}
		//唤醒对方了！
	}

}