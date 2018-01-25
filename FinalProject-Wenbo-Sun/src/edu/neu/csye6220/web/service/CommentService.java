package edu.neu.csye6220.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.web.dao.CommentDao;
import edu.neu.csye6220.web.domain.Comment;

@Service("commentService")
public class CommentService {

	private CommentDao commentdao;

	@Autowired
	public void setCommentdao(CommentDao commentdao) {
		this.commentdao = commentdao;
	}
	
	public List<Comment> getConmments() {
		return commentdao.getComments();
	}
	
	public void update(Comment comment) {
		commentdao.update(comment);	
	}
	
	public Comment getComment(int id) {
		List<Comment> comments = commentdao.getComments(id);
		if(comments.size()==0) return null;
		return comments.get(0);
	}

	public void updateComment(Comment comment) {
			commentdao.update(comment);
	}

	public void delete(int id) {
		commentdao.delete(id);
	}
}
