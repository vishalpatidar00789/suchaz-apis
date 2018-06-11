package com.suchaz.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A ItemImage.
 */
@Entity
@Table(name = "item_image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "item_image_name", nullable = false)
    private String itemImageName;

    @Column(name = "item_image_desc")
    private String itemImageDesc;

    @NotNull
    @Column(name = "item_image_size", nullable = false)
    private Long itemImageSize;

    @Column(name = "item_image_url")
    private String itemImageURL;

    @Lob
    @Column(name = "item_image")
    private byte[] itemImage;

    @Column(name = "item_image_content_type")
    private String itemImageContentType;

    @Column(name = "item_type")
    private String itemType;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_on_top")
    private Status imageOnTop;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_on_hover")
    private Status imageOnHover;

    @NotNull
    @Column(name = "last_refreshed_date", nullable = false)
    private Long lastRefreshedDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Long createdDate;

    @Column(name = "last_updated_date")
    private Long lastUpdatedDate;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @ManyToOne
    private Item item;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemImageName() {
        return itemImageName;
    }

    public ItemImage itemImageName(String itemImageName) {
        this.itemImageName = itemImageName;
        return this;
    }

    public void setItemImageName(String itemImageName) {
        this.itemImageName = itemImageName;
    }

    public String getItemImageDesc() {
        return itemImageDesc;
    }

    public ItemImage itemImageDesc(String itemImageDesc) {
        this.itemImageDesc = itemImageDesc;
        return this;
    }

    public void setItemImageDesc(String itemImageDesc) {
        this.itemImageDesc = itemImageDesc;
    }

    public Long getItemImageSize() {
        return itemImageSize;
    }

    public ItemImage itemImageSize(Long itemImageSize) {
        this.itemImageSize = itemImageSize;
        return this;
    }

    public void setItemImageSize(Long itemImageSize) {
        this.itemImageSize = itemImageSize;
    }

    public String getItemImageURL() {
        return itemImageURL;
    }

    public ItemImage itemImageURL(String itemImageURL) {
        this.itemImageURL = itemImageURL;
        return this;
    }

    public void setItemImageURL(String itemImageURL) {
        this.itemImageURL = itemImageURL;
    }

    public byte[] getItemImage() {
        return itemImage;
    }

    public ItemImage itemImage(byte[] itemImage) {
        this.itemImage = itemImage;
        return this;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemImageContentType() {
        return itemImageContentType;
    }

    public ItemImage itemImageContentType(String itemImageContentType) {
        this.itemImageContentType = itemImageContentType;
        return this;
    }

    public void setItemImageContentType(String itemImageContentType) {
        this.itemImageContentType = itemImageContentType;
    }

    public String getItemType() {
        return itemType;
    }

    public ItemImage itemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Status getImageOnTop() {
        return imageOnTop;
    }

    public ItemImage imageOnTop(Status imageOnTop) {
        this.imageOnTop = imageOnTop;
        return this;
    }

    public void setImageOnTop(Status imageOnTop) {
        this.imageOnTop = imageOnTop;
    }

    public Status getImageOnHover() {
        return imageOnHover;
    }

    public ItemImage imageOnHover(Status imageOnHover) {
        this.imageOnHover = imageOnHover;
        return this;
    }

    public void setImageOnHover(Status imageOnHover) {
        this.imageOnHover = imageOnHover;
    }

    public Long getLastRefreshedDate() {
        return lastRefreshedDate;
    }

    public ItemImage lastRefreshedDate(Long lastRefreshedDate) {
        this.lastRefreshedDate = lastRefreshedDate;
        return this;
    }

    public void setLastRefreshedDate(Long lastRefreshedDate) {
        this.lastRefreshedDate = lastRefreshedDate;
    }

    public Status getStatus() {
        return status;
    }

    public ItemImage status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public ItemImage createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ItemImage lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ItemImage createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ItemImage lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Item getItem() {
        return item;
    }

    public ItemImage item(Item item) {
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
        ItemImage itemImage = (ItemImage) o;
        if (itemImage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemImage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemImage{" +
            "id=" + getId() +
            ", itemImageName='" + getItemImageName() + "'" +
            ", itemImageDesc='" + getItemImageDesc() + "'" +
            ", itemImageSize=" + getItemImageSize() +
            ", itemImageURL='" + getItemImageURL() + "'" +
            ", itemImage='" + getItemImage() + "'" +
            ", itemImageContentType='" + getItemImageContentType() + "'" +
            ", itemType='" + getItemType() + "'" +
            ", imageOnTop='" + getImageOnTop() + "'" +
            ", imageOnHover='" + getImageOnHover() + "'" +
            ", lastRefreshedDate=" + getLastRefreshedDate() +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
