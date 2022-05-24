package servlet;

import com.google.gson.Gson;
import entity.WaterGate;
import other.Methods;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xie
 * @create 2022-05-19-14:10
 */
@WebServlet(name = "GetGatesServlet")
public class GetGatesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(Methods.getCurrentTime()+": GetGatesServlet运行完毕!");
        ServletContext application = this.getServletContext();
        List<WaterGate> wgs = (List<WaterGate>) application.getAttribute("watergate");

        // convert wgs to JsonArray
        Gson gson = new Gson();
        String json = gson.toJson(wgs);
        response.getWriter().print(json);
    }
}
