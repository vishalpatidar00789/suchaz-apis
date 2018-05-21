package com.suchaz.app.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the ItemReview entity.
 */
public class ItemReviewDTO implements Serializable {

    private Long id;

    @NotNull
    private String ratingValue;

    private String ratingComment;

    private Long itemId;

    private String itemTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getRatingComment() {
        return ratingComment;
    }

    public void setRatingComment(String ratingComment) {
        this.ratingComment = ratingComment;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemReviewDTO itemReviewDTO = (ItemReviewDTO) o;
        if(itemReviewDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemReviewDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemReviewDTO{" +
            "id=" + getId() +
            ", ratingValue='" + getRatingValue() + "'" +
            ", ratingComment='" + getRatingComment() + "'" +
            "}";
    }
}
