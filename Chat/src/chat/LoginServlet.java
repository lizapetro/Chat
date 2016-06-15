package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
/**
 * 
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
        @Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get request parameters for userID and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		UsersBD uf = new UsersBD();
		if(uf.checking1(user, pwd)){
                    uf.setStatus(user, "on");
                    Cookie loginCookie = new Cookie("user",user);
                    //setting cookie to expiry in 30 mins
                    response.addCookie(loginCookie);
                    response.sendRedirect("LoginSuccess.jsp");
		}else{
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                    PrintWriter out= response.getWriter();
                    response.setContentType("json;charset=utf-8");
                    out.println("<font color=red>Either user name or password is wrong.</font>");
                    rd.include(request, response);
		}
	}
}