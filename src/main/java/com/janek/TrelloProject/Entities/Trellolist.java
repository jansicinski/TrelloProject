package com.janek.TrelloProject.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC) //PRIVATE
@AllArgsConstructor
@ToString
@Entity
@Table(name = "trellolist")
public class Trellolist {
    @Id
    String list_id;

    String board_id;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="board_id")
    Trelloboard trelloboard;

    @OneToMany(mappedBy="trellolist", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    List<Trellocard> trellocards;

    public void add(Trellocard tempCard) {
        if (trellocards == null) {
            trellocards = new ArrayList<>();
        }
        trellocards.add(tempCard);
        tempCard.setTrellolist(this);
    }
}