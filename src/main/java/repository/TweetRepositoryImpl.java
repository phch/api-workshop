package repository;

import criteria.CriteriaTweetNewerThan;
import criteria.CriteriaTweetOlderThan;
import dao.MemoryTweetDao;
import dao.TweetDao;
import model.Tweet;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Task 2: Once you switched out the Dao for SimulatedRemoteTweetDao, things
 * started working too slow. Can you add caching here to speed things up?
 *
 * Things to consider:
 *   - Where does the cache live?
 *   - What should the TTL be?
 *   - Which methods should the cache apply to?
 */
public class TweetRepositoryImpl implements TweetRepository {
    /**
     * Task 1b: Replace this with a SimulatedRemoteTweetDao and try hitting
     * the endpoint on Postman. Do you see the difference in response time?
     */
    private final TweetDao tweetDao = new MemoryTweetDao();

    @Override
    public Tweet add(Tweet tweet) {
        tweet.setId(UUID.randomUUID().toString());
        tweet.setCreatedAt(Instant.now());
        tweetDao.create(tweet);
        return tweet;
    }

    @Override
    public Tweet get(String tweetId) {
        return tweetDao.read(tweetId);
    }

    @Override
    public void update(Tweet tweet) {
        tweetDao.update(tweet);
    }

    @Override
    public void remove(String tweetId) {
        tweetDao.delete(tweetId);
    }

    @Override
    public List<Tweet> getNewerThan(Instant instant) {
        CriteriaTweetNewerThan criteria = new CriteriaTweetNewerThan(instant);
        return tweetDao.search(criteria);
    }

    @Override
    public List<Tweet> getOlderThan(Instant instant) {
        CriteriaTweetOlderThan criteria = new CriteriaTweetOlderThan(instant);
        return tweetDao.search(criteria);
    }
}
