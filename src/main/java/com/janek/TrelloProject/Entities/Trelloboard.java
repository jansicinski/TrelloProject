package com.janek.TrelloProject.Entities;

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
//@ToString
@Entity
@Table(name = "trelloboard")
public class Trelloboard implements Trelloentity{

    @Id
    @Column(name="board_id")
    String boardId;

    String name;

    @JsonManagedReference
    @OneToMany(mappedBy="trelloboard", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Trellolist> trellolists;

    public void add(Trellolist tempList) {
        if (trellolists == null) {
            trellolists = new ArrayList<>();
        }
        trellolists.add(tempList);
        tempList.setTrelloboard(this);
    }

    @Override
    public String toString() {
        return "Trelloboard{" +
                "boardId='" + boardId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
