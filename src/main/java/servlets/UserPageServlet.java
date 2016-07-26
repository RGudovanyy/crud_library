package servlets;

import model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 *
 */

@WebServlet("/main")
public class UserPageServlet extends HttpServlet {
    Logger log = LoggerFactory.getLogger(UserPageServlet.class);

    UserService userService= new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = "";
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if ("cookieLogin".equals(cookie.getName())){
                    userLogin = cookie.getValue();
                    log.info("получили имя из кук " + userLogin);
                }
            }
        }

        List<Book> books = userService.getBooksForUser(userLogin);
        for(Book book:books)
            log.info(book.getName());

        HttpSession session = req.getSession();
        session.setAttribute("books", books);
        req.setAttribute("books", books);
        RequestDispatcher rd = req.getRequestDispatcher("/main.jsp");
        rd.forward(req, resp);
    }
}
