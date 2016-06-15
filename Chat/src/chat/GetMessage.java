/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Елизавета
 */
public class GetMessage extends HttpServlet {
/**
 * 
 * @param req servlet request
 * @param res servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            MessBD cl = new MessBD();
            resp.setContentType("json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            if(req.getParameter("last").equals("NaN")){
                Date Current_Date = new Date();
                long date = Current_Date.getTime();
                out.print(cl.list(date));
            }
            else{
                Long l1 = new Long(req.getParameter("last"));
                out.print(cl.list(l1));
            }
            
        } catch (IllegalArgumentException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
