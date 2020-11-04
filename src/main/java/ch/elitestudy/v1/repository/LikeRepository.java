package ch.elitestudy.v1.repository;

import ch.elitestudy.v1.model.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LikeRepository extends JpaRepository<Chat, Long>{

    @Transactional
    @Modifying //SELECT * FROM chat WHERE  chat.msgfrom = :feedid OR  chat.msgto = :username  ORDER BY timestamp DESC
   @Query(value = "INSERT INTO likes (feed_Id, username) VALUES (:feedid, :username)" , nativeQuery=true)
    void likeDb(@Param("feedid")long feedID, @Param("username") String username);



    @Query(value = "INSERT INTO dislikes (feed_Id, username) VALUES (:feedid, :username)" , nativeQuery=true)
    void dislikeDb(@Param("feedid")long feedID, @Param("username") String username);




}
