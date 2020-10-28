package dao;

import model.User;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryUserDao implements UserDao {
    private final static List<User> savedUsers = new ArrayList<>();

    @Override
    public void create(User user) {
        savedUsers.add(user);
    }

    @Override
    public User read(String userId) {
        Optional<User> userOpt = savedUsers.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
        return userOpt.orElse(null);
    }

    @Override
    public void update(User user) {
        User foundUser = read(user.getId());
        if (foundUser != null) {
            foundUser.setFirstName(user.getFirstName());
            foundUser.setLastName(user.getLastName());
            foundUser.setEmail(user.getEmail());
        } else {
            throw new NotFoundException("User with ID " + user.getId() + " does not exist");
        }
    }

    @Override
    public void delete(String userId) {
        savedUsers.removeIf(user -> user.getId().equals(userId));
    }
}
