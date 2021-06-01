package br.com.socialmeli.models;

import java.util.List;

public class User {
    public User(Integer userId, String userName, List<Integer> follow, List<Integer> posts, Boolean isSaler) {
        this.userId = userId;
        this.userName = userName;
        this.follow = follow;
        this.posts = posts;
        this.isSaler = isSaler;
    }

    protected Integer userId;
    protected String userName;
    protected List<Integer> follow;
    protected List<Integer> posts;
    protected Boolean isSaler;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public List<Integer> getFollow() {
        return follow;
    }

    public void setFollow(List<Integer> follow) {
        this.follow = follow;
    }

    public List<Integer> getPosts() {
        return posts;
    }

    public void setPosts(List<Integer> posts) {
        this.posts = posts;
    }

    public Boolean getSaler() {
        return isSaler;
    }

    public void setSaler(Boolean saler) {
        isSaler = saler;
    }


    public void addConnection(Integer id){
        this.follow.add(id);
    }

    public void removeConnection(Integer id){
        this.follow.remove(id);
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", follow=" + follow +
                ", posts=" + posts +
                ", isSaler=" + isSaler +
                '}';
    }
}
