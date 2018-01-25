package edu.neu.csye6220.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.csye6220.web.domain.Comment;

@Component("commentDao")
@Transactional
public class CommentDao {

	@Autowired
	private SessionFactory sessionfactory;
	
	public org.hibernate.Session getSession(){
		return  sessionfactory.getCurrentSession();
	}
	
	public Comment getComment(int id) {
		Criteria cr =getSession().createCriteria(Comment.class);
		cr.createAlias("user", "u");
		cr.add(Restrictions.eq("u.enabled",true));
		cr.add(Restrictions.idEq(id));
		return (Comment) cr.uniqueResult();
		}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getComments() {
		Criteria cr =getSession().createCriteria(Comment.class);
		cr.createAlias("user", "u");
		cr.add(Restrictions.eq("u.enabled",true));
		return cr.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getComments(int id) {
		Criteria cr =getSession().createCriteria(Comment.class);
		cr.createAlias("user", "u");
		cr.add(Restrictions.eq("u.enabled",true));
		cr.add(Restrictions.eq("id",id));
		return cr.list();
	}
	
	public void update(Comment comment) {
		getSession().saveOrUpdate(comment);
	}
	
	public void delete(int id) {
		Query q;
		q=getSession().createQuery("delete from Comment where id =:id");
		q.setLong("id", id);
		q.executeUpdate();
	}	
	
}
