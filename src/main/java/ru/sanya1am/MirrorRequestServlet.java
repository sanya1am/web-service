package ru.sanya1am;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MirrorRequestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        // Получаем значение параметра key из запроса
        String value = request.getParameter("key");

        // Устанавливаем статус ответа в зависимости от значение параметра key:
        // Если значение пустое, то устанавливается статус 403 Forbidden (запрос недопустим)
        // Если значение не пустое, то устанавливается статус 200 OK (успешное выполнение запроса)
        response.setStatus(value.isEmpty() ? HttpServletResponse.SC_FORBIDDEN : HttpServletResponse.SC_OK);

        // Записываем значение параметра key в поток вывода
        response.getWriter().write(value);

    }
}
