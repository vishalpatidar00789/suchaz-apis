package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the StoreImage entity.
 */
public class StoreImageDTO implements Serializable {

    private Long id;

    @NotNull
    private String storeImageName;

    private String storeImageDesc;

    @NotNull
    private Long storeImageSize;

    @Lob
    private byte[] storeImage;
    private String storeImageContentType;

    private String storeImageType;

    @NotNull
    private Status status;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Long storeId;

    private String storeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreImageName() {
        return storeImageName;
    }

    public void setStoreImageName(String storeImageName) {
        this.storeImageName = storeImageName;
    }

    public String getStoreImageDesc() {
        return storeImageDesc;
    }

    public void setStoreImageDesc(String storeImageDesc) {
        this.storeImageDesc = storeImageDesc;
    }

    public Long getStoreImageSize() {
        return storeImageSize;
    }

    public void setStoreImageSize(Long storeImageSize) {
        this.storeImageSize = storeImageSize;
    }

    public byte[] getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(byte[] storeImage) {
        this.storeImage = storeImage;
    }

    public String getStoreImageContentType() {
        return storeImageContentType;
    }

    public void setStoreImageContentType(String storeImageContentType) {
        this.storeImageContentType = storeImageContentType;
    }

    public String getStoreImageType() {
        return storeImageType;
    }

    public void setStoreImageType(String storeImageType) {
        this.storeImageType = storeImageType;
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

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StoreImageDTO storeImageDTO = (StoreImageDTO) o;
        if(storeImageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), storeImageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StoreImageDTO{" +
            "id=" + getId() +
            ", storeImageName='" + getStoreImageName() + "'" +
            ", storeImageDesc='" + getStoreImageDesc() + "'" +
            ", storeImageSize=" + getStoreImageSize() +
            ", storeImage='" + getStoreImage() + "'" +
            ", storeImageType='" + getStoreImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
