package servlets.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import servlets.spec.Controller;

public class ListFinderController extends HttpServlet implements Controller {

	private static final long serialVersionUID = -7209361548340612113L;
	private static final Logger LOG = LoggerFactory.getLogger(ListFinderController.class);


	public void service(HttpServletRequest req, HttpServletResponse res) {
	    //TODO Récupérer grâce au listFinderService la toute première question de l'arbre.
	    req.setAttribute("firstQuestionLabel", "Will it contain key/value pairs or values only?");
	    Map<String, String> firstQuestionAnswers = new HashMap<String,String>();
	    firstQuestionAnswers.put("yes", "Yes");
	    firstQuestionAnswers.put("no", "No");
	    req.setAttribute("firstQuestionAnswers", firstQuestionAnswers);
		try {
		    req.getServletContext().getRequestDispatcher("/jsp/listFinder.jsp").forward(req, res);
		} catch (ServletException se) {
			LOG.error("Erreur de forward dans ListFinderController",se);
		} catch (IOException ioe) {
			LOG.error("Erreur dans l'url de la JSP ListFinderController",ioe);
		}
	}

}
