package sk.ness.academy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.ness.academy.domain.Article;
import sk.ness.academy.domain.Comment;
import sk.ness.academy.dto.ArticleJ;
import sk.ness.academy.dto.Author;
import sk.ness.academy.dto.AuthorStats;
import sk.ness.academy.service.ArticleService;
import sk.ness.academy.service.AuthorService;
import sk.ness.academy.service.CommentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
public class BlogController {

  @Resource
  private ArticleService articleService;

  @Resource
  private CommentService commentService;

  @Resource
  private AuthorService authorService;

  // ~~ Article

  // Displays specific article
  @RequestMapping(value = "articles/{articleId}", method = RequestMethod.GET)
  public Article getArticle(@PathVariable final Integer articleId) {
    try {
      return this.articleService.findByID(articleId);
    } catch (NullPointerException e){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  // Displays all articles
  @RequestMapping(value = "articles", method = RequestMethod.GET)
  public List<ArticleJ> getAllArticles() {
	  return this.articleService.findAll();
  }

  // Searches specific article
  @RequestMapping(value = "articles/search/{searchText}", method = RequestMethod.GET)
  public List<ArticleJ> searchArticle(@PathVariable final String searchText) {
	  return this.articleService.searchArticles(searchText);
  }

  // Creates new article
  @RequestMapping(value = "articles", method = RequestMethod.PUT)
  public void addArticle(@RequestBody final Article article) {
	  this.articleService.createArticle(article);
  }

  // Deletes article
  @RequestMapping(value = "articles/{articleId}", method = RequestMethod.DELETE)
  public void deleteArticle(@PathVariable final Integer articleId) {
    try{
      this.articleService.deleteArticle(articleId);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  //~~ Comment

  // Displays all comments associated with one specific article
  @RequestMapping(value = "articles/{articleId}/comments", method = RequestMethod.GET)
  public List<Comment> getAllComments(@PathVariable final Integer articleId) {
    List list = this.commentService.findAll(articleId);
    if (list.size() != 0){
      return list;
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  // Displays one specific comment
  @RequestMapping(value = "articles/{articleId}/comments/{commentId}", method = RequestMethod.GET)
  public Comment getComment(@PathVariable final Integer articleId, @PathVariable final Integer commentId) {
    Comment comment = this.commentService.findByID(articleId, commentId);
    if (comment != null && Objects.equals(articleId, comment.getArticle_id())){
      return comment;
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  // Creates new comment
  @RequestMapping(value = "articles/{articleId}/comments", method = RequestMethod.PUT)
  public void addComment(@PathVariable final Integer articleId, @RequestBody final Comment comment) {
    Article article = getArticle(articleId);
    if (article != null){
      Comment comment1 = comment;
      comment1.setArticle_id(articleId);
      this.commentService.createComment(comment);
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  // Deletes specific comment
  @RequestMapping(value = "articles/{articleId}/comments/{commentId}", method = RequestMethod.DELETE)
  public void removeComment(@PathVariable final Integer articleId, @PathVariable final Integer commentId){
    Comment comment = getComment(articleId, commentId);
    if (comment != null && Objects.equals(articleId, comment.getArticle_id())){
      this.commentService.deleteComment(comment);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  // Deletes all comments in the article
  @RequestMapping(value = "articles/{articleId}/comments", method = RequestMethod.DELETE)
  public void removeAllComments(@PathVariable final Integer articleId){
    List<Comment> list = getAllComments(articleId);
    if (list.size() != 0){
      list.forEach(item -> this.commentService.deleteAllComments(item));
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  // ~~ Author

  // Displays all authors
  @RequestMapping(value = "authors", method = RequestMethod.GET)
  public List<Author> getAllAuthors() {
	  return this.authorService.findAll();
  }

  // Displays all authors with their statistics
  @RequestMapping(value = "authors/stats", method = RequestMethod.GET)
  public List<AuthorStats> authorStats() {
    return this.authorService.getAuthorsStats();
  }

}
