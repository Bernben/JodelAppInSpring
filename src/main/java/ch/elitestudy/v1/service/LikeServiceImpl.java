package ch.elitestudy.v1.service;

import ch.elitestudy.v1.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public void likeFeed(long feedId, String username) {
        this.likeRepository.likeDb(feedId, username);
    }

    @Override
    public void dislikeFeed(long feedId, String username) {
        this.likeRepository.dislikeDb(feedId, username);
    }

}
