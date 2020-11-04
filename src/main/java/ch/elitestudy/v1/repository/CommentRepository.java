package ch.elitestudy.v1.repository;

import ch.elitestudy.v1.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    @Query("FROM Comment WHERE feedId = ?1 ORDER BY timestamp DESC")
    List<Comment> findbyFeed_id(Long feed_Id);




}
