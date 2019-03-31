package sda.twitter2.dbServices;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class DefaultUserServiceTest {

    @Test
    @Parameters(method = "testUsers")
    public void checkUser(String username, String password, boolean expected) throws IOException {
        DefaultUserService defaultUserService = DefaultUserService.getInstance();

        assertThat(defaultUserService.checkUser(username,password), equalTo(expected));
    }


    public Object[] testUsers() {
        return new Object[]{
                new Object[]{"admin", "admin", true},
                new Object[]{"user", "user", false},
                new Object[]{"test", "test", false}
        };
    }

}