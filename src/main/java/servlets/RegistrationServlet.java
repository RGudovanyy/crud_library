package servlets;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.PasswordValidator;
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
public class RegistrationServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    PasswordValidator passwordValidator;


    Logger log = LoggerFactory.getLogger(RegistrationServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String incomeLogin = req.getParameter("login");
        String incomePassword1 = req.getParameter("password");
        String incomePassword2 = req.getParameter("password2");
        if(incomePassword1.equals(incomePassword2)){
            //if(passwordValidator.validate(incomePassword1)){
                User newUser = new User(incomeLogin, incomePassword1);
                userService.addNewUser(newUser);
                log.info("Add new user");
                Cookie cookieLogin = new Cookie("cookieLogin",incomeLogin);
                Cookie cookiePassword = new Cookie("cookiePassword",incomePassword1);
                resp.addCookie(cookieLogin);
                resp.addCookie(cookiePassword);
                resp.sendRedirect("/main");
           /* }
            else {
                writer.write("Your password:\n" +
                        "must contains one digit from 0-9\n" +
                        "must contains one lowercase character\n" +
                        " must contains one uppercase characters\n" +
                        "<a href = \"signup.html\">Try again</a>");
            }*/
        }
        else if(!incomePassword1.equals(incomePassword2))
            {
                writer.write("Passwords are not equal. Please, <a href=\"signup.html\">try again</a>");
            }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
