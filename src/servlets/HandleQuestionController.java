package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.jdom2.DataConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.FinalListDto;
import dto.QuestionAnswer;
import service.ListFinderService;

public class HandleQuestionController extends HttpServlet  {
    private static final long serialVersionUID = 2073508572748146802L;
    private static final Logger LOG = LoggerFactory.getLogger(HandleQuestionController.class); 

    public void service(HttpServletRequest request, HttpServletResponse response) {
    	
	String idQuestionStr = request.getParameter("idQuestion");
	String reponseStr = request.getParameter("currentAnswer");
	if(NumberUtils.isNumber(idQuestionStr) && NumberUtils.isNumber(reponseStr)){
	    ListFinderService listFinderService = new ListFinderService();
	    QuestionAnswer nextQuestion = null;
	    try {
		nextQuestion = listFinderService.getNextQuestion(Integer.valueOf(idQuestionStr), Integer.valueOf(reponseStr));
	    } catch (NumberFormatException e) {
		e.printStackTrace();
	    } catch (DataConversionException e) {
		e.printStackTrace();
	    }
	    if(nextQuestion != null){
		request.setAttribute("nextQuestion", nextQuestion);
	    }else{
		FinalListDto listeFinale = listFinderService.getFinalListe(Integer.valueOf(idQuestionStr), Integer.valueOf(reponseStr));
		request.setAttribute("listeFinale", listeFinale);
	    }
	}
	try {	
	    request.getRequestDispatcher("/jsp/handleQuestion.jsp").forward(request, response);
	} catch (ServletException e) {
	    LOG.error("Impossible de forward",e);
	} catch (IOException e) {
	    LOG.error("Impossible de forward",e);
	}
    }

}
