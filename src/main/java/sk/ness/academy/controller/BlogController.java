package sk.ness.academy.controller;

import org.springframework.web.bind.annotation.*;
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

@RestController
public class BlogController {

  @Resource
  private ArticleService articleService;

  @Resource
  private CommentService commentService;

  @Resource
  private AuthorService authorService;

  // ~~ Article
  @RequestMapping(value = "articles", method = RequestMethod.GET)
  public List<ArticleJ> getAllArticles() {
	  return this.articleService.findAll();
  }

  @RequestMapping(value = "articles/{articleId}", method = RequestMethod.GET)
  public Article getArticle(@PathVariable final Integer articleId) {
    return this.articleService.findByID(articleId);
  }

  @RequestMapping(value = "articles/search/{searchText}", method = RequestMethod.GET)
  public List<Article> searchArticle(@PathVariable final String searchText) {
	  return this.articleService.searchArticles(searchText);
  }

  @RequestMapping(value = "articles", method = RequestMethod.PUT)
  public void addArticle(@RequestBody final Article article) {
	  this.articleService.createArticle(article);
  }

  @RequestMapping(value = "articles/{articleId}", method = RequestMethod.DELETE)
  public void deleteArticle(@PathVariable final Integer articleId) {
    this.articleService.deleteArticle(articleId);
  }

  //~~ Comment

  // Display all comments associated with one article
  @RequestMapping(value = "articles/{articleId}/comments", method = RequestMethod.GET)
  public List<Comment> getAllComments(@PathVariable final Integer articleId) {
    return this.commentService.findAll(articleId);
  }

  // Display one specific comment
  @RequestMapping(value = "articles/{articleId}/comments/{commentId}", method = RequestMethod.GET)
  public Comment getComment(@PathVariable final Integer articleId, @PathVariable final Integer commentId) {
    return this.commentService.findByID(articleId, commentId);
  }

  // Create new comment
  @RequestMapping(value = "articles/{articleId}/comments", method = RequestMethod.PUT)
  public void addComment(@PathVariable final Integer articleId, @RequestBody final Comment comment) {
    this.commentService.createComment(articleId, comment);
  }

  // Delete specific comment
  //TODO Chytit NullPointerException
  @RequestMapping(value = "articles/{articleId}/comments/{commentId}", method = RequestMethod.DELETE)
  public void removeComment(@PathVariable final Integer articleId, @PathVariable final Integer commentId){
    this.commentService.deleteComment(articleId, commentId);
  }

  @RequestMapping(value = "articles/{articleId}/comments", method = RequestMethod.DELETE)
  public void removeAllComments(@PathVariable final Integer articleId){
    this.commentService.deleteAllComments(articleId);
  }

  // ~~ Author
  @RequestMapping(value = "authors", method = RequestMethod.GET)
  public List<Author> getAllAuthors() {
	  return this.authorService.findAll();
  }

  @RequestMapping(value = "authors/stats", method = RequestMethod.GET)
  public List<AuthorStats> authorStats() {
    //TODO findAllWithNumArt
	  throw new UnsupportedOperationException("Author statistics not implemented.");
  }

}
