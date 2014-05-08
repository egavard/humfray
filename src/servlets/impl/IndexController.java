package servlets.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.spec.Controller;

public class IndexController extends HttpServlet implements Controller{
	private static final long serialVersionUID = 3758411461253557735L;

	public void service(HttpServletRequest req,HttpServletResponse res){
		try {
			req.getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(req, res);
		} catch (ServletException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
