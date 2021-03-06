package sk.ness.academy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import sk.ness.academy.dao.ArticleDAO;
import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticleJ;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

  @Resource
  private ArticleDAO articleDAO;

  @Override
  public Article findByID(final Integer articleId) {
	  return this.articleDAO.findByID(articleId);
  }

  @Override
  public List<ArticleJ> findAll() {
	  return this.articleDAO.findAll();
  }

  @Override
  public void createArticle(final Article article) {
	  this.articleDAO.persist(article);
  }

  @Override
  public void deleteArticle(Integer article) {
    this.articleDAO.delete(article);
  }

  @Override
  public List<ArticleJ> searchArticles(String text) {
    return this.articleDAO.searchArticles(text);
  }

  @Override
  public void ingestArticles(final String jsonArticles) {
    ObjectMapper objMapper = new ObjectMapper();
    try{
      Article[] article = objMapper.readValue(new File(jsonArticles), Article[].class);
      Arrays.stream(article).forEach(a -> this.articleDAO.persist(a));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
