package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Services.TrelloboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("TrelloView/")
public class ViewController {

    private final TrelloboardService trelloboardService;

    public ViewController(TrelloboardService trelloboardService) {
        this.trelloboardService = trelloboardService;
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

}
