package chat;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *Сервлет чата
 * @author Елизавета
 */
public class ChatServlet extends HttpServlet {
/**
 * 
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Если не задан параметр action, то возвратить ошибку 400.
        if (request.getParameter("action") == null) {
            response.sendError(400, "Bad Request");
            return;
        }

        // Отправить ответ клиенту в формате JSON
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            MessBD cl = new MessBD();
            UsersBD uf = new UsersBD();
            
            if (request.getParameter("action").equals("addUser")) {
                Users c = new Users(null,
                        request.getParameter("login"),
                        request.getParameter("pass"),
                        "off");
                out.println(uf.add(c));
            }
            
            if (request.getParameter("action").equals("users")){
                response.setContentType("json;charset=utf-8");
                out.print(uf.whoOn());
            }

            if (request.getParameter("action").equals("createdb")) {
                out.println(cl.createDB());
                out.println(uf.createDB());
            }            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

