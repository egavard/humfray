package servlets.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.impl.ListFinderService;
import service.interf.IListFinderService;
import servlets.spec.Controller;

public class ListFinderController extends HttpServlet implements Controller {

	private static final long serialVersionUID = -7209361548340612113L;
	private static final Logger LOG = LoggerFactory.getLogger(ListFinderController.class);


	public void service(HttpServletRequest req, HttpServletResponse res) {
	    //TODO Récupérer grâce au listFinderService la toute première question de l'arbre.
	    IListFinderService listFinderService = new ListFinderService();
	    req.setAttribute("firstQuestion", listFinderService.getFirstQuestion());
		try {
		    req.getServletContext().getRequestDispatcher("/jsp/listFinder.jsp").forward(req, res);
		} catch (ServletException se) {
			LOG.error("Erreur de forward dans ListFinderController",se);
		} catch (IOException ioe) {
			LOG.error("Erreur dans l'url de la JSP ListFinderController",ioe);
		}
	}

}
