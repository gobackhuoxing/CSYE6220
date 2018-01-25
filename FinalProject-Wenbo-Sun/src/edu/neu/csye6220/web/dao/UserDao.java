package edu.neu.csye6220.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.csye6220.web.domain.User;

@Transactional
@Component("userDao")
public class UserDao {

	@Autowired
	private SessionFactory sessionfactory;
	
	public org.hibernate.Session getSession(){
		return  sessionfactory.getCurrentSession();
	}
	
	@Transactional
	public void create(User user) {
		getSession().save(user);
	}

	public boolean exists(String username) {
		Criteria cr;	
		User user;
		cr= getSession().createCriteria(User.class);
		cr.add(Restrictions.idEq(username));
		user= (User) cr.uniqueResult();
		return user != null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return getSession().createQuery("from User").list();
	}


	@SuppressWarnings("unchecked")
	public List<User> getUsers(String username) {
		Criteria cr =getSession().createCriteria(User.class);
		cr.add(Restrictions.eq("enabled",true));
		cr.add(Restrictions.eq("username",username));
		return cr.list();
	}

	public void manage(boolean enabled, String authority, String username) {
		Query q1;
		Query q2;
		q1=getSession().createQuery("update User set enabled=:enabled"+ " where username=:username");
		q2=getSession().createQuery("update User set authority = :authority"+ " where username=:username");
		q1.setParameter("enabled", enabled);
		q2.setParameter("authority", authority);
		q1.setParameter("username", username);
		q2.setParameter("username", username);
		q1.executeUpdate();
		q2.executeUpdate();
	}

	
}
