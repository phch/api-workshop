package repository;

import model.User;

public interface UserRepository {
    User add(User user);
    User get(String userId);
    void update(User user);
    void remove(String userId);
}
