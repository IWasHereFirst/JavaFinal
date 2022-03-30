package sk.ness.academy.dao;

import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Repository
public class CommentHibernateDAO implements CommentDAO {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public Comment findByID(final Integer articleId, final Integer commentId) {
    Comment comment = this.sessionFactory.getCurrentSession().get(Comment.class, commentId);
    if (comment != null && Objects.equals(articleId, comment.getArticle_id())){
      return comment;
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Comment> findAll(Integer articleId) {
    List list = this.sessionFactory.getCurrentSession().createSQLQuery("select * from comments where article_id = '" + articleId + "'")
            .addEntity(Comment.class).list();
    if (list.size() != 0){
      return list;
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public void persist(final Integer articleId, final Comment comment) {
    Article article = this.sessionFactory.getCurrentSession().get(Article.class, articleId);
    if (article != null){
      Comment comment1 = comment;
      comment1.setArticle_id(articleId);
      this.sessionFactory.getCurrentSession().saveOrUpdate(comment1);
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public void delete(Integer articleId, Integer commentId) {
    Comment comment = (Comment) this.sessionFactory.getCurrentSession().get(Comment.class, commentId);
    if (comment != null && Objects.equals(articleId, comment.getArticle_id())){
      this.sessionFactory.getCurrentSession().delete(comment);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public void deleteAll(Integer articleId) {
    List list = this.sessionFactory.getCurrentSession().createSQLQuery("select * from comments where article_id = '" + articleId + "'")
            .addEntity(Comment.class).list();
    if (list.size() != 0){
      list.forEach(item -> this.sessionFactory.getCurrentSession().delete(item));
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
}
