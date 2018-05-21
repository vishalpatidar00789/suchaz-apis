package com.suchaz.app.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the CategoryImage entity.
 */
public class CategoryImageDTO implements Serializable {

    private Long id;

    @NotNull
    private String categoryImageName;

    private String categoryImageDesc;

    @NotNull
    private Long categoryImageSize;

    @Lob
    private byte[] categoryImage;
    private String categoryImageContentType;

    private String categoryImageType;

    @NotNull
    private Status status;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Long categoryId;

    private String categoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryImageName() {
        return categoryImageName;
    }

    public void setCategoryImageName(String categoryImageName) {
        this.categoryImageName = categoryImageName;
    }

    public String getCategoryImageDesc() {
        return categoryImageDesc;
    }

    public void setCategoryImageDesc(String categoryImageDesc) {
        this.categoryImageDesc = categoryImageDesc;
    }

    public Long getCategoryImageSize() {
        return categoryImageSize;
    }

    public void setCategoryImageSize(Long categoryImageSize) {
        this.categoryImageSize = categoryImageSize;
    }

    public byte[] getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(byte[] categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryImageContentType() {
        return categoryImageContentType;
    }

    public void setCategoryImageContentType(String categoryImageContentType) {
        this.categoryImageContentType = categoryImageContentType;
    }

    public String getCategoryImageType() {
        return categoryImageType;
    }

    public void setCategoryImageType(String categoryImageType) {
        this.categoryImageType = categoryImageType;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoryImageDTO categoryImageDTO = (CategoryImageDTO) o;
        if(categoryImageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryImageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryImageDTO{" +
            "id=" + getId() +
            ", categoryImageName='" + getCategoryImageName() + "'" +
            ", categoryImageDesc='" + getCategoryImageDesc() + "'" +
            ", categoryImageSize=" + getCategoryImageSize() +
            ", categoryImage='" + getCategoryImage() + "'" +
            ", categoryImageType='" + getCategoryImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
