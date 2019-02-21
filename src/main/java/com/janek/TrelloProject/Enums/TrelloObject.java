package com.janek.TrelloProject.Enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum  TrelloObject {

    MEMBERS("members"),
    CARDS("cards"),
    LISTS("lists"),
    BOARDS("boards"),
    ME("me");

    private final String objectName;

}
