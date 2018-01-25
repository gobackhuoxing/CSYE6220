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

import edu.neu.csye6220.web.domain.Comment;
import edu.neu.csye6220.web.service.CommentService;

@Controller
public class CommentController {
	private CommentService commentservice;

	@Autowired
	public void setCommentservice(CommentService commentservice) {
		this.commentservice = commentservice;
	}
	
	@RequestMapping("/comment")
	public String comment(Model model) {
	
		List<Comment> comments = commentservice.getConmments();
		
		model.addAttribute("comments", comments);
		
		return "comment";
	}
	
	@RequestMapping("/newcomment")
	public String newcomment(Model model) {
		Comment comment = null;
		comment = new Comment();
		model.addAttribute("comments", comment);
		return "newcomment";
	}
	
	@RequestMapping("/checkcomment")
	public String checkComment(Model model,HttpServletRequest request) {
		int id=Integer.valueOf(request.getParameter("id"));
		Comment comment = commentservice.getComment(id);
		model.addAttribute("comment", comment);
		return "checkcomment";
	}
		
	@RequestMapping(value = "/commentcreated", method = RequestMethod.POST)
	public String commentcreated(Model model, @Valid Comment comment, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return "newcomment";
		}
		else{
			String username = principal.getName();
			comment.getUser().setUsername(username);
			commentservice.updateComment(comment);
			return "commentcreated";
		}
	}
}
