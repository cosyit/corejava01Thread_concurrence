package com.mumu1.线程通讯;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ThreadDemo13 {
	public static void main(String[] args) {
		PipedOutputStream out=null;
		PipedInputStream in=null;
		try {
			in=new PipedInputStream();
			out=new PipedOutputStream(in);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Sender sender=new Sender(out);
		Thread t1=new Thread(sender);
		
		Receiver receiver=new Receiver(in);
		Thread t2=new Thread(receiver);
		t1.start();
		t2.start();
	}
}


class Sender implements Runnable{
	private OutputStream out;

	public Sender(OutputStream out) {
		super();
		this.out = out;
	}

	@Override
	public void run() {
		//做的事情很简单，循环五次产生5次随机的byte值。
		for(int i=1;i<=5;i++){

			byte value=(byte)(Math.random()*100);
			System.out.println("send the value is :"+value);
			try {
				out.write(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

class Receiver implements Runnable{
	private InputStream in;
	
	public Receiver(InputStream in) {
		super();
		this.in=in;
	}
	@Override
	public void run() {
		for(int i=1;i<=5;i++){
			try {
				byte value=(byte)in.read();
				System.out.println("receiver the value is:"+value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}