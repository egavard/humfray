package net.archoslounge.humfray.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.springframework.stereotype.Service;

import net.archoslounge.humfray.dto.FinalListDto;
import net.archoslounge.humfray.dto.QuestionAnswer;
import net.archoslounge.humfray.tools.XmlTool;

@Service
public class ListFinderService {
	private static final String LINK = "ListV1.xml";
	private XmlTool tool;

	public ListFinderService() {
		tool = new XmlTool();
		tool.initFile(LINK);
	}

	public QuestionAnswer getFirstQuestion() throws DataConversionException {
		Element e = tool.getChildren("questionArray");

		// retourne les questions
		List<Element> array = tool.getListChildren(e, tool.getRoot());

		// retourne les reponses de la premiere question
		List<Element> array2 = array.get(0).getChildren();

		Map<String, String> mp = new HashMap<String, String>();
		for (Element tmp : array2) {
			mp.put(tmp.getAttributeValue("id"), tmp.getAttributeValue("r"));
		}

		QuestionAnswer retour = new QuestionAnswer(array.get(0).getAttributeValue("q"),
				array.get(0).getAttribute("id").getIntValue(), mp);
		return retour;

	}

	public QuestionAnswer getNextQuestion(Integer idQuestion, Integer idReponse) throws DataConversionException {
		Element e = tool.getChildren("questionArray");
		Element actuel = null;
		Integer nextQuestion = null;
		// retourne les questions
		List<Element> array = tool.getListChildren(e, tool.getRoot());

		// recherche la question avec l'id
		for (Element tmp : array) {
			if (tmp.getAttributeValue("id").equals(idQuestion + "")) {
				actuel = tmp;
			}
		}
		// retourne les reponses de la question
		List<Element> array2 = actuel.getChildren();

		for (Element tmp : array2) {
			if (tmp.getAttributeValue("id").equals(idReponse + "")) {
				if (tmp.getAttributeValue("nextQ").equals("none"))
					return null;
				nextQuestion = Integer.valueOf(tmp.getAttributeValue("nextQ"));
			}
		}

		// recherche la question avec l'id
		for (Element tmp : array) {
			if (tmp.getAttributeValue("id").equals(nextQuestion + "")) {
				actuel = tmp;
			}
		}
		// on recupere les reponses
		array2 = actuel.getChildren();
		Map<String, String> mp = new HashMap<String, String>();
		for (Element tmp : array2) {
			mp.put(tmp.getAttributeValue("id"), tmp.getAttributeValue("r"));
		}

		QuestionAnswer retour = new QuestionAnswer(actuel.getAttributeValue("q"),
				actuel.getAttribute("id").getIntValue(), mp);
		return retour;
	}

	public FinalListDto getFinalListe(Integer idQuestion, Integer idReponse) {
		Element e = tool.getChildren("questionArray");
		Element actuel = null;
		Integer idListe = null;
		FinalListDto result = null;
		// retourne les questions
		List<Element> array = tool.getListChildren(e, tool.getRoot());

		// recherche la question avec l'id
		for (Element tmp : array) {
			if (tmp.getAttributeValue("id").equals(idQuestion + "")) {
				actuel = tmp;
			}
		}
		// retourne les reponses de la question
		List<Element> array2 = actuel.getChildren();

		for (Element tmp : array2) {
			if (tmp.getAttributeValue("id").equals(idReponse + "")) {
				// si on est pas a la fin de l'arbre
				if (!tmp.getAttributeValue("nextQ").equals("none"))
					return null;
				else {
					idListe = Integer.valueOf(tmp.getAttributeValue("listId"));
				}
			}
		}

		// recuperation de la liste des listes et recherche de l'id
		e = tool.getRoot().getChild("listArray");
		array.clear();
		array = e.getChildren();
		for (Element tmp : array) {
			if (Integer.valueOf(tmp.getAttributeValue("id")) == idListe) {
				result = new FinalListDto(tmp.getAttributeValue("value"));
			}
		}

		return result;
	}
}
