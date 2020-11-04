package ch.elitestudy.v1.repository;

import ch.elitestudy.v1.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface FeedRepository extends JpaRepository<Feed, Long>{

    @Transactional
    @Modifying
    @Query("UPDATE Feed SET likes = likes + 1 WHERE id = ?1")
    void likeFeed(Long feed_Id);

    @Transactional
    @Modifying
    @Query("UPDATE Feed SET likes = likes - 1 WHERE id = ?1")
    void dislikeFeed(Long feed_Id);

}
