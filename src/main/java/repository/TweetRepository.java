package repository;

import model.Tweet;

import java.time.Instant;
import java.util.List;

public interface TweetRepository {
    Tweet add(Tweet tweet);
    Tweet get(String tweetId);
    void update(Tweet tweet);
    void remove(String tweetId);

    List<Tweet> getNewerThan(Instant instant);
    List<Tweet> getOlderThan(Instant instant);
}
