package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("token") == null){
			if (req.getRequestURI().contains("login.html"))
			req.getRequestDispatcher("login.html").forward(req, resp);;
		}
		else{
			if (req.getRequestURI().equals("/"))
			{
				req.getRequestDispatcher("group/createGroup.html").forward(req,resp);			
			}
			if (req.getRequestURI().endsWith(".html")){
				req.getRequestDispatcher(req.getRequestURI()).forward(req,resp);
			}
		}
	}
}
