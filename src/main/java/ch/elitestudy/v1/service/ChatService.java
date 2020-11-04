package ch.elitestudy.v1.service;

import ch.elitestudy.v1.model.Chat;

import java.util.List;

public interface ChatService {

    List<Chat> getAllChats();
    void saveChat(Chat chat);
    Chat getChatById(long id);
    void deleteChatById(long id);




    List<Chat> ChatOverview(String receiver);

    List<Chat> findChats(String from, String to);

    Integer countUnread(String receiver);
void setRequestTrue(String receiver, String sender);


    //REQUESt
    void acceptRequest(String from, String to);
    Integer countRequests(String receiver);
    List<Chat> findWhereAllowedFalse(String receiver);

    List<Chat> ChatRequestCheck(String receiver);
    List<Chat> ChatRequestCheck(String receiver, String sender);
}
