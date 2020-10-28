package dao;

import criteria.Criteria;
import model.Tweet;

import java.util.List;

public interface TweetDao {
    Tweet create(Tweet tweet);
    Tweet read(String tweetId);
    void update(Tweet tweet);
    void delete(String tweetId);

    List<Tweet> search(Criteria<Tweet> criteria);
}
