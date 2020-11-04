package ch.elitestudy.v1.controller;

import ch.elitestudy.v1.model.Chat;
import ch.elitestudy.v1.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;




    //ADDS The Unread Count to the Menu Icon
    @ModelAttribute("unreadMSG")
    public Integer unreadMSG() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return chatservice.countUnread(username);
    }





    @GetMapping("/deleteRequest/{id}")
    public String deleteRequest(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.chatService.deleteChatById(id);
        return "redirect:/chatreq";
    }

    @PostMapping("/saveRequest")
    public String saveFeed(@ModelAttribute("Chat") Chat chat) {

        Timestamp ts = new Timestamp(System.currentTimeMillis());

        chat.setTimestamp(ts);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        chat.setFrom(username);
        chat.setAllowChat(false);
        try {
            chatService.saveChat(chat);
        } catch (Exception e) {
            return "error";
        }


        return "redirect:/chat";
    }


    //FOR CHAT NORMAL

    @Autowired
    private ChatService chatservice;

    //Simple Get Mapping to delete a Chat
    @GetMapping("/deleteChat/{id}")
    public String deleteChat(@PathVariable(value = "id") long id) {
        this.chatservice.deleteChatById(id);
        return "redirect:/chat";
    }

    //Post Mapping and JPA save from Chat message, whith some given properties..
    @PostMapping("/saveChat")
    public String saveChat(@ModelAttribute("Chat") Chat chat) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        chat.setTimestamp(ts);
        chat.setRead(false);
        chat.setAllowChat(true);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        chat.setFrom(username);
        String to = chat.getTo();
        chatservice.saveChat(chat);
        return "redirect:/chat/" + to;
    }

    // Simple Get Request to accept a Chat
    @GetMapping("/acceptRequest/{id}")
    public String acceptChat(@PathVariable(value = "id") String to) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        this.chatService.acceptRequest(username, to);
        return "redirect:/chat/" + to;

    }


    @GetMapping("/chat/{to}")
    public String chatView2(Model model, @PathVariable(value = "to") String to) {
        //Below are the things needed to display User to user chat and save..
        Chat chat3 = new Chat();
        model.addAttribute("chatadd", chat3); //To ADD a chat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);

        //Set some given properties
        chat3.setFrom(username);
        chat3.setTo(to);
        chat3.setRead(false);

        // IF Page gets opened set Chat read Boolean= TRUE
        chatservice.setRequestTrue(username, to);
        ///
        //Request Counter for Chats which has Chat Allow boolean false
        model.addAttribute("requestCount", chatService.countRequests(username));
        List<Chat> listRequests = chatService.findWhereAllowedFalse(username);
        model.addAttribute("listRequests", listRequests);
        //////////

        // Checks if The Request has been accepted
        List<Chat> testrequest = chatService.ChatRequestCheck(username, to);
        if (testrequest.isEmpty()) {
            return "error";  // REturn Error PAge if one isn't authorizied to chat with someone (Chat request has to set to be true)
        } else {
            //Find the Chats between the two users and add them as a model
            List<Chat> listChats = chatservice.findChats(username, to);
            model.addAttribute("listChats", listChats);

            //Add a Variable for Thymeleaf (receiver)
            model.addAttribute("too", to); //To
            return "chat/chatview";
        }
    }


    @GetMapping("/chat")
    public String chat(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        // Lists all Chats with a Group query to display the Chat Overview
        try {
            List<Chat> listChats = chatservice.ChatRequestCheck(username);
            model.addAttribute("listChats", listChats);
        } catch (Exception e) {
            return "error";
        }
        ////
        //ChatRequest Counter for all Requests which hasn't been accepted
        model.addAttribute("requestCount", chatService.countRequests(username));
        ///
        //FIND OPEN CHAT REQUESTS and List them to accept
        List<Chat> listRequests = chatService.findWhereAllowedFalse(username);
        model.addAttribute("listRequests", listRequests);
        return "chat/chatblank";
    }

}



