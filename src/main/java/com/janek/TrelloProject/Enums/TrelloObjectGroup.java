package com.janek.TrelloProject.Enums;

public enum  TrelloObjectGroup {
    MEMBERS("members"),
    CARDS("cards"),
    LISTS("lists"),
    BOARDS("boards");

    private final String objectGroup;

    private TrelloObjectGroup(String objectGroup) {
        this.objectGroup = objectGroup;
    }
}
