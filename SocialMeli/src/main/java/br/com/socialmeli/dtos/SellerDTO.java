package br.com.socialmeli.dtos;

import java.util.List;

public class SellerDTO {
    private Long userId;
    private String userName;
    private List<PostDTO> postDTOList;

    public SellerDTO(Long userId, String userName, List<PostDTO> postDTOList) {
        this.userId = userId;
        this.userName = userName;
        this.postDTOList = postDTOList;
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

    public List<PostDTO> getPostDTOList() {
        return postDTOList;
    }

    public void setPostDTOList(List<PostDTO> postDTOList) {
        this.postDTOList = postDTOList;
    }
}
