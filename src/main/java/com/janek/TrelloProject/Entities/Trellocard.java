package com.janek.TrelloProject.Entities;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC) //PRIVATE
@AllArgsConstructor
@ToString
@Entity
@Table(name = "trellocard")
public class Trellocard {

    @Id
    @Column(name="card_id")
    String cardId;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="list_id")
    Trellolist trellolist;

}
