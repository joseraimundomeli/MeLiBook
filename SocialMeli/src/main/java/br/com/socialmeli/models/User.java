package br.com.socialmeli.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USERTYPE")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long userId;
    protected String userName;

    @OneToMany(mappedBy = "user")
    protected List<Following> following = new ArrayList<>();

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Following> getFollowing() {
        return following;
    }

    public void setFollowing(List<Following> following) {
        this.following = following;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", following=" + following +
                '}';
    }
}
