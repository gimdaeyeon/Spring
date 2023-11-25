package com.example.securingweb.domain.type;

public enum Authority {
    ROLE_USER("ROLE_USER"),ROLE_ADMIN("ROLE_ADMIN");

    private final String name;

    Authority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
