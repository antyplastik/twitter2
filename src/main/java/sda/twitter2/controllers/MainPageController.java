package sda.twitter2.controllers;

import sda.twitter2.services.oldSolution.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="MainPageController",
        urlPatterns = "/")
public class MainPageController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = null;
        for(Cookie c : request.getCookies()){
            if(c.getName().equals("userId")){
                userId = c.getValue();
            }
        }
        if(userId == null){
            response.sendRedirect("register.jsp");
        } else {
            request.setAttribute("user", UserService.INSTANCE.findUser(Integer.parseInt(userId)));
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }
    }
}
