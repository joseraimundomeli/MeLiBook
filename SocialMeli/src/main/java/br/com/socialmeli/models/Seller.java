package br.com.socialmeli.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Seller")
public class Seller extends User{

    @OneToMany
    protected List<Post> posts;

    @OneToMany(mappedBy = "seller")
    protected List<Following> followers = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Following> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Following> followers) {
        this.followers = followers;
    }
}
