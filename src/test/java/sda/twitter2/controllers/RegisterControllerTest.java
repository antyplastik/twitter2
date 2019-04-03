package sda.twitter2.controllers;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sda.twitter2.models.User;
import sda.twitter2.services.DatabaseService;
import sda.twitter2.services.DefaultUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;


@RunWith(JUnitParamsRunner.class)
public class RegisterControllerTest {

    RegisterController registerController;
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Before
    public void setUp() throws ServletException {
        registerController = new RegisterController();
        registerController.init();
    }

    @Test
    @Parameters(method = "testUsers")
    public void checkUser(String username, String password, boolean expected) throws IOException {
        DatabaseService userService = new DefaultUserService();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        assertThat(userService.isExist(user), equalTo(expected));
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        // TODO Prepare GIVEN step - mock the content of request object
        //...
        // WHEN
        //registerController.doPost(request, response);
        //TODO Prepare THEN step - verify response object content and whether certain redirects have been called
    }

    public Object[] testUsers() {
        return new Object[]{
                new Object[]{"admin", "admin", true},
                new Object[]{"user", "user", false},
                new Object[]{"test", "test", false}
        };
    }

}
