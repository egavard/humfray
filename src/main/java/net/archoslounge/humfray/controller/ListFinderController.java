package net.archoslounge.humfray.controller;

import org.jdom2.DataConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.archoslounge.humfray.service.ListFinderService;

@Controller
@RequestMapping("/listFinder")
public class ListFinderController {
	@Autowired
	private ListFinderService listFinderService;

	@RequestMapping
	public String listFinder(Model model) throws DataConversionException {
		model.addAttribute("firstQuestion", listFinderService.getFirstQuestion());
		return "listFinder";
	}
}
