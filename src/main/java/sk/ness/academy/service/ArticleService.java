package sk.ness.academy.service;

import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticleJ;

import java.util.List;

public interface ArticleService {

	  /** Returns {@link Article} with provided ID */
	  Article findByID(Integer articleId);

	  /** Returns all available {@link Article}s */
	  List<ArticleJ> findAll();

	  /** Creates new {@link Article} */
	  void createArticle(Article article);

	  /** Deletes {@link Article} */
	  void deleteArticle(Integer article);

	  /** Creates new {@link Article}s by ingesting all articles from json */
	  void ingestArticles(String jsonArticles);

	  /** Search {@link Article} */
	  List<ArticleJ> searchArticles(String text);

}
