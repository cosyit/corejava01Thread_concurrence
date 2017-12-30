package com;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	// static 静态块的目的是让这些工作只加载一次。
	
	static {
		try {
			//创建一个config配置对象去加载配置xml，返回一个配置对象。
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).buildServiceRegistry();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable exception) { // 加一个异常处理程序更加细腻
			System.err.println("Initial SessionFactory creation failed."
					+ exception + "初始化sessionFactory失败。");
			throw new ExceptionInInitializerError(exception);
		}
	}

	/*
	 * ThreadLocal并不是线程本地化的实现,而是线程局部变量。 也就是说每个使用该变量的线程都必须为该变量提供一个副本,
	 * 每个线程改变该变量的值仅仅是改变该副本的值,而不会影响其他线程的该变量的值.
	 * ThreadLocal是隔离多个线程的数据共享，不存在多个线程之间共享资源,因此不再需要对线程同步
	 */
	public static final ThreadLocal<Session> s = new ThreadLocal<Session>();

	public static Session getCurrentSession() throws HibernateException {

		Session session = s.get();
		// 如果该线程还没有Session,则创建一个新的Session
		if (session == null) {
			session = sessionFactory.openSession();
			// 将获得的Session变量存储在ThreadLocal变量session里
			s.set(session);
		}
		return session;
	}

	public static void closeSession() throws HibernateException {
/**
 * s不是一个本地线程的实现，它根本就不是一个线程，而是线程的局部变量。
 * 它的作用是为每一个使用这个变量的线程都提供一个变量值的副本，并且每一个线程
 * 都可以独立的改变自己的副本，而不会和其他线程的副本冲突。
 * 从线程的角度看，好像每一个线程都完全拥有一个该变量一样。
 */
		Session session = s.get();
		
		if (session != null) {
			session.close();
		}
		s.set(null);
	}
}
