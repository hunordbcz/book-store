package net.debreczeni.model.list;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.debreczeni.model.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {
    @XmlElement(name = "user")
    private Map<Integer, User> users;

    public Users(Map<Integer, User> users) {
        this.users = users;
    }
}
