package repository;

import dao.MemoryUserDao;
import dao.UserDao;
import model.User;

import java.time.Instant;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {
    /**
     * Task 1a: Replace this with a SimulatedRemoteUserDao and try hitting
     * the endpoint on Postman. Do you see the difference in response time?
     */
    private final UserDao userDao = new MemoryUserDao();

    @Override
    public User add(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setCreatedAt(Instant.now());
        userDao.create(user);
        return user;
    }

    /**
     * Task 3: We want to return a richer set of information about the
     * user, like their social media profile with a list of their tweets.
     */
    @Override
    public User get(String userId) {
        return userDao.read(userId);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * Task 4: Can you update this code to remove tweets associated
     * with a User from the TweetDao?
     */
    @Override
    public void remove(String userId) {
        userDao.delete(userId);
    }
}
