package com.janek.TrelloProject.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC) //PRIVATE
@AllArgsConstructor
@Entity
@Table(name = "trellolist")
public class Trellolist implements Trelloentity{

    @Id
    @Column(name="list_id")
    String listId;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="board_id")
    Trelloboard trelloboard;

    @JsonManagedReference
    @OneToMany(mappedBy="trellolist", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Trellocard> trellocards;

    public void add(Trellocard tempCard) {
        if (trellocards == null) {
            trellocards = new ArrayList<>();
        }
        trellocards.add(tempCard);
        tempCard.setTrellolist(this);
    }

    @Override
    public String toString() {
        return "Trellolist{" +
                "listId='" + listId + '\'' +
                ", trelloboard=" + trelloboard +
                '}';
    }

}