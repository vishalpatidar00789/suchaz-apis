package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the ItemImage entity.
 */
public class ItemImageDTO implements Serializable {

    private Long id;

    @NotNull
    private String itemImageName;

    private String itemImageDesc;

    @NotNull
    private Long itemImageSize;

    private String itemImageURL;

    @Lob
    private byte[] itemImage;
    private String itemImageContentType;

    private String itemType;

    @NotNull
    private Long lastRefreshedDate;

    @NotNull
    private Status status;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Long itemId;

    private String itemTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemImageName() {
        return itemImageName;
    }

    public void setItemImageName(String itemImageName) {
        this.itemImageName = itemImageName;
    }

    public String getItemImageDesc() {
        return itemImageDesc;
    }

    public void setItemImageDesc(String itemImageDesc) {
        this.itemImageDesc = itemImageDesc;
    }

    public Long getItemImageSize() {
        return itemImageSize;
    }

    public void setItemImageSize(Long itemImageSize) {
        this.itemImageSize = itemImageSize;
    }

    public String getItemImageURL() {
        return itemImageURL;
    }

    public void setItemImageURL(String itemImageURL) {
        this.itemImageURL = itemImageURL;
    }

    public byte[] getItemImage() {
        return itemImage;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemImageContentType() {
        return itemImageContentType;
    }

    public void setItemImageContentType(String itemImageContentType) {
        this.itemImageContentType = itemImageContentType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getLastRefreshedDate() {
        return lastRefreshedDate;
    }

    public void setLastRefreshedDate(Long lastRefreshedDate) {
        this.lastRefreshedDate = lastRefreshedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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

        ItemImageDTO itemImageDTO = (ItemImageDTO) o;
        if(itemImageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemImageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemImageDTO{" +
            "id=" + getId() +
            ", itemImageName='" + getItemImageName() + "'" +
            ", itemImageDesc='" + getItemImageDesc() + "'" +
            ", itemImageSize=" + getItemImageSize() +
            ", itemImageURL='" + getItemImageURL() + "'" +
            ", itemImage='" + getItemImage() + "'" +
            ", itemType='" + getItemType() + "'" +
            ", lastRefreshedDate=" + getLastRefreshedDate() +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
