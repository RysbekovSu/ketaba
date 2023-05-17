package com.Ketaba.ketaba.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,WORKER, COMPANY, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
