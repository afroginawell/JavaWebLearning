package servlet;

import entity.DataBase;
import entity.WaterGate;
import other.Methods;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xie
 * @create 2022-05-19-14:10
 */
@WebServlet(name = "AddGateServlet")
public class AddGateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(Methods.getCurrentTime()+": AddGateServlet运行完毕!");
        WaterGate wg = new WaterGate();
        wg.setWatergateid(request.getParameter("watergateid"));
        wg.setWatergatename(request.getParameter("watergatename"));
        wg.setBuild_time(request.getParameter("build_time"));
        wg.setPrincipal(request.getParameter("principal"));
        wg.setLongitude(request.getParameter("longitude"));
        wg.setLatitude(request.getParameter("latitude"));
        wg.setIntroduction(request.getParameter("introduction"));

        if(wg.existNull()){
            RequestDispatcher dis = request.getRequestDispatcher("addGate.jsp");
            dis.forward(request,response);
            return;
        }

        ServletContext application = this.getServletContext();
        synchronized (application){
            List<WaterGate> wgs = (List<WaterGate>) application.getAttribute("watergate");
            int n = wgs.size();
            for(int i = 0;i < n;i++){
                WaterGate w = wgs.get(i);
                String id = wg.getWatergateid();
                if(w.getWatergateid().equals(id)){
                    request.setAttribute("error",1);
                    RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
                    dis.forward(request,response);
                    return;
                }
            }
            wgs.add(wg);
            application.setAttribute("watergate",wgs);
            DataBase.insertWaterGate(wg);
            response.sendRedirect("main.jsp");
            return;
        }
    }
}
