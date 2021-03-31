package net.debreczeni.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User {
    private String username;
    private String password;
}
