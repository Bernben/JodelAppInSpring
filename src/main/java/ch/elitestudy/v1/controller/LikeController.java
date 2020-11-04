package ch.elitestudy.v1.controller;

import ch.elitestudy.v1.service.FeedService;
import ch.elitestudy.v1.service.LikeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LikeController {


    private final FeedService feedService;

    private final LikeService likeService;

    public LikeController(FeedService feedService, LikeService likeService) {
        this.feedService = feedService;
        this.likeService = likeService;
    }


    //Like feed.. and make entry with user+feedid to block more than 1 like or dislike
    @PostMapping("/likefeed")
    public String likeFeed(@RequestParam("id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        try {
            likeService.likeFeed(id, username);
            feedService.likeFeed(id);
        } catch (Exception e) {
            System.out.println("User friendly error message caused by column");
        }
        return "redirect:/feed";

    }

    //DisLike feed.. and make entry with user+feedid to block more than 1 like or dislike
    @PostMapping("/dislikefeed")
    public String dislikeFeed(@RequestParam("id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        try {
            likeService.dislikeFeed(id, username);
            feedService.dislikeFeed(id);
        } catch (Exception e) {
            System.out.println("User friendly error message caused by column");
        }
        return "redirect:/feed";
    }
}