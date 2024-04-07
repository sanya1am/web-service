package ru.sanya1am.main;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.sanya1am.accountServer.AccountServer;
import ru.sanya1am.accountServer.AccountServerController;
import ru.sanya1am.accountServer.AccountServerControllerMBean;
import ru.sanya1am.accountServer.AccountServerImpl;
import ru.sanya1am.servlets.AdminPageServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Main {
    static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {

        int port = 8080;
        logger.info("Starting at http://127.0.0.1:" + port);

        AccountServer accountServer = new AccountServerImpl();

        AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServerController");
        mbs.registerMBean(serverStatistics, name);

        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new AdminPageServlet(accountServer)), AdminPageServlet.PAGE_URL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);


        server.start();
        logger.info("Server started");
        // Создаю поток для проверки setUsersLimit() через 10 сек после старта сервера
        Thread taskThread = new Thread(() -> {
            try {
                Thread.sleep(10000);
                accountServer.setUsersLimit(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        taskThread.start();

        server.join();
    }
}
