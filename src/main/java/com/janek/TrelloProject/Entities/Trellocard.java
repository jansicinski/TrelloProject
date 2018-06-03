package com.janek.TrelloProject.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC) //PRIVATE
@AllArgsConstructor
//@ToString
@Entity
@Table(name = "trellocard")
public class Trellocard {

    @Id
    @Column(name="card_id")
    String cardId;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="list_id")
    Trellolist trellolist;

}
