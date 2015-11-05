package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 3758411461253557735L;
	private static final Logger LOG  = LoggerFactory.getLogger(IndexController.class);
	public void service(HttpServletRequest req,HttpServletResponse res){
	    try {
		req.getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(req, res);
	    } catch (ServletException e){
		LOG.error("Impossible de forward: IndexController",e);
	    }catch (IOException e) {
		LOG.error("Impossible de forward: IndexController",e);
	    }
	}
}
