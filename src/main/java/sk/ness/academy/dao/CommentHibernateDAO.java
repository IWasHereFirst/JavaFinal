package sk.ness.academy.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import sk.ness.academy.domain.Comment;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CommentHibernateDAO implements CommentDAO {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public Comment findByID(final Integer commentId) {
      return this.sessionFactory.getCurrentSession().get(Comment.class, commentId);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Comment> findAll(Integer articleId) {
      return this.sessionFactory.getCurrentSession().createSQLQuery("select * from comments where article_id = '" + articleId + "'").addEntity(Comment.class).list();
  }

  @Override
  public void persist(final Comment comment) {
      this.sessionFactory.getCurrentSession().saveOrUpdate(comment);
  }

  @Override
  public void delete(Comment comment) {
      this.sessionFactory.getCurrentSession().delete(comment);
  }

  @Override
  public void deleteAll(Comment comment) {
    this.sessionFactory.getCurrentSession().delete(comment);
  }
}
