package com.janek.TrelloProject.RestControllers.Db;


import com.janek.TrelloProject.Entities.Trellolist;
import com.janek.TrelloProject.Services.TrellolistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("TrelloDb/lists/")
@AllArgsConstructor
public class TrelloDbListController {

    private final TrellolistService trellolistService;

    @GetMapping("")
    public ResponseEntity<List<Trellolist>> getAllMyLists(){
        List<Trellolist> trellolists = trellolistService.read();
        if (trellolists != null) {
            return ResponseEntity.ok().body(trellolists);
        } else
            return ResponseEntity.notFound().build();
    }

}
