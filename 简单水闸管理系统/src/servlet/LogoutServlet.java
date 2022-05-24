package servlet;

import other.Methods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author xie
 * @create 2022-05-19-14:10
 */
@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(Methods.getCurrentTime()+": LogoutServlet运行完毕!");
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("loginState");
        response.sendRedirect("login.jsp");
        return;
    }
}
