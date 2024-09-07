package com.example.servlet;

import com.example.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        else {
            request.getRequestDispatcher("/user/hello.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        List<String> users = Users.getInstance().getUsers();

        if (users.contains(login) && !password.isEmpty()) {
            session.setAttribute("user", login);
            response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
