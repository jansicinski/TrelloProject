package com.janek.TrelloProject.Errors;

public class NullTrelloObjectIdException extends Exception {
    public NullTrelloObjectIdException(String message){
        super(message);
    }
}
