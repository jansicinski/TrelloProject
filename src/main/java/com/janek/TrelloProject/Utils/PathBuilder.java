package com.janek.TrelloProject.Utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class PathBuilder {

    public static URI pathWithId(Object id){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id.toString())
                .toUri();
    }

}
