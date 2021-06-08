package br.com.socialmeli.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Following {

    @EmbeddedId
    private FollowingDateId followingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("sellerId")
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Column(name = "date")
    private LocalDate data;

    public Following(User user, Seller seller) {
        this.user = user;
        this.seller = seller;
        this.data = LocalDate.now();
        this.followingId = new FollowingDateId(user.getUserId(), seller.getUserId());
    }

    public Following() {
    }

    public FollowingDateId getFollowingId() {
        return followingId;
    }

    public void setFollowingId(FollowingDateId followingId) {
        this.followingId = followingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Following following = (Following) o;
        return user.equals(following.user) && seller.equals(following.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, seller);
    }
}
