package com.janek.TrelloProject.RestControllers.Db;


import com.janek.TrelloProject.Entities.Trellocard;
import com.janek.TrelloProject.Services.TrellocardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("TrelloDb/cards/")
public class TrelloDbCardController {

    private final TrellocardService trellocardService;

    public TrelloDbCardController(TrellocardService trellocardService) {
        this.trellocardService = trellocardService;
    }

    @GetMapping("")
    public ResponseEntity<List<Trellocard>> getAllMyCards(){
        List<Trellocard> trellocards = trellocardService.read();
        if (trellocards != null) {
            return ResponseEntity.ok().body(trellocards);
        } else
            return ResponseEntity.notFound().build();
    }

}
