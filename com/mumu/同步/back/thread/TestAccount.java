package com.mumu.同步.back.thread;

import com.mumu.同步.back.entity.Account;

public class TestAccount implements Runnable{
	//所有用TestAccount创建的对象共享同一个账户对象
	private Account account=new Account();
	@Override
	public void run(){
		for(int i=0;i<5;i++){
			
			this.withdrawl(100);
			int 实时余额=account.getBalance();
			
			if(实时余额<0){
				System.out.println("账户账户已经透支了");
			}
		}
	}
	/**
	 * 取款方法
	 * @param amount 取款金额
	 */
	private void withdrawl(int amount){
		//将取款的这段代码，每次只允许一个线程进来。就不会造成错误了。本案例还可以给方法上同步块也是可以的。
		synchronized (account) {
			if (account.getBalance() >= amount) {
				System.out.println(Thread.currentThread().getName() + "准备取款");

				//如果余额足够，则取款
				account.qukuan(amount);
				System.out.println(Thread.currentThread().getName() + "完成取款");
				try {
					Thread.sleep(1000);//0.5秒后实现取款。
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				//余额不足给出提示
				System.out.println(
						"余额不足以支付" + Thread.currentThread().getName() + "的取款，当前余额为：" + account.getBalance() + "元");
			}
		}
	}
}



