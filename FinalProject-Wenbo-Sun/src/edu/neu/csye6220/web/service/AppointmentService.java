package edu.neu.csye6220.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.web.dao.AppointmentDao;
import edu.neu.csye6220.web.domain.Appointment;

@Service("appointmentService")
public class AppointmentService {
	
	private AppointmentDao appointmentDao;
	
	@Autowired
	public void setAppointmentsDao(AppointmentDao appointmentDao) {
		this.appointmentDao = appointmentDao;
	}

	public List<Appointment> getAppointment() {
		return appointmentDao.getAppointments();
	}
	
	public Appointment getAppointment(String username) {
		if(username == null) return null;
	
		List<Appointment> appointments = appointmentDao.getAppointments(username);
		
		if(appointments.size()==0) return null;
		return appointments.get(0);
	}

	public void create(Appointment appointment) {
		appointmentDao.update(appointment);
	}

	public boolean hasAppointment(String username) {
		if(username==null) return false;
		List<Appointment> appointments = appointmentDao.getAppointments(username);
		if(appointments.size()==0) return false;
		return true;
	}

	public void update(Appointment appointment) {
			appointmentDao.update(appointment);
	}

	public void delete(int id) {
		appointmentDao.delete(id);
	}

	public void confirm(String username, String confirm) {
		appointmentDao.confirm(username,confirm);
	}

}
