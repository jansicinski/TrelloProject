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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String card_id;

    String list_id;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="list_id")
    Trellolist trellolist;
}
