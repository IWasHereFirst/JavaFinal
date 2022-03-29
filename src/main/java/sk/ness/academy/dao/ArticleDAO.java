package sk.ness.academy.dao;

import sk.ness.academy.domain.Article;
import sk.ness.academy.dto.ArticleJ;

import java.util.List;

public interface ArticleDAO {

	  /** Returns {@link Article} with provided ID */
	  Article findByID(Integer articleId);

	  /** Returns all available {@link Article}s */
	  List<ArticleJ> findAll();

	  /** Persists {@link Article} into the DB */
	  void persist(Article article);

	  /** Deletes {@link Article} from DB*/
	  void delete(Integer article);

	  /** Search {@link Article} from DB*/
	  List<Article> searchArticles(String text);
	}
