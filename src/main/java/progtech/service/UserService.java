package progtech.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import progtech.dao.UserDao;
import progtech.model.Series;
import progtech.model.user.User;

import javax.transaction.Transactional;

@Service
@Slf4j
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User loginAndGetUser(String username, String password) {
        try {
            User user = findByUsername(username);
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                log.debug("Wrong password for user : " + username);
            }
        } catch (Exception e) {
            log.debug("No such user" + e);
            log.debug("No such user: " + username);
        }
        return null;
    }

    public void registerUser(String username, String password) {
        try {
            findByUsername(username);
            log.debug("Already existing user");
        } catch (Exception e) {
            User user = new User(username, password);
            persist(user);
            log.debug("Registered user " + username);
        }
    }

    public User findById(long id) {
        return userDao.find(id);
    }

    @Transactional
    public void persist(User user) {
        userDao.persist(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void addSeriesToUser(Series series, User user) {
        user.getSeries().add(series);
        update(user);
    }
}
