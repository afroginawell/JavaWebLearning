package servlet;

import entity.DataBase;
import entity.WaterGate;
import other.Methods;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author xie
 * @create 2022-05-19-14:11
 */
@WebServlet(name = "DeleteGateServlet")
public class DeleteGateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(Methods.getCurrentTime()+": DeleteGateServlet运行完毕!");
        String watergateid = request.getParameter("watergateid");
        // delete the water gate data of watergateid in application
        ServletContext application = this.getServletContext();
        List<WaterGate> wgs = (List<WaterGate>) application.getAttribute("watergate");
        synchronized (application){
            for(WaterGate wg : wgs){
                if(wg.getWatergateid().equals(watergateid)){
                    wgs.remove(wg);
                    application.setAttribute("watergate",wgs);
                    DataBase.deleteWaterGate(watergateid);
                    response.sendRedirect("main.jsp");
                    return;
                }
            }
        }
    }
}
