package ch.elitestudy.v1.service;

import ch.elitestudy.v1.model.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

	List<Comment> getAllComment();
	void saveComment(Comment comment);
	Comment getCommentById(long id);
	void deleteCommentById(long id);
	List<Comment> findbyFeed_id(Long feedId);
	Page<Comment> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
