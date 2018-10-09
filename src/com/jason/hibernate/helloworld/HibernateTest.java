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
		
		//����һ��SessionFactory ����
		SessionFactory sessionFactory = null;
		
		//����Configuration ���󣺶�Ӧhibernate�Ļ���������Ϣ�Ͷ����ϵӳ����Ϣ
		Configuration configuration = new Configuration().configure();
		
		//����һ��ServiceRegistry����hibernate 4.x ����ӵĶ���
		//hiberbate���κ����úͷ�����Ҫ�ڸö�����ע��������Ч
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
		
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		//����һ��Session����
		Session session = sessionFactory.openSession();
		
		//��������
		Transaction transaction = session.beginTransaction();
		
		//ִ�б������
		News news = new News("Title-test", "Author-test", new Date(new java.util.Date().getTime()));
		session.save(news);
		
		//�ύ����
		transaction.commit();
		
		//�ر�session
		session.close();
		
		//�ر�sessionFactory����
		sessionFactory.close();
	}

}
