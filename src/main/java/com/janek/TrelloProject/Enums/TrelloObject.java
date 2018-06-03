package com.janek.TrelloProject.Enums;

public enum  TrelloObject {

    MEMBERS("members"),
    CARDS("cards"),
    LISTS("lists"),
    BOARDS("boards"),
    ME("me");

    private final String objectName;

    TrelloObject(String objectName) {
        this.objectName = objectName;
    }

}
