package br.com.socialmeli.dtos;

public class SellerCountPromoDTO {
    private Long userId;
    private String userName;
    private Long promoProductsCount;

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

    public Long getPromoProductsCount() {
        return promoProductsCount;
    }

    public void setPromoProductsCount(Long promoProductsCount) {
        this.promoProductsCount = promoProductsCount;
    }
}
