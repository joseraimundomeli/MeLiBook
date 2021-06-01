package br.com.socialmeli.dtos;public class UserDTO {

    private Integer userID;
    private String userName;

    public UserDTO(Integer userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
