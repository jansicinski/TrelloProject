package com.janek.TrelloProject.Entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC) //PRIVATE
@AllArgsConstructor
@ToString
@Entity
public class TrelloMe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;


}
