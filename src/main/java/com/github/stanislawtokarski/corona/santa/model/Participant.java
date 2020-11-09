package com.github.stanislawtokarski.corona.santa.model;

import java.util.Map;

public class Participant {

    private final String name;
    private final String email;

    public Participant(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static Participant fromMapEntry(Map.Entry<String, String> nameAndEmailEntry) {
        return new Participant(nameAndEmailEntry.getKey(), nameAndEmailEntry.getValue());
    }
}
