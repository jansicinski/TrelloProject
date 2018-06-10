package com.janek.TrelloProject.Services;

import com.janek.TrelloProject.Entities.Trelloentity;

import java.util.List;

public interface Trellocrudable {
    Trelloentity create(Trelloentity trelloentity);
    List<Trelloentity> read(String id);
    List<Trelloentity> read();
    Trelloentity update(Trelloentity trelloentity);
    boolean delete(String id);
}
