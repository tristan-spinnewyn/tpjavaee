package montp.data.model.security;

import montp.data.model.GenericEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SECURITY_USER")
public class User extends GenericEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String userMail;
    @Column(columnDefinition = "TEXT")
    private String password;
    @Column(columnDefinition = "TEXT")
    private String oldPassword;

    @ManyToMany
    @JoinTable(name = "SECURITY_USER_GROUP",
            joinColumns = @JoinColumn(name = "usermail",
                    referencedColumnName = "usermail"),
            inverseJoinColumns = @JoinColumn(name = "groupname",
                    referencedColumnName = "groupname"))
    private List<Group> groups;

    public User() {
    }

    public User(String userMail, String password) {
        this.userMail = userMail;
        this.password = password;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public String toString() {
        return userMail;
    }

}
