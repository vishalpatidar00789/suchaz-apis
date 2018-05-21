package com.suchaz.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A CategoryImage.
 */
@Entity
@Table(name = "category_image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CategoryImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "category_image_name", nullable = false)
    private String categoryImageName;

    @Column(name = "category_image_desc")
    private String categoryImageDesc;

    @NotNull
    @Column(name = "category_image_size", nullable = false)
    private Long categoryImageSize;

    @Lob
    @Column(name = "category_image")
    private byte[] categoryImage;

    @Column(name = "category_image_content_type")
    private String categoryImageContentType;

    @Column(name = "category_image_type")
    private String categoryImageType;

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
    private Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryImageName() {
        return categoryImageName;
    }

    public CategoryImage categoryImageName(String categoryImageName) {
        this.categoryImageName = categoryImageName;
        return this;
    }

    public void setCategoryImageName(String categoryImageName) {
        this.categoryImageName = categoryImageName;
    }

    public String getCategoryImageDesc() {
        return categoryImageDesc;
    }

    public CategoryImage categoryImageDesc(String categoryImageDesc) {
        this.categoryImageDesc = categoryImageDesc;
        return this;
    }

    public void setCategoryImageDesc(String categoryImageDesc) {
        this.categoryImageDesc = categoryImageDesc;
    }

    public Long getCategoryImageSize() {
        return categoryImageSize;
    }

    public CategoryImage categoryImageSize(Long categoryImageSize) {
        this.categoryImageSize = categoryImageSize;
        return this;
    }

    public void setCategoryImageSize(Long categoryImageSize) {
        this.categoryImageSize = categoryImageSize;
    }

    public byte[] getCategoryImage() {
        return categoryImage;
    }

    public CategoryImage categoryImage(byte[] categoryImage) {
        this.categoryImage = categoryImage;
        return this;
    }

    public void setCategoryImage(byte[] categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryImageContentType() {
        return categoryImageContentType;
    }

    public CategoryImage categoryImageContentType(String categoryImageContentType) {
        this.categoryImageContentType = categoryImageContentType;
        return this;
    }

    public void setCategoryImageContentType(String categoryImageContentType) {
        this.categoryImageContentType = categoryImageContentType;
    }

    public String getCategoryImageType() {
        return categoryImageType;
    }

    public CategoryImage categoryImageType(String categoryImageType) {
        this.categoryImageType = categoryImageType;
        return this;
    }

    public void setCategoryImageType(String categoryImageType) {
        this.categoryImageType = categoryImageType;
    }

    public Status getStatus() {
        return status;
    }

    public CategoryImage status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public CategoryImage createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public CategoryImage lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CategoryImage createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public CategoryImage lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Category getCategory() {
        return category;
    }

    public CategoryImage category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        CategoryImage categoryImage = (CategoryImage) o;
        if (categoryImage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryImage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryImage{" +
            "id=" + getId() +
            ", categoryImageName='" + getCategoryImageName() + "'" +
            ", categoryImageDesc='" + getCategoryImageDesc() + "'" +
            ", categoryImageSize=" + getCategoryImageSize() +
            ", categoryImage='" + getCategoryImage() + "'" +
            ", categoryImageContentType='" + getCategoryImageContentType() + "'" +
            ", categoryImageType='" + getCategoryImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
