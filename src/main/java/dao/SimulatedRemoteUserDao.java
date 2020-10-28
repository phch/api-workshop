package dao;

import lombok.SneakyThrows;
import model.User;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimulatedRemoteUserDao implements UserDao {
    private final static List<User> savedUsers = new ArrayList<>();

    @SneakyThrows
    private void sleepThread() {
        Thread.sleep((long)(Math.random() * 1500));
    }

    @Override
    public void create(User user) {
        sleepThread();
        savedUsers.add(user);
    }

    @Override
    public User read(String userId) {
        sleepThread();
        Optional<User> userOpt = savedUsers.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
        return userOpt.orElse(null);
    }

    @Override
    public void update(User user) {
        sleepThread();
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
        sleepThread();
        savedUsers.removeIf(user -> user.getId().equals(userId));
    }
}
