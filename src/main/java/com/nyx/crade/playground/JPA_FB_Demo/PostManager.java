package com.nyx.crade.playground.JPA_FB_Demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.nyx.crade.playground.JPA_FB_Demo.entity.FbPost;

public class PostManager {
	protected SessionFactory sessionFactory;

	protected void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		MetadataSources sources = new MetadataSources(registry);
		Metadata metadata = sources.buildMetadata();
		sessionFactory = metadata.buildSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.getTransaction().commit();
		session.close();
	}

	protected void create(FbPost post) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(post);

		session.getTransaction().commit();
		session.close();
	}

}
