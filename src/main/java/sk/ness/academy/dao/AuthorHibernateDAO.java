package sk.ness.academy.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import sk.ness.academy.dto.Author;
import sk.ness.academy.dto.AuthorStats;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AuthorHibernateDAO implements AuthorDAO {

  @Resource(name = "sessionFactory")
  private SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  @Override
  public List<Author> findAll() {
    return this.sessionFactory.getCurrentSession().createQuery("select distinct new sk.ness.academy.dto.Author(author) from Article", Author.class).list();
  }

  @Override
  public List<AuthorStats> getAuthorsStats() {
    return this.sessionFactory.getCurrentSession()
            .createQuery("select new sk.ness.academy.dto.AuthorStats(count(id), author) from Article group by author", AuthorStats.class).list();
  }
}

