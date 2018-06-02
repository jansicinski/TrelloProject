package com.janek.TrelloProject.Enums;

public enum TrelloObjectId {

    ME("me"), LISTS("lists"), CARDS("cards"), BOARDS("boards");

    private final String objectId;

    TrelloObjectId(String objectId) {
        this.objectId = objectId;
    }

}
