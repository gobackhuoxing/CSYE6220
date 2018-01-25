package edu.neu.csye6220.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.csye6220.web.domain.User;
import edu.neu.csye6220.web.service.UsersService;

@Controller
public class UserController {

	private UsersService usersService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/map")
	public String map() {
		return "map";
	}
	
	@RequestMapping("/denied")
	public String denied() {
		return "denied";
	}

	@RequestMapping("/admin")
	public String admin(Model model) {

		List<User> users = usersService.getUsers();
		model.addAttribute("users", users);

		return "admin";
	}

	@RequestMapping("/logout")
	public String logout() {
		return "logout";
	}
	

	@RequestMapping("/newaccount")
	public String newaccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createaccount(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "newaccount";
		}
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username", "username exist");
			return "newaccount";
		}

		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getClass());
			result.rejectValue("username", "DuplicateKey.user.username", "username exist");
			return "newaccount";
		}

		return "accountcreated";
	}
	
	@RequestMapping("/management")
	public String management(Model model,HttpServletRequest request) {
		String username=request.getParameter("username");
		User user = usersService.getUser(username);
		model.addAttribute("user", user);
		return "management";
	}
	
	@RequestMapping(value = "/managementuser", method = RequestMethod.POST)
	public String managementUser(Model model, HttpServletRequest request){
		String username = request.getParameter("username");
		String authority = request.getParameter("authority");
		boolean enabled = Boolean.parseBoolean(request.getParameter("enabled"));
		usersService.manage(enabled,authority,username);
		return "home";
	}
}
