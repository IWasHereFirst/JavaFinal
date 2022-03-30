package sk.ness.academy.service;

import sk.ness.academy.domain.Comment;

import java.util.List;

public interface CommentService {

	  /** Returns {@link Comment} with provided ID */
	  Comment findByID(Integer commentId);

	  /** Returns all available {@link Comment}s */
	  List<Comment> findAll();

	  /** Creates new {@link Comment} */
	  void createComment(Integer articleId, Comment comment);

	  /** Deletes {@link Comment} */
	  void deleteComment(Integer comment);

	  /** Creates new {@link Comment}s by ingesting all articles from json */
	  void ingestComment(String jsonComment);

}
