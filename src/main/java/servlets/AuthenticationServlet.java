package servlets;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
public class AuthenticationServlet extends HttpServlet {
    Logger log = LoggerFactory.getLogger(AuthenticationServlet.class);

    UserService userService = new UserServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String incomeLogin = req.getParameter("login");
        String incomePassword = req.getParameter("password");
        User incomeUser = new User(incomeLogin, incomePassword);
        User checkedUser = userService.getUserByLogin(incomeLogin);
        if(checkedUser.equals(incomeUser)){
            Cookie cookieLogin = new Cookie("cookieLogin",incomeLogin);
            Cookie cookiePassword = new Cookie("cookiePassword",incomePassword);
            resp.addCookie(cookieLogin);
            resp.addCookie(cookiePassword);
            log.info("user " + checkedUser.getName() + " logged in");
            writer.write("Добро пожаловать " + checkedUser.getName()+"!\n Загружаем ваш список");
            resp.sendRedirect("/main");
        }
        else
            {

                writer.write("Invalid login/password. <a href=\"signin.html\">Try again</a>, of \n" +
                        "if you not a registered user - <a href=\"signup.html\">Sign up</a>");
            }


    }
}
