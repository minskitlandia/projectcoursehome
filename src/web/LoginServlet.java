package web;

import dao.AuthDao;
import dao.UserDaoImpl;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/signin")
public class LoginServlet extends HttpServlet {

    AuthDao authDao;

    public LoginServlet() {
        this.authDao = new UserDaoImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/signin.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.builder()
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .build();
        user = authDao.login(user);
        if (user.getId() != 0) {
            Util.user = user;
            resp.sendRedirect(req.getContextPath() + "/ads");
        } else {
            resp.sendRedirect(req.getContextPath() + "/signin");
        }
    }
}
