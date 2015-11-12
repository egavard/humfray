package net.archoslounge.humfray.controller;

import org.jdom2.DataConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.archoslounge.humfray.dto.FinalListDto;
import net.archoslounge.humfray.dto.QuestionAnswer;
import net.archoslounge.humfray.service.ListFinderService;

@Controller
@RequestMapping("/handleQuestion")
public class HandleQuestionController {
	@Autowired
	private ListFinderService listFinderService;

	@RequestMapping
	public String handleQuestion(Model model, @RequestParam int idQuestion, @RequestParam int currentAnswer)
			throws DataConversionException {
		QuestionAnswer nextQuestion = listFinderService.getNextQuestion(idQuestion, currentAnswer);

		if (nextQuestion != null) {
			model.addAttribute("nextQuestion", nextQuestion);
			return "handleQuestionWithNext";
		}

		FinalListDto listeFinale = listFinderService.getFinalListe(idQuestion, currentAnswer);
		model.addAttribute("listeFinale", listeFinale);
		return "handleFinaleQuestion";
	}
}
