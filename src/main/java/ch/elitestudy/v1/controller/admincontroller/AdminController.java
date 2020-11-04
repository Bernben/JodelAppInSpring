package ch.elitestudy.v1.controller.admincontroller;

import ch.elitestudy.v1.model.Comment;
import ch.elitestudy.v1.model.Feed;
import ch.elitestudy.v1.service.CommentService;
import ch.elitestudy.v1.service.FeedService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final CommentService commentService;

    private final FeedService feedService;

    public AdminController(CommentService commentService, FeedService feedService) {
        this.commentService = commentService;
        this.feedService = feedService;
    }

    @GetMapping("/admin/feed")
    public String viewHomePage2(Model model) {
        return findPaginated(1, "timestamp", "desc", model);
    }


    @GetMapping("admin/deleteFeed/{id}")
    public String deleteFeed(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.feedService.deleteFeedById(id);
        return "redirect:/";
    }


    @GetMapping("admin/feed_page/{pageNo}")
    public String findPaginated2(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 15;

        Page<Feed> page = feedService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Feed> listFeeds = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listFeeds", listFeeds);

        Feed feed = new Feed();
        model.addAttribute("feed", feed); //To ADD a comment
        return "admin/feeds";
    }
    // display list of comments
    @GetMapping("/admin/comment")
    public String viewHomePage(Model model) {

        return findPaginated(1, "timestamp", "desc", model);
    }


    @GetMapping("admin/deleteComment/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.commentService.deleteCommentById(id);
        return "redirect:/";
    }


    @GetMapping("admin/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 15;

        Page<Comment> page = commentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Comment> listComments = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listComments", listComments);

        Comment comments = new Comment();
        model.addAttribute("comment", comments); //To ADD a comment
        return "admin/comments";
    }




}
