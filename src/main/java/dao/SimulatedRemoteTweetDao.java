package dao;

import criteria.Criteria;
import lombok.SneakyThrows;
import model.Tweet;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimulatedRemoteTweetDao implements TweetDao {
    private final static List<Tweet> savedTweets = new ArrayList<>();

    @SneakyThrows
    private void sleepThread() {
        Thread.sleep((long)(Math.random() * 1500));
    }

    @Override
    public Tweet create(Tweet tweet) {
        sleepThread();
        savedTweets.add(tweet);
        return tweet;
    }

    @Override
    public Tweet read(String tweetId) {
        sleepThread();
        Optional<Tweet> tweetOpt = savedTweets.stream()
                .filter(tweet -> tweet.getId().equals(tweetId))
                .findFirst();
        return tweetOpt.orElse(null);
    }

    @Override
    public void update(Tweet tweet) {
        sleepThread();
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
        sleepThread();
        savedTweets.removeIf(tweet -> tweet.getId().equals(tweetId));
    }

    @Override
    public List<Tweet> search(Criteria<Tweet> criteria) {
        sleepThread();
        return criteria.meetsCriteria(savedTweets);
    }
}
