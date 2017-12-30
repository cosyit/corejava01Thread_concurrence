package com.mumu1.线程通讯;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

class BeeperControl {
	// 属性为创建的1个能延迟或者定期蜂鸣的线程。
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	// 定义一个每一小时蜂鸣一次的方法。
	public void beepForAnHour() {
		//实现了Runnable接口的类的具体实例对象。
		final Runnable beeper = new Runnable() {
			public void run() {
				System.out.println("beep");
			}
		};
		
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
		
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
			}
		}, 60 * 60, SECONDS);
	}
}
