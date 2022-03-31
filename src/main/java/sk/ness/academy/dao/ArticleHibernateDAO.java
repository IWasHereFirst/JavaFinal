package sk.ness.academy.dao;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticleJ;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ArticleHibernateDAO implements ArticleDAO {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public Article findByID(final Integer articleId) {
    Article article = this.sessionFactory.getCurrentSession().get(Article.class, articleId);
    Hibernate.initialize(article.getComments());
    return article;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ArticleJ> findAll() {
    return this.sessionFactory.getCurrentSession().createQuery("select new sk.ness.academy.dto.ArticleJ(id, title, text, author, createTimestamp) from Article", ArticleJ.class).list();
  }

  @Override
  public void persist(final Article article) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(article);
  }

  @Override
  public void delete(Integer articleId) {
    Article article = (Article) this.sessionFactory.getCurrentSession().get(Article.class, articleId);
    this.sessionFactory.getCurrentSession().delete(article);
    this.sessionFactory.getCurrentSession().flush();
  }

  @Override
  public List<ArticleJ> searchArticles(String text) {
    return this.sessionFactory.getCurrentSession()
            .createQuery("select new sk.ness.academy.dto.ArticleJ(id, title, text, author, createTimestamp) from Article where author like '%"+text+"%' OR title like '%"+text+"%' OR text like '%"+text+"%'", ArticleJ.class).list();
  }
}
