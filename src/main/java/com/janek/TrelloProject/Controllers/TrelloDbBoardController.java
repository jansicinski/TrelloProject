package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Entities.Trelloboard;
import com.janek.TrelloProject.Repositories.TrelloboardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("TrelloDb/boards/")
public class TrelloDbBoardController {

    private final TrelloboardRepository trelloboardRepository;

    public TrelloDbBoardController(TrelloboardRepository trelloboardRepository) {
        this.trelloboardRepository = trelloboardRepository;
    }

    @PutMapping("")
    public ResponseEntity<Trelloboard> updateBoard(@RequestBody Trelloboard trelloboard){
        trelloboard = trelloboardRepository.save(trelloboard);
        Optional<Trelloboard> trelloboardOptional = trelloboardRepository.findById(trelloboard.getId());
        if (trelloboardOptional.isPresent()) {
            return ResponseEntity.ok().body(trelloboardOptional.get());
        } else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<Trelloboard> createBoard(@RequestBody Trelloboard trelloboard){
        trelloboard = trelloboardRepository.save(trelloboard);
        return ResponseEntity.created(URI.create("localhost:8080/TrelloDb/cards/" + trelloboard.getId())).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Trelloboard>  deleteBoard(@PathVariable int id){
        if(trelloboardRepository.findById((long) id).isPresent()) {
            trelloboardRepository.deleteById((long) id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Trelloboard> getBoard(@PathVariable int id){
        Optional<Trelloboard> trelloboardOptional = trelloboardRepository.findById((long) id);
        if (trelloboardOptional.isPresent()) {
            return ResponseEntity.ok().body(trelloboardOptional.get());
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Trelloboard>> getAllMyBoards(){
        List<Trelloboard> trelloboards = trelloboardRepository.findAll();
        if (trelloboards.size() > 0) {
            return ResponseEntity.ok().body(trelloboards);
        } else
            return ResponseEntity.notFound().build();
    }

}
