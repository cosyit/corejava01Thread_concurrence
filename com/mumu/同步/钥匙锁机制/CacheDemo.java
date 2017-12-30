package com.mumu.同步.钥匙锁机制;
//缓存的模拟
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
	
//模拟缓存操作。用Map接口的实现类来缓存数据。

/**
 * 修改数据的时候，只能一个线程。
 * 但是只是读数据，所有线程都可以访问。
 * 
 * java5之后提供了读写锁的机制。
 * @author Administrator
 *
 */
public class CacheDemo {
	private HashMap<String,String> hm=new HashMap<String, String>();
	private ReentrantReadWriteLock rrw=new ReentrantReadWriteLock(true);
	private String value=null;
	
	//把key传递过来，我去取数据。怎么取？
/*	public String getData(String key){
		value=hm.get(key);
		//判断值是不是没有数据，没有数据。赋值操作。
		if(value==null){
			value="hello";//将来是通过其他资源获取。而且资源很耗时。
			//这样，我们就把这次得到得到的值，放进去了。
			hm.put(key, value);
		}
		return value;
	}
 */
	public String getData(String key){
		rrw.readLock().lock();//看到这段代码就要写try/catch/finally
		try {
			//所有线程宝宝可以来读数据。
			value=hm.get(key);
			if(value==null){//value如果是null那就进行写操作
				//要进行写操作
				rrw.readLock().unlock();//解读锁
				rrw.writeLock().lock();//上写锁，既然上了写锁又要try/catch/finally
				try {
					if(value==null){
						
					value="从其他地方取得的资源";
					hm.put(key, value);
					}
				} catch (Exception e) {
				}finally{
					rrw.writeLock().unlock();//
					rrw.readLock().lock();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			rrw.readLock().unlock();//解读锁
		}
		
		return value;
		
	}
	
	
	
}
