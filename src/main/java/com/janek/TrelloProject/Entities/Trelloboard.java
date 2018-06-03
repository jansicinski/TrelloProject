package com.janek.TrelloProject.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC) //PRIVATE
@AllArgsConstructor
//@ToString
@Entity
@Table(name = "trelloboard")
public class Trelloboard {

    @Id
    @Column(name="board_id")
    String boardId;

    @OneToMany(mappedBy="trelloboard", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Trellolist> trellolists;

    public void add(Trellolist tempList) {
        if (trellolists == null) {
            trellolists = new ArrayList<>();
        }
        trellolists.add(tempList);
        tempList.setTrelloboard(this);
    }

}
