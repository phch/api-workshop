package dao;

import criteria.Criteria;
import model.Tweet;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryTweetDao implements TweetDao {
    private final static List<Tweet> savedTweets = new ArrayList<>();

    @Override
    public Tweet create(Tweet tweet) {
        savedTweets.add(tweet);
        return tweet;
    }

    @Override
    public Tweet read(String tweetId) {
        Optional<Tweet> tweetOpt = savedTweets.stream()
                .filter(tweet -> tweet.getId().equals(tweetId))
                .findFirst();
        return tweetOpt.orElse(null);
    }

    @Override
    public void update(Tweet tweet) {
        Tweet foundTweet = read(tweet.getId());
        if (foundTweet != null) {
            foundTweet.setText(tweet.getText());
            foundTweet.setRetweetCount(tweet.getRetweetCount());
        } else {
            throw new NotFoundException("Tweet with ID " + tweet.getId() + " does not exist");
        }
    }

    @Override
    public void delete(String tweetId) {
        savedTweets.removeIf(tweet -> tweet.getId().equals(tweetId));
    }

    @Override
    public List<Tweet> search(Criteria<Tweet> criteria) {
        return criteria.meetsCriteria(savedTweets);
    }
}
