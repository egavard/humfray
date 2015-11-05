package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.FinalListeDto;
import dto.QuestionAnswer;
import tools.XmlTool;

public class ListFinderService {
	private static final String LINK = "../../ressources/ListV1.xml";
	private XmlTool tool;
	private static final Logger LOG = LoggerFactory.getLogger(ListFinderService.class);
	public ListFinderService()
	{
		tool = new XmlTool();
		tool.initFile(LINK);
	}

	public QuestionAnswer getFirstQuestion() {
		try
		{
			Element e = tool.getChildren("questionArray");

			//retourne les questions
			List<Element> array = tool.getListChildren(e,tool.getRoot());

			//retourne les reponses de la premiere question
			List<Element> array2 = array.get(0).getChildren();

			Map<String,String> mp = new HashMap<String,String>();
			for(Element tmp : array2)
			{
				mp.put(tmp.getAttributeValue("id"), tmp.getAttributeValue("r"));
			}

			QuestionAnswer retour = new QuestionAnswer(array.get(0).getAttributeValue("q"),array.get(0).getAttribute("id").getIntValue(), mp);
			return retour;
		}
		catch (Exception e)
		{
			LOG.error("Unable to find questionArray node");
		}
		return null;

	}

	public QuestionAnswer getNextQuestion(Integer idQuestion, Integer idReponse) throws DataConversionException {
		try
		{
		Element e = tool.getChildren("questionArray");
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
		List<Element> array2 = actuel.getChildren();
		
		for(Element tmp : array2)
		{
			if(tmp.getAttributeValue("id").equals(idReponse + ""))
			{
				if(tmp.getAttributeValue("nextQ").equals("none"))
					return null;
				nextQuestion = Integer.valueOf(tmp.getAttributeValue("nextQ"));
			}
		}

		//recherche la question avec l'id
		for(Element tmp : array)
		{
			if(tmp.getAttributeValue("id").equals(nextQuestion + ""))
			{
				actuel = tmp;
			}
		}
		//on recupere les reponses
		array2 = actuel.getChildren();
		Map<String,String> mp = new HashMap<String,String>();
		for(Element tmp : array2)
		{
			mp.put(tmp.getAttributeValue("id"), tmp.getAttributeValue("r"));
		}
		
		QuestionAnswer retour = new QuestionAnswer(actuel.getAttributeValue("q"),actuel.getAttribute("id").getIntValue(), mp);
		return retour;
		}
		catch (Exception e)
		{
			LOG.error("erreur lors de la recuperation de la question suivante");
		}
		return null;
	}


	public FinalListeDto getFinalListe(Integer idQuestion, Integer idReponse) {
		try
		{
		Element e = tool.getChildren("questionArray");
		Element actuel = null;
		Integer idListe = null;
		FinalListeDto result = null;
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
		List<Element> array2 = actuel.getChildren();
		
		for(Element tmp : array2)
		{
			if(tmp.getAttributeValue("id").equals(idReponse + ""))
			{
				//si on est pas a la fin de l'arbre
				if(!tmp.getAttributeValue("nextQ").equals("none"))
					return null;
				else
					idListe = Integer.valueOf(tmp.getAttributeValue("listId"));
			}
		}

		//recuperation de la liste des listes et recherche de l'id
		e = tool.getRoot().getChild("listArray");
		array.clear();
		array = e.getChildren();
		for(Element tmp : array)
		{
			if(Integer.valueOf(tmp.getAttributeValue("id")) == idListe)
				result = new FinalListeDto(tmp.getAttributeValue("value"));
		}
		
		return result;
		}
		catch(Exception e)
		{
			LOG.error("Unable to retrieve final response");
			return null;
		}
	}
	
	/*
	public static void main(String[] args) throws DataConversionException {
		ListFinderService t = new ListFinderService();
		System.out.println(t.getFinalListe(4, 5).getName());
	}*/

}
