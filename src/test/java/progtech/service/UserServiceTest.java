package progtech.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import progtech.dao.UserDao;
import progtech.model.user.User;

import javax.persistence.EntityNotFoundException;

public class UserServiceTest {

    private static UserService userService;
    private static UserDao userDao;

    @BeforeAll
    static void init() {
        userDao = Mockito.mock(UserDao.class);
        userService = new UserService(userDao);
    }

    @Test
    void loginAndGetUserShouldGetUserFromDaoAndReturnUser() {
        User expected = new User("Kornel", "123");
        Mockito.when(userDao.findByUsername("Kornel")).thenReturn(expected);
        User user = userService.loginAndGetUser("Kornel", "123");

        Assertions.assertEquals(expected, user);
        Mockito.verify(userDao, Mockito.atLeastOnce()).findByUsername("Kornel");
    }

    @Test
    void loginAndGetUserSHouldReturnNullIfNoSuchUser() {
        Mockito.when(userDao.findByUsername("Kornel")).thenReturn(null);
        User user = userService.loginAndGetUser("Kornel", "123");

        Assertions.assertNull(user);
        Mockito.verify(userDao, Mockito.atLeastOnce()).findByUsername("Kornel");
    }

    @Test
    void registerUserShouldPersistUserThroughDao() {
        Mockito.when(userDao.findByUsername("Kornel")).thenThrow(new EntityNotFoundException());
        userService.registerUser("Kornel", "123");
        Mockito.verify(userDao, Mockito.atLeastOnce()).persist(new User("Kornel", "123"));
    }

}
