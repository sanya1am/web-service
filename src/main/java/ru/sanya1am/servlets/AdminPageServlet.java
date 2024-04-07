package ru.sanya1am.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sanya1am.accountServer.AccountServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AdminPageServlet extends HttpServlet {
    public static final String PAGE_URL = "/admin";
    private AccountServer accountServer;
    private Logger logger = LogManager.getLogger(AdminPageServlet.class.getName());


    public AdminPageServlet(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");

        int limit = accountServer.getUsersLimit();
        logger.info("Limit: " + limit);
        response.getWriter().println(limit);
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
