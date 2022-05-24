package servlet;

import entity.User;
import other.Methods;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author xie
 * @create 2022-05-19-13:57
 */
//@javax.servlet.annotation.WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println(Methods.getCurrentTime()+": LoginServlet运行完毕!");
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
        if(user_name.equals("") || password.equals("")){
            request.setAttribute("loginState",-1);
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        ServletContext application = this.getServletContext();
        List<User> users = (List<User>) application.getAttribute("user");
        int n = users.size();
        for(int i = 0; i < n;i++){
            User u = users.get(i);
            if(u.getUser_name().equals(user_name) && "正常".equals(u.getCurrent_state()) && password.equals(u.getPassword())){
                // load data into session
                HttpSession session = request.getSession();
                session.setAttribute("user",u);
                System.out.println(Methods.getCurrentTime()+": "+u.getUser_name()+" 登录系统");
                response.sendRedirect("main.jsp");
                return; // the following code will executed without return.
            }
        }
        request.setAttribute("loginState",-1);
        request.getRequestDispatcher("login.jsp").forward(request,response);
        return;
    }
}
