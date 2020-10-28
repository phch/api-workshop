package dao;

import model.User;

public interface UserDao {
    void create(User user);
    User read(String userId);
    void update(User user);
    void delete(String userId);
}
