package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import webtracker.Group;

public class GroupDAO extends TrackerDAO {
	
	public static void createGroup(Group group) throws HibernateException, Exception{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(group);
		session.getTransaction().commit();
		session.close();
	}

}
