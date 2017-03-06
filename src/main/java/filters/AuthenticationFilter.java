package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		
		
		String requestUri = req.getRequestURI();
		if ("login.html".equals(requestUri) || "logout".contains(requestUri)){
			chain.doFilter(request, response);		
		}
		else{
			HttpSession session = req.getSession(false);
			if (session == null){
				session = req.getSession(true);
			}
			String token = req.getParameter("token");
		
			if (token == null){
				token = (String) session.getAttribute("token");
			}	
			else{
				session.setAttribute("token", token);
			}
			if(token != null)	
				chain.doFilter(request, response);			
			else
				req.getRequestDispatcher("/login.html").forward(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
