package ru.sanya1am.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.sanya1am.accounts.AccountService;
import ru.sanya1am.database.DBService;
import ru.sanya1am.servlets.SignInServlet;
import ru.sanya1am.servlets.SignUpServlet;

public class Main {
    public static void main(String[] args) {
        try {
            DBService dbService = new DBService();
            AccountService accountService = new AccountService(dbService);

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
            context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

            ResourceHandler resourceHandler = new ResourceHandler();
            resourceHandler.setResourceBase("public_html");

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[] {resourceHandler, context});


            Server server = new Server(8080);
            server.setHandler(handlers);

            server.start();
            System.out.println("Server started");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}