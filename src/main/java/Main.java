import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.DBService;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);

        DBService dbService = new DBService();
        dbService.printConnectInfo();

        Server server = new Server(8080);
        WebAppContext context = new WebAppContext();
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");
        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/[^/]*jstl.*\\.jar$");
        Configuration.ClassList classList = Configuration.ClassList.serverDefault(server);
        classList.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
                "org.eclipse.jetty.plus.webapp.EnvConfiguration",
                "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classList.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");

        server.setHandler(context);

        try{
            server.start();
            server.join();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
