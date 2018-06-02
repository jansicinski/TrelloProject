package com.janek.TrelloProject.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC) //PRIVATE
@AllArgsConstructor
@ToString
@Entity
@Table(name = "trelloboard")
public class Trelloboard {
    @Id
    String board_id;

    @OneToMany(mappedBy="trelloboard", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    List<Trellolist> trellolists;

    public void add(Trellolist tempList) {
        if (trellolists == null) {
            trellolists = new ArrayList<>();
        }
        trellolists.add(tempList);
        tempList.setTrelloboard(this);
    }
}
