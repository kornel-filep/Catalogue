package progtech.service;

import org.springframework.stereotype.Service;
import progtech.dao.UserDao;
import progtech.model.user.User;

import javax.transaction.Transactional;

@Service
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void loginUser(String username, String password) {
        try {
            User user = findByUsername(username);
            if (user.getPassword().equals(password)) {
                System.out.println("logged in as" + username);
            } else {
                System.out.println("wrong pw");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such user.");
        }
    }

    public User loginAndGetUser(String username, String password) {
        try {
            User user = findByUsername(username);
            if (user.getPassword().equals(password)) {
                System.out.println("logged in as" + username);
                return user;
            } else {
                System.out.println("wrong pw");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such user.");
        }
        return null;
    }

    public void registerUser(String username, String password) {
        try {
            findByUsername(username);
            System.out.println("Already existing user");
        } catch (Exception e) {
            User user = new User(username, password);
            persist(user);
            System.out.println("Registered user " + username);
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

    public void refresh(User user) {
        userDao.refresh(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
