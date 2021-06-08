package br.com.socialmeli.models;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "POST")
public class Post  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(name = "date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    private Integer category;
    private Double price;
    private Double discount = 0.0;
    private Boolean hasPromo = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId")
    private Seller seller;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private Product detail;

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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Product getDetail() {
        return detail;
    }

    public void setDetail(Product detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", date=" + date +
                ", category=" + category +
                ", price=" + price +
                ", discount=" + discount +
                ", hasPromo=" + hasPromo +
                ", seller=" + seller +
                ", detail=" + detail +
                '}';
    }
}
