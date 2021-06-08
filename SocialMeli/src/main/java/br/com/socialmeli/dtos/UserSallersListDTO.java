package br.com.socialmeli.dtos;

import java.util.List;

public class UserSallersListDTO {
    private Long userId;
    private String userName;
    private List<UserDTO> following;



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

    public List<UserDTO> getFollowing() {
        return following;
    }

    public void setFollowing(List<UserDTO> following) {
        this.following = following;
    }
}
