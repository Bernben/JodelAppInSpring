package ch.elitestudy.v1.service;

public interface LikeService {

    void likeFeed(long feedId, String username);
    void dislikeFeed(long feedId, String username);

}
