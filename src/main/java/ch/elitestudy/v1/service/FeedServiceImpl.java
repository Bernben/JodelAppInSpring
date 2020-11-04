package ch.elitestudy.v1.service;

import ch.elitestudy.v1.model.Feed;
import ch.elitestudy.v1.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedRepository feedRepository;




    @Override
    public void saveFeed(Feed feed) {
        this.feedRepository.save(feed);
    }

    @Override
    public Feed getFeedById(long id) {
        Optional<Feed> optional = feedRepository.findById(id);
        Feed feed = null;
        if (optional.isPresent()) {
            feed = optional.get();
        } else {
            throw new RuntimeException(" Comment not found for id :: " + id);
        }
        return feed;
    }


    @Override
   public void likeFeed(long id){
        this.feedRepository.likeFeed(id);
    }
    @Override
    public void dislikeFeed(long id){
        this.feedRepository.dislikeFeed(id);
    }

    @Override
    public void deleteFeedById(long id) {
        this.feedRepository.deleteById(id);
    }

    @Override
    public Page <Feed> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.feedRepository.findAll(pageable);
    }

//API
    public List<Feed> getAllFeeds() {
        return feedRepository.findAll();
    }

}
