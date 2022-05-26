package web;

import dao.BaseDao;
import dao.UserDaoImpl;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/")
public class UserServlet extends HttpServlet {

    private BaseDao<User> userDao;

    public UserServlet() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertUser(request, response);
                break;
            case "/delete":
                deleteUser(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateUser(request, response);
                break;
            case "/list":
                listUser(request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.read(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/user-form.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDao.read();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/list-user.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.userDao.insert(User.builder()
                .login(request.getParameter("login"))
                .password(request.getParameter("password"))
                .role_id(Integer.parseInt(request.getParameter("role_id")))
                .build());
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.userDao.delete(id);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        int role_id = Integer.parseInt(request.getParameter("role_id"));
        this.userDao.update(new User(id, login, password, role_id));
        response.sendRedirect("list");
    }
}
