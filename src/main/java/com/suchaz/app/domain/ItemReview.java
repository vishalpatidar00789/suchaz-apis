package com.suchaz.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ItemReview.
 */
@Entity
@Table(name = "item_review")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rating_value", nullable = false)
    private String ratingValue;

    @Column(name = "rating_comment")
    private String ratingComment;

    @ManyToOne
    private Item item;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public ItemReview ratingValue(String ratingValue) {
        this.ratingValue = ratingValue;
        return this;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getRatingComment() {
        return ratingComment;
    }

    public ItemReview ratingComment(String ratingComment) {
        this.ratingComment = ratingComment;
        return this;
    }

    public void setRatingComment(String ratingComment) {
        this.ratingComment = ratingComment;
    }

    public Item getItem() {
        return item;
    }

    public ItemReview item(Item item) {
        this.item = item;
        return this;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemReview itemReview = (ItemReview) o;
        if (itemReview.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemReview.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemReview{" +
            "id=" + getId() +
            ", ratingValue='" + getRatingValue() + "'" +
            ", ratingComment='" + getRatingComment() + "'" +
            "}";
    }
}
