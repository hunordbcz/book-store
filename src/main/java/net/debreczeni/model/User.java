package net.debreczeni.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
//@XmlType(propOrder = {"username", "password", "accessType"})
public class User {
    private Integer id;
    private String username;
    private String password;
    private AccessType accessType;
}
