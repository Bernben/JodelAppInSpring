package ch.elitestudy.v1.controller;

import ch.elitestudy.v1.model.Chat;
import ch.elitestudy.v1.model.Feed;
import ch.elitestudy.v1.model.Comment;
import ch.elitestudy.v1.service.ChatService;
import ch.elitestudy.v1.service.CommentService;
import ch.elitestudy.v1.service.FeedService;

import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@Controller
public class FeedCommentController {

    private final FeedService feedService;
    private final CommentService commentService;
    private final ChatService chatservice;



private String username;

    public FeedCommentController(FeedService feedService, CommentService commentService, ChatService chatservice) {
        this.feedService = feedService;
        this.commentService = commentService;
        this.chatservice = chatservice;
    }

    @ModelAttribute("unreadMSG")
    public Integer unreadMSG() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            username = authentication.getName();
        }

        return chatservice.countUnread(username);
    }


    @GetMapping("/feed")
    public String viewFeedPage(Model model) {
        return findPaginated2(1, "timestamp", "desc", model);
    }



    @GetMapping("/comment")
    public String viewComment(Model model, @RequestParam("id") long feedId) {
        model.addAttribute("feedcoming", feedService.getFeedById(feedId));
        model.addAttribute("listComments", commentService.findbyFeed_id(feedId));

        Comment comment = new Comment();
        model.addAttribute("comment", comment);

        model.addAttribute("feedcomingid", feedId);

        model.addAttribute("details", username);


        return "user/commentView";
    }

    @PostMapping("/saveFeed")
    public String saveFeed(@ModelAttribute("Feed") Feed feed) {
        // save comment to database
        feed.setLikes((long) 0);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        feed.setTimestamp(ts);


        feedService.saveFeed(feed);
        return "redirect:/feed";
    }

    @PostMapping("/saveComment")
    public String saveComment(@ModelAttribute("Comment") Comment comment) {
        // save comment to database
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        comment.setTimestamp(ts);
        Long id = comment.getFeedId();
        commentService.saveComment(comment);

        return "redirect:/comment?id=" + id;
    }

    @GetMapping("user/page/{pageNo}")
    public String findPaginated2(@PathVariable(value = "pageNo") int pageNo,
                                 @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir,
                                 Model model) {

        int pageSize = 8;

        Page<Feed> page = feedService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Feed> listFeeds = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");


        Feed feed = new Feed();
        model.addAttribute("feed", feed); //To ADD a comment


        Chat chat = new Chat();
        model.addAttribute("chatRequest", chat); //To ADD a Request

        model.addAttribute("details", username);
        model.addAttribute("listFeeds", listFeeds);

        return "user/feedView";

    }

}



