package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

/**
 *
 * @author Елизавета
 */

public class TakeMessage extends HttpServlet {
    
 /**
 * 
 * @param req servlet request
 * @param resp servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */  
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            JSONArray list = null;
            MessBD mf = new MessBD();
            Date Current_Date = new Date();
            long date = Current_Date.getTime();
            Message msg = new Message(null,
                        req.getParameter("nick"),
                        req.getParameter("mess"),
                        date);
            resp.setContentType("json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            list = new JSONArray();
            mf.add(msg);
            list.add(msg);
            out.print(list);
        } catch (IllegalArgumentException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    /**
     * 
     * @return описание
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}