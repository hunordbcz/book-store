package net.debreczeni.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@XmlRootElement
@XmlType(propOrder = {"username", "password", "accessType"})
public class User {
    private String username;
    private String password;
    private AccessType accessType;
}
