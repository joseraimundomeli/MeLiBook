package br.com.socialmeli.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class PostNewDTO {
    private Long userId;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate date;
    private Integer category;
    private Double price;
    private Boolean hasPromo = false;
    private Double discount = 0.0;
    private ProductDTO detail;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean getHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(Boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public ProductDTO getDetail() {
        return detail;
    }

    public void setDetail(ProductDTO detail) {
        this.detail = detail;
    }
}
