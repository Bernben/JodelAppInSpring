package ch.elitestudy.v1.service;

import ch.elitestudy.v1.model.Feed;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FeedService {
    List<Feed> getAllFeeds();
    void saveFeed(Feed feed);
    Feed getFeedById(long id);

    void likeFeed(long id);
    void dislikeFeed(long id);

    void deleteFeedById(long id);
    Page<Feed> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
