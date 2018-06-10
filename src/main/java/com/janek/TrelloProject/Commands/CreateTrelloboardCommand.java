package com.janek.TrelloProject.Commands;

import com.janek.TrelloProject.Entities.Trelloboard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrelloboardCommand {

    @Size(min = 3, message = "needs at least 3 chars")
    String boardId;

    @NotBlank(message = "cannot be blank")
    String name;

    public Trelloboard buildTrelloboard(){
        return Trelloboard.builder().boardId(boardId).name(name).build();
    }

}
