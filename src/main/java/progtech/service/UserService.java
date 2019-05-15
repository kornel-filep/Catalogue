package progtech.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import progtech.dao.UserDao;
import progtech.domain.Series;
import progtech.domain.user.User;

import javax.transaction.Transactional;

/**
 * The Service which interacts with the User domain object and DAOs.
 */
@Service
@Slf4j
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Logs in and then returns the logged in user if it exists. Returns null if it does not exist.
     * @param username the user name of the user
     * @param password the password of the user
     * @return the user if it exists in the database, null otherwise
     */
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

    /**
     * Registers the user. Doesnt do anything if the user already exists.
     * @param username the username of the user
     * @param password the password of the user
     */
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

    /**
     * Uses the DAO to find the user by its id.
     * @param id the id of the user
     * @return the User with the id
     */
    public User findById(long id) {
        return userDao.find(id);
    }

    /**
     * Persists the user via the DAO.
     * @param user the User to be persisted
     */
    @Transactional
    public void persist(User user) {
        userDao.persist(user);
    }

    /**
     * Updates the user via the DAO.
     * @param user the User to be updated
     */
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * Finds the user va the DAO by its username.
     * @param username the username of the user.
     * @return the User with the given username
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * Adds a Series to the Users watchlist. And then updates the user in the database.
     * @param series The Series to add to the user
     * @param user The User to add the user to.
     */
    public void addSeriesToUser(Series series, User user) {
        user.getSeries().add(series);
        update(user);
    }
}
