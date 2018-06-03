package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Entities.Trelloboard;
import com.janek.TrelloProject.Services.TrelloboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("TrelloDb/boards/")
public class TrelloDbBoardController {

    private final TrelloboardService trelloboardService;

    public TrelloDbBoardController(TrelloboardService trelloboardService) {
        this.trelloboardService = trelloboardService;
    }

    @PutMapping("")
    public ResponseEntity<Trelloboard> updateBoard(@RequestBody Trelloboard trelloboard){
        trelloboard = trelloboardService.update(trelloboard);
        if (trelloboard != null) {
            return ResponseEntity.ok().body(trelloboard);
        } else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<Trelloboard> createBoard(@RequestBody Trelloboard trelloboard){
        trelloboard = trelloboardService.create(trelloboard);
        return ResponseEntity.created(URI.create("localhost:8080/TrelloDb/cards/" + trelloboard.getBoardId())).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>  deleteBoard(@PathVariable String id){
        if(trelloboardService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Trelloboard> getBoard(@PathVariable String id){
        Trelloboard trelloboard = trelloboardService.read(id);
        if (trelloboard != null) {
            return ResponseEntity.ok().body(trelloboard);
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Trelloboard>> getAllMyBoards(){
        List<Trelloboard> trelloboards = trelloboardService.read();
        if (trelloboards != null) {
            return ResponseEntity.ok().body(trelloboards);
        } else
            return ResponseEntity.notFound().build();
    }

}
