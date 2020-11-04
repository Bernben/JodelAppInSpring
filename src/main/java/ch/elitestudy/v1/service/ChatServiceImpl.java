package ch.elitestudy.v1.service;

import ch.elitestudy.v1.model.Chat;
import ch.elitestudy.v1.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public void saveChat(Chat chat) {
        this.chatRepository.save(chat);
    }

    @Override
    public Chat getChatById(long id) {
        Optional<Chat> optional = chatRepository.findById(id);
        Chat chat = null;
        if (optional.isPresent()) {
            chat = optional.get();
        } else {
            throw new RuntimeException(" Chat not found for id :: " + id);
        }
        return chat;
    }


    @Override
    public void deleteChatById(long id) {
        this.chatRepository.deleteById(id);
    }


    @Override
    public List<Chat> ChatOverview(String receiver) {
        return this.chatRepository.chatOverview(receiver);

    }

    @Override
    public List<Chat> findChats(String from, String to) {
       return this.chatRepository.findChats(from, to);

    }


    @Override
    public void setRequestTrue(String receiver, String sender) {

        this.chatRepository.setRequestTrue(receiver, sender);
    }

    @Override
    public Integer countUnread(String receiver) {
        return this.chatRepository.countUnread(receiver);

    }


    //// REQUESTS




    @Override
    public void acceptRequest(String from, String to) {
        this.chatRepository.acceptRequest(from, to);
    }

    @Override
    public List<Chat> findWhereAllowedFalse(String receiver) {
        return this.chatRepository.findWhereAllowedFalse(receiver);
    }

    @Override
    public Integer countRequests(String receiver)
    {
        return this.chatRepository.countRequests(receiver);
    }
    @Override
    public List<Chat> ChatRequestCheck(String receiver) {
        return  this.chatRepository.ChatRequestCheck(receiver);
    }

    @Override
    public List<Chat> ChatRequestCheck(String receiver, String sender) {

        return  this.chatRepository.ChatRequestCheck(receiver, sender);
    }

//    @Override
//    public Page<ChatRequest> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//                Sort.by(sortField).descending();
//
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//        return this.chatRequestRepository.findById(pageable);
//    }

//	@Override
//	public Page<Comment> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
//		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//				Sort.by(sortField).descending();
//
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//		return this.commentRepository.findAll(pageable);
//	}


}

