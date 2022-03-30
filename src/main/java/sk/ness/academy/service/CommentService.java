package sk.ness.academy.service;

import sk.ness.academy.domain.Comment;

import java.util.List;

public interface CommentService {

	  /** Returns {@link Comment} with provided ID */
	  Comment findByID(Integer articleId, Integer commentId);

	  /** Returns all available {@link Comment}s */
	  List<Comment> findAll(Integer articleId);

	  /** Creates new {@link Comment} */
	  void createComment(Comment comment);

	  /** Deletes {@link Comment} */
	  void deleteComment(Comment comment);

	  /** Deletes all {@link Comment} */
	  void deleteAllComments(Comment comment);

}
