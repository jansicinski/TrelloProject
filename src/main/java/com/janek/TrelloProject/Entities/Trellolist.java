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
@Table(name = "trellolist")
public class Trellolist {

    @Id
    @Column(name="list_id")
    String listId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="board_id")
    Trelloboard trelloboard;

    @OneToMany(mappedBy="trellolist", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Trellocard> trellocards;

    public void add(Trellocard tempCard) {
        if (trellocards == null) {
            trellocards = new ArrayList<>();
        }
        trellocards.add(tempCard);
        tempCard.setTrellolist(this);
    }

}