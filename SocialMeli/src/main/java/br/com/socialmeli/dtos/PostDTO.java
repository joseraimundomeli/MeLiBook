package br.com.socialmeli.dtos;

import br.com.socialmeli.models.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class PostDTO {
    private Long postId;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate date;
    private ProductDTO detail;
    private Integer category;
    private Double price;


    public PostDTO(Long postId, LocalDate date, ProductDTO detail, Integer category, Double price) {
        this.postId = postId;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ProductDTO getDetail() {
        return detail;
    }

    public void setDetail(ProductDTO detail) {
        this.detail = detail;
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

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", date=" + date +
                ", detail=" + detail +
                ", category=" + category +
                ", price=" + price +
                '}';
    }
}
