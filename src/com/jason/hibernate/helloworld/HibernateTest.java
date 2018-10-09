package com.jason.hibernate.helloworld;

import static org.junit.Assert.*;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {
		
		//创建一个SessionFactory 对象
		SessionFactory sessionFactory = null;
		
		//创建Configuration 对象：对应hibernate的基本配置信息和对象关系映射信息
		Configuration configuration = new Configuration().configure();
		
		//创建一个ServiceRegistry对象：hibernate 4.x 新添加的对象
		//hiberbate的任何配置和服务都需要在该对象中注册后才能有效
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
		
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		//创建一个Session对象
		Session session = sessionFactory.openSession();
		
		//开启事务
		Transaction transaction = session.beginTransaction();
		
		//执行保存操作
		News news = new News("Title-test", "Author-test", new Date(new java.util.Date().getTime()));
		session.save(news);
		
		//提交事务
		transaction.commit();
		
		//关闭session
		session.close();
		
		//关闭sessionFactory对象
		sessionFactory.close();
	}

}
