package com.mumu.threadLife;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized不面向对象， java5版本就推出了面向对象的Lock。 而且语法也不是那么复杂。
 * 
 * @author mumu
 * 
 */
public class Demo2 {
	public static void main(String[] args) {
		MumuOutput m1 = new MumuOutput();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				m1.print("Hello World!");
			}
		});
		t1.start();
		MumuOutput1 m2 = new MumuOutput1();
		m2.start();
	}
}

class MumuOutput1 extends Thread {
	@Override
	public void run() {
		MumuOutput m1 = new MumuOutput();
		m1.print("Mumu老师告诉我们要做个安静的程序员。");
	}
}

class MumuOutput {
	// 创建锁对象
	Lock lock = new ReentrantLock();

	public void print(String name) {
		// 当线程进来之后，我就给他上了一个锁了。只有程序解锁来能进来。
		lock.lock();
		try {
			while (true) {
				for (int i = 0; i < name.length(); i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}