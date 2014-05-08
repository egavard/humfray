package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.interf.IListFinderService;
import tools.XmlTool;
import dto.FinalListeDto;
import dto.QuestionReponse;

public class ListFinderService implements IListFinderService{
	private static final String LINK = "../../ressources/ListV1.xml";
	private XmlTool tool;
	private static final Logger LOG = LoggerFactory.getLogger(ListFinderService.class);
	public ListFinderService()
	{
		tool = new XmlTool();
		tool.initFile(LINK);
	}

	@Override
	public QuestionReponse getFirstQuestion() {
		try
		{
			Element e = tool.getChildren("arrayOfQuestions");

			//retourne les questions
			List<Element> array = tool.getListChildren(e,tool.getRoot());

			//retourne les reponses de la premiere question
			List<Element> array2 = tool.getListChildren(array.get(0), e);

			Map<String,String> mp = new HashMap<String,String>();
			for(Element tmp : array2)
			{
				mp.put(tmp.getAttributeValue("id"), tmp.getAttributeValue("r"));
			}

			QuestionReponse retour = new QuestionReponse(array.get(0).getAttributeValue("q"),array.get(0).getAttribute("id").getIntValue(), mp);
			return retour;
		}
		catch (Exception e)
		{
			LOG.error("erreur lors de la recuperation de la premiere question");
		}
		return null;

	}

	@Override
	public QuestionReponse getNextQuestion(Integer idQuestion, Integer idReponse) throws DataConversionException {
		try
		{
		Element e = tool.getChildren("arrayOfQuestions");
		Element actuel = null;
		Integer nextQuestion = null;
		//retourne les questions
		List<Element> array = tool.getListChildren(e,tool.getRoot());
		
		//recherche la question avec l'id
		for(Element tmp : array)
		{
			if(tmp.getAttributeValue("id").equals(idQuestion + ""))
			{
				actuel = tmp;
			}
		}
		
		//retourne les reponses de la question
		List<Element> array2 = tool.getListChildren(actuel, e);
		
		for(Element tmp : array2)
		{
			if(tmp.getAttributeValue("id").equals(idReponse + ""))
			{
				nextQuestion = Integer.valueOf(tmp.getAttributeValue("nextQ"));
			}
		}
		
		//retourne la nouvelle question
		array = tool.getListChildren(e,tool.getRoot());
		
		//recherche la question avec l'id
		for(Element tmp : array)
		{
			if(tmp.getAttributeValue("id").equals(nextQuestion + ""))
			{
				actuel = tmp;
			}
		}
		
		//on recupere les reponses
		array2 = tool.getListChildren(actuel, e);

		Map<String,String> mp = new HashMap<String,String>();
		for(Element tmp : array2)
		{
			mp.put(tmp.getAttributeValue("id"), tmp.getAttributeValue("r"));
		}
		
		QuestionReponse retour = new QuestionReponse(array.get(0).getAttributeValue("q"),actuel.getAttribute("id").getIntValue(), mp);
		return retour;
		}
		catch (Exception e)
		{
			LOG.error("erreur lors de la recuperation de la question suivante");
		}
		return null;
	}

	@Override
	public FinalListeDto getFinalListe(Integer idQuestion, Integer idReponse) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	public static void main(String[] args) {
		ListFinderService t = new ListFinderService();
		System.out.println(t.getFirstQuestion().getListeReponse());
	}*/

}
