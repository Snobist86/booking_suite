package by.htp.Pankov.servlet.user;

import by.htp.Pankov.dao.UserDao;
import by.htp.Pankov.entity.User;
import by.htp.Pankov.util.JspPath;
import by.htp.Pankov.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/login", name = "login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (StringUtil.isEmpty(login) || StringUtil.isEmpty(password)) {
            resp.sendRedirect("/login");
            return;
        }

        Optional<User> user = UserDao.getInstance().findByLogin(login);

        if (user.isPresent() && user.get().getPassword().equals(password)) {

            req.getSession().setAttribute("currentUser", new User(user.get().getId(), user.get().getLogin(), user.get().getRoleId()));
            resp.sendRedirect("/main");
        } else {
            resp.sendRedirect("/login");
        }
    }
}
