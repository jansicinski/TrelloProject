package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Services.TrelloboardService;
import com.janek.TrelloProject.Services.TrellocardService;
import com.janek.TrelloProject.Services.TrellolistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("TrelloView/")
public class ViewController {

    private final TrelloboardService trelloboardService;

    private final TrellolistService trellolistService;

    private final TrellocardService trellocardService;

    public ViewController(TrelloboardService trelloboardService, TrellolistService trellolistService, TrellocardService trellocardService) {
        this.trelloboardService = trelloboardService;
        this.trellolistService = trellolistService;
        this.trellocardService = trellocardService;
    }

    @GetMapping("greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("boards")
    public String boards(Model model) {
        model.addAttribute("boards", trelloboardService.read());
        return "boards";
    }

    @GetMapping("lists")
    public String lists(Model model) {
        model.addAttribute("lists", trellolistService.read());
        return "lists";
    }

    @GetMapping("cards")
    public String cards(Model model) {
        model.addAttribute("cards", trellocardService.read());
        return "cards";
    }

}
