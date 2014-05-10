package servlets.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import connection.ConnectionProvider;
import servlets.spec.Controller;

public class IndexController extends HttpServlet implements Controller{
	private static final long serialVersionUID = 3758411461253557735L;
	private static final Logger LOG  = LoggerFactory.getLogger(IndexController.class);
	public void service(HttpServletRequest req,HttpServletResponse res){
	    Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;
	    try {
		con = ConnectionProvider.getConnection();
		LOG.warn("con != null:"+(con!=null));
		if(null != con){
		    st = con.createStatement();
		    rs = st.executeQuery("SELECT * FROM version");
		    if(rs.next()){
			LOG.warn("ouh yeah duff man!");;
		    }
		    con.close();
		}
	    } catch (SQLException e2) {
		LOG.error("Impossible de creer le statement",e2);
	    }
	    try {
		req.getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(req, res);
	    } catch (ServletException e){
		LOG.error("Impossible de forward: IndexController",e);
	    }catch (IOException e) {
		LOG.error("Impossible de forward: IndexController",e);
	    }
	}
}
