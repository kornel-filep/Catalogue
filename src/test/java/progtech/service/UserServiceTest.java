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
    void testLoginAndGetUserShouldGetUserFromDaoAndReturnUser() {
        User expected = new User("Kornel", "123");
        Mockito.when(userDao.findByUsername("Kornel")).thenReturn(expected);
        User user = userService.loginAndGetUser("Kornel", "123");

        Assertions.assertEquals(expected, user);
        Mockito.verify(userDao, Mockito.atLeastOnce()).findByUsername("Kornel");
    }

    @Test
    void testLoginAndGetUserSHouldThrowEntityNotFoundException() {
        Mockito.when(userDao.findByUsername(Mockito.any())).thenReturn(null);
        User user = userService.loginAndGetUser("Kornel", "123");
        Assertions.assertNull(user);
        Mockito.verify(userDao, Mockito.atLeastOnce()).findByUsername("Kornel");
    }

    @Test
    void testRegisterUserShouldPersistUserThroughDao() {
        Mockito.when(userDao.findByUsername("Kornel")).thenThrow(new EntityNotFoundException());
        userService.registerUser("Kornel", "123");
        Mockito.verify(userDao, Mockito.atLeastOnce()).persist(new User("Kornel", "123"));
    }

    @Test
    void testUpdateShouldCallDaoToUpdate() {
        userService.update(new User());
        Mockito.verify(userDao, Mockito.atLeastOnce()).persist(Mockito.any(User.class));
    }

    @Test
    void testFindByIdShouldCallDaoToFindId() {
        Mockito.when(userDao.find(1)).thenReturn(new User("Name", "password"));
        User user = userService.findById(1);
        Assertions.assertEquals(new User("Name", "password"), user);
        Mockito.verify(userDao).find(1);
    }

}
