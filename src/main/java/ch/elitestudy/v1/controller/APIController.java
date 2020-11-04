package ch.elitestudy.v1.controller;

import ch.elitestudy.v1.model.Feed;
import ch.elitestudy.v1.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class APIController {
    @Autowired
    FeedService feedService;


    @RequestMapping(value = "/getFeeds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Feed> getAllFeeds(){

        return feedService.getAllFeeds();
    }

//
//    @RequestMapping(value = "/getFeed/{feedId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Feed getFeed(@PathVariable Integer feedId) throws SQLException, ClassNotFoundException {
//
//        log.info("---------------------------------------------------------------------------------------------------");
//        log.info("FeedRESTController::getFeed()");
//
//        return feedService.getFeedById(feedId);
//    }

}
