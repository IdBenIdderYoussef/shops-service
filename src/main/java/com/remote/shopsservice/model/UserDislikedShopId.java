package com.remote.shopsservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserDislikedShopId implements Serializable {

    @Column(name = "user_id")
    private long userId;

    @Column(name = "shop_id")
    private long shopId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        UserDislikedShopId that = (UserDislikedShopId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, shopId);
    }

}
