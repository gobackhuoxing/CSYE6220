package edu.neu.csye6220.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.neu.csye6220.web.domain.Appointment;
import edu.neu.csye6220.web.service.AppointmentService;

@Controller
public class AppointmentController {
	
	private AppointmentService appointmentService;
	
	@Autowired
	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@RequestMapping("/myappointment")
	public String myAppointment(Model model, HttpServletRequest request) {
		
		String username = request.getParameter("username");
		
		
		Appointment appointment = appointmentService.getAppointment(username);
		
		model.addAttribute("appointment", appointment);
		
		return "myappointment";
	}
	
	@RequestMapping("/newappointment")
	public String newAppointment(Model model, Principal principal) {
		Appointment appointment = null;
		
		if(principal!= null){
			String username=principal.getName();
			appointment = appointmentService.getAppointment(username);
		}
		
		if(appointment==null) appointment = new Appointment();
		model.addAttribute("appointments", appointment);
		return "newappointment";
	}
	
	@RequestMapping(value = "/appointmentcreated", method = RequestMethod.POST)
	public String createAppointment(Model model, @Valid Appointment appointment,
			BindingResult result, Principal principal,
			@RequestParam(value = "delete", required = false) String delete) {

		if (result.hasErrors()) {
			return "newappointment";
		}
		
		if(delete == null) {
			String username = principal.getName();
			appointment.getUser().setUsername(username);
			appointmentService.update(appointment);
			return "appointmentcreated";
		}
		else {
			appointmentService.delete(appointment.getId());
			return "appointmentdeleted";
		}
	}
	
	@RequestMapping("/confirmappointment")
	public String confirmAppointment(Model model,Appointment appointment, HttpServletRequest request) {
		List<Appointment> appointments = appointmentService.getAppointment();
		
		model.addAttribute("appointments", appointments);
		
		return "confirmappointment";
	}
	
	@RequestMapping("/confirm")
	public String confirm(Model model,HttpServletRequest request){
		String username=request.getParameter("username");
		Appointment appointment = appointmentService.getAppointment(username);
		model.addAttribute("appointment", appointment);
		
		return "confirm";
	}
	
	@RequestMapping(value = "/confirmed", method = RequestMethod.POST)
	public String confirmed(Model model, HttpServletRequest request){
		String username = request.getParameter("username");
		String confirm = request.getParameter("confirm");	
		appointmentService.confirm(username,confirm);
		
		return "home";
		
	}
}
