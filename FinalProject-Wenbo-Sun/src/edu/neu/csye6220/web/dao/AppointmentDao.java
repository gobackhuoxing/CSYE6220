package edu.neu.csye6220.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.csye6220.web.domain.Appointment;

@Component("appointmentDao")
@Transactional
public class AppointmentDao {
	
	@Autowired
	private SessionFactory sessionfactory;
	
	public org.hibernate.Session getSession(){
		return  sessionfactory.getCurrentSession();
	}
	
	public Appointment getAppointment(int id) {
		Criteria cr =getSession().createCriteria(Appointment.class);
		cr.createAlias("user", "u");
		cr.add(Restrictions.eq("u.enabled",true));
		cr.add(Restrictions.idEq(id));
		return (Appointment) cr.uniqueResult();
		}
	
	@SuppressWarnings("unchecked")
	public List<Appointment> getAppointments() {
		Criteria cr =getSession().createCriteria(Appointment.class);
		cr.createAlias("user", "u");
		cr.add(Restrictions.eq("u.enabled",true));
		return cr.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Appointment> getAppointments(String username) {
		Criteria cr =getSession().createCriteria(Appointment.class);
		cr.createAlias("user", "u");
		cr.add(Restrictions.eq("u.enabled",true));
		cr.add(Restrictions.eq("u.username",username));
		return cr.list();
	}
	
	public void update(Appointment appointment) {
		getSession().saveOrUpdate(appointment);
	}
	
	public void delete(int id) {
		Query q;
		q=getSession().createQuery("delete from Appointment where id =:id");
		q.setLong("id", id);
		q.executeUpdate();
	}	
	
	public void confirm(String username, String confirm) {
		Query q;
		q=getSession().createQuery("update Appointment set confirm=:confirm"+ " where username=:username");
		q.setParameter("confirm", confirm);
		q.setParameter("username", username);
		q.executeUpdate();
	}
}
