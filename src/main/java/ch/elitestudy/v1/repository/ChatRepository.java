package ch.elitestudy.v1.repository;

import ch.elitestudy.v1.model.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {


    @Query(value = "SELECT chat.msgfrom, chat.msgto, chat.allowchat FROM chatrequest INNER JOIN chat ON chatrequest.msgfrom = chat.msgfrom AND chatrequest.msgto = chat.msgto WHERE chatrequest.request = true AND chatrequest.msgto = :to", nativeQuery = true)
    List<Chat> chatOverview(@Param("to") String to);//Which are chat allowed

    @Query(value = "SELECT COUNT(chat.msgto) FROM chat WHERE chat.msgto = :receiver AND chat.isread = false", nativeQuery = true)
    Integer countUnread(@Param("receiver") String receiver);

    @Query(value = "UPDATE chat SET isread = true WHERE chat.msgto = :receiver AND chat.msgfrom = :sender", nativeQuery = true)
    void setRequestTrue(@Param("receiver") String receiver, @Param("sender") String sender);

    List<Chat> findByFromAndToOrderByTimestampDesc(String from, String to);

    List<Chat> findByToAndFromOrderByTimestampDesc(String to, String from);

    @Query(value = "FROM Chat chat WHERE chat.from = :from AND chat.to = :to OR chat.from = :to AND chat.to = :from ORDER BY chat.timestamp DESC")
    List<Chat> findChats(@Param("to") String to, @Param("from") String from);


    //ALL THINGS WITH REQUESTS

    @Query("FROM Chat WHERE to = ?1 AND allowChat = false ORDER BY timestamp DESC")
    List<Chat> findWhereAllowedFalse(String receiver);




    @Query("SELECT count(to)  from Chat  WHERE to = :receiver AND allowChat = false")
    Integer countRequests(@Param("receiver")String receiver);


    @Modifying
    @Transactional
    @Query("UPDATE Chat c set c.allowChat = true where c.to = :receiver AND c.from = :sender")
    void acceptRequest(@Param("receiver")String receiver, @Param("sender")String sender);


    @Query(value = "FROM Chat c WHERE c.to = :receiver AND c.allowChat = true GROUP BY c.from")
    List<Chat> ChatRequestCheck(@Param("receiver")String receiver);


    @Query(value = "FROM Chat c WHERE c.to = :receiver AND c.from = :sender AND c.allowChat = true OR c.to = :sender AND c.from = :receiver AND c.allowChat = true GROUP BY c.from")
    List<Chat> ChatRequestCheck(@Param("receiver")String receiver, @Param("sender")String sender);


}
