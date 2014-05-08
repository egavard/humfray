package servlets.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.FinalListeDto;
import dto.QuestionReponse;
import service.impl.ListFinderService;
import service.interf.IListFinderService;
import servlets.spec.Controller;

public class TraitementQuestionController extends HttpServlet implements Controller  {
    private static final Logger LOG = LoggerFactory.getLogger(TraitementQuestionController.class); 

    public void service(HttpServletRequest request, HttpServletResponse response) {
	String idQuestionStr = request.getParameter("idQuestion");
	String reponseStr = request.getParameter("currentReponse");
	if(NumberUtils.isNumber(idQuestionStr) && NumberUtils.isNumber(reponseStr)){
	    IListFinderService listFinderService = new ListFinderService();
	    QuestionReponse nextQuestion = listFinderService.getNextQuestion(Integer.valueOf(idQuestionStr), Integer.valueOf(reponseStr));
	    if(nextQuestion != null){
		request.setAttribute("nextQuestion", nextQuestion);
	    }else{
		FinalListeDto listeFinale = listFinderService.getFinalListe(Integer.valueOf(idQuestionStr), Integer.valueOf(reponseStr));
		request.setAttribute("listeFinale", listeFinale);
	    }
	    try {
		request.getRequestDispatcher("/jsp/traitement.jsp").forward(request, response);
	    } catch (ServletException e) {
		LOG.error("Impossible de forward",e);
	    } catch (IOException e) {
		LOG.error("Impossible de forward",e);
	    }
	}
    }

}
