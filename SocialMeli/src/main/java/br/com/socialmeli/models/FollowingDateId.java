package br.com.socialmeli.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FollowingDateId implements Serializable {

    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "SELLER_ID")
    private Long followerId;

    public FollowingDateId(Long userId, Long followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }

    public FollowingDateId() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowingDateId following = (FollowingDateId) o;
        return Objects.equals(userId, following.userId) && Objects.equals(followerId, following.followerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, followerId);
    }

}
