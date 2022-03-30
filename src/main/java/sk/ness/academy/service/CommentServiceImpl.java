package sk.ness.academy.service;

import org.springframework.stereotype.Service;
import sk.ness.academy.dao.CommentDAO;
import sk.ness.academy.domain.Comment;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

  @Resource
  private CommentDAO commentDAO;

  @Override
  public Comment findByID(final Integer articleId, final Integer commentId) {
	  return this.commentDAO.findByID(articleId, commentId);
  }

  @Override
  public List<Comment> findAll(final Integer articleId) {
	  return this.commentDAO.findAll(articleId);
  }

  @Override
  public void createComment(final Integer articleId, final Comment comment) {
	  this.commentDAO.persist(articleId, comment);
  }

  @Override
  public void deleteComment(Integer articleId, Integer commentId) {
    this.commentDAO.delete(articleId, commentId);
  }
}
