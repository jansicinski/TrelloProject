package com.janek.TrelloProject.Controllers.Db;

import com.janek.TrelloProject.Commands.CreateTrelloboardCommand;
import com.janek.TrelloProject.Entities.Trelloboard;
import com.janek.TrelloProject.Services.TrelloboardService;
import com.janek.TrelloProject.Utils.PathBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Trelloboard> createBoard(@Valid @RequestBody CreateTrelloboardCommand createTrelloboardCommand){
        Trelloboard trelloboard = createTrelloboardCommand.buildTrelloboard();
        trelloboard = trelloboardService.create(trelloboard);
        return ResponseEntity.created(PathBuilder.pathWithId(trelloboard.getBoardId())).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable String id){
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
