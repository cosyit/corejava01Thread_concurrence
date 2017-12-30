package com.mumu.同步.back.entity;

public class Account {
	private int balance;//余额
	
	{
		balance=900;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	//给账户一个取款的方法
	public void qukuan(int howMoney){
		balance=balance-howMoney;
	}
	
}
