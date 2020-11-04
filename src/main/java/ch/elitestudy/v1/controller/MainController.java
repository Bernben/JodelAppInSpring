package ch.elitestudy.v1.controller;

import ch.elitestudy.v1.model.User;
import ch.elitestudy.v1.service.ChatService;
import ch.elitestudy.v1.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ch.elitestudy.v1.service.ChatService;

@Controller
public class MainController {

   private final UserService userService;
   private final ChatService chatservice;
    private String username;

    public MainController(UserService userService, ChatService chatService) {
        this.userService = userService;
       this.chatservice = chatService;
    }

    @ModelAttribute("unreadMSG")
    public Integer unreadMSG() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            username = authentication.getName();
        }

        return chatservice.countUnread(username);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/userdetail")
    public String userDetail(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user/userdetail";
    }
}
