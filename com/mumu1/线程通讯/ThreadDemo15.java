package com.mumu1.线程通讯;
//多线程中：生产者和消耗者的套路。
import java.util.Random;
/**
 * 在开始我们先介绍wait和notifyAll这两个方法。
 * 这两个方法是Object类的方法，并且是native方法。 
（1）调用某个对象的wait()方法能让当前线程阻塞，并且当前线程必须拥有此对象锁。
(2) 唤醒在此对象监视器上等待的单个线程。如果所有线程都在此对象上等待，则会选择唤醒其中一个线程。
选择是任意性的，并在对实现做出决定时发生。线程通过调用其中一个 wait 方法，在对象的监视器上等待。  
（3）调用notifyAll()方法能够唤醒所有正在等待这个对象的锁线程；
	只能唤醒等待该想拥有该对象锁的线程，因此我们需要保证对象的同一性。
	
	API上的解释：
	唤醒在此对象监视器上等待的所有线程。线程通过调用其中一个 wait 方法，在对象的监视器上等待。 
直到当前线程放弃此对象上的锁定，才能继续执行被唤醒的线程。被唤醒的线程将以常规方式与在该对象上主动同步的其他所有线程进行竞争；例如，唤醒的线程在作为锁定此对象的下一个线程方面没有可靠的特权或劣势。 

此方法只应由作为此对象监视器的所有者的线程来调用。有关线程能够成为监视器所有者的方法的描述，请参阅 notify 方法。 



 * @author mumu
 *
 */
public class ThreadDemo15 {
	public static void main(String[] args) {
		//创建Runnable接口实例
		WaitSend send=new WaitSend();
		WaitRec rec=new WaitRec(send);
		//把实例作为Thread类的构造函数的参数。
		Thread t1=new Thread(send);
		Thread t2=new Thread(rec);
		//把t2设置为守护线程。当程序只有守护线程运行时，程序会自动结束。
		//守护线程就是守护其他线程的，如果就它一个人在运行，他没有人要守护，他肯定要回家睡觉，难道守护空气吗？
		t2.setDaemon(true);
		t1.start();
		t2.start();
	}
}

/**
 * 生产者模型
 * @author Administrator
 *
 */
class WaitSend implements Runnable{

	//假设盘子厨师制造满了为1，盘子喝空了为0。
	boolean panzi;
	int theValue;
	@Override
	public void run() {
		for(int i=0;i<5;i++){
			/**
			 * 语言特性严格规定：这个this和下面的this必须是同一个对象。
			 * 如果你用的是这个对象的锁，那么就意味着你要挂到这个对象的线程等待池当中。
			 * 生产者和消费者一定要是同一个等待池。将来消费者也要挂到这个对象上。
			 */
			synchronized (this){
				/**
				 * 实现中断和虚假唤醒是可能的，
				 * 所以一定要用while。如果被虚假唤醒只要标识不变，还是继续等待。
				 * 虚假唤醒就是一些obj.wait()会在
				 * 除了obj.notify()和obj.notifyAll()的其他情况被唤醒，
				 * 而此时是不应该唤醒的。
				 * 解决的办法是基于while来反复判断进入正常操作的临界条件是否满足：
				 */
				try {//还有不要把try、catch，finally.不要写在while里面，一旦出现异常就不合理了。
				while(panzi){
						this.wait();//wait会释放同步的钥匙。
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				//代码到达这里说明盘子空了，那么生产者制造食物。
				theValue=new Random().nextInt(1000);
				//另外一种获取随机数的方式：theValue=(int) (Math.random()*1000);
				panzi=true;//盘子满了。厨师线程又去等待了。
				this.notify();//不要忘了，食客线程也要挂这这个对象上。只有在同一个对象上才能进行唤醒和等待的操作。	
			}
			
		}
	}
	
}

/**
 * 消费者模型
 * @author Administrator
 *
 */
class WaitRec implements Runnable{
	//1.先把生产者作为你的成员属性。
	private  WaitSend send;

	public WaitRec(WaitSend send) {
		super();
		this.send = send;
	}

	@Override
	public void run() {
		//不知道生产者生产多少食物，生产多少消费多少。所以我写一个while循环死循环去消费。
		while(true){
			//因为前面已经将生产者作为了属性，这里可以直接使用这个对象了。
			synchronized (send) {
				while(!send.panzi){
					try { 
						send.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//如果前面不等待的话，那么我就去:消费食物(做一个业务模拟)。
				System.out.println("recevie the value is"+send.theValue);
				//食物消费完了。自己去等待。
				send.panzi=false;//同一个对象上的。
				send.notify();
			}//Out--代码锁定区
		}
	}
	
	
	
}