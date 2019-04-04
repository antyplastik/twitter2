package sda.twitter2.controllers;

import sda.twitter2.models.User;
import sda.twitter2.services.oldSolution.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

@WebServlet(name = "RegisterController",
        urlPatterns = "/register")
public class RegisterController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (userCheck(username) && passwordCheck(password)) {
            user.setUsername(username);
            try {
                user.setPassword(DatatypeConverter.printHexBinary(
                        MessageDigest.getInstance("MD5").digest(password.getBytes("UTF-8"))));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            UserService.INSTANCE.registerUser(user);
            Cookie c = new Cookie("userId", "" + user.getId());
            c.setMaxAge(60 * 60);
            response.addCookie(c);
            response.sendRedirect("/twitter2/");
        } else {

        }
    }

    private boolean passwordCheck(String password) {
        return (password != null || !password.equals(""));
    }

    private boolean userCheck(String user) {
        return (user != null || !user.equals(""));
    }


}
