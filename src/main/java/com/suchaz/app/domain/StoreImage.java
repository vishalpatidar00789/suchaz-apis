package com.suchaz.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A StoreImage.
 */
@Entity
@Table(name = "store_image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StoreImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "store_image_name", nullable = false)
    private String storeImageName;

    @Column(name = "store_image_desc")
    private String storeImageDesc;

    @NotNull
    @Column(name = "store_image_size", nullable = false)
    private Long storeImageSize;

    @Lob
    @Column(name = "store_image")
    private byte[] storeImage;

    @Column(name = "store_image_content_type")
    private String storeImageContentType;

    @Column(name = "store_image_type")
    private String storeImageType;

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
    private Store store;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreImageName() {
        return storeImageName;
    }

    public StoreImage storeImageName(String storeImageName) {
        this.storeImageName = storeImageName;
        return this;
    }

    public void setStoreImageName(String storeImageName) {
        this.storeImageName = storeImageName;
    }

    public String getStoreImageDesc() {
        return storeImageDesc;
    }

    public StoreImage storeImageDesc(String storeImageDesc) {
        this.storeImageDesc = storeImageDesc;
        return this;
    }

    public void setStoreImageDesc(String storeImageDesc) {
        this.storeImageDesc = storeImageDesc;
    }

    public Long getStoreImageSize() {
        return storeImageSize;
    }

    public StoreImage storeImageSize(Long storeImageSize) {
        this.storeImageSize = storeImageSize;
        return this;
    }

    public void setStoreImageSize(Long storeImageSize) {
        this.storeImageSize = storeImageSize;
    }

    public byte[] getStoreImage() {
        return storeImage;
    }

    public StoreImage storeImage(byte[] storeImage) {
        this.storeImage = storeImage;
        return this;
    }

    public void setStoreImage(byte[] storeImage) {
        this.storeImage = storeImage;
    }

    public String getStoreImageContentType() {
        return storeImageContentType;
    }

    public StoreImage storeImageContentType(String storeImageContentType) {
        this.storeImageContentType = storeImageContentType;
        return this;
    }

    public void setStoreImageContentType(String storeImageContentType) {
        this.storeImageContentType = storeImageContentType;
    }

    public String getStoreImageType() {
        return storeImageType;
    }

    public StoreImage storeImageType(String storeImageType) {
        this.storeImageType = storeImageType;
        return this;
    }

    public void setStoreImageType(String storeImageType) {
        this.storeImageType = storeImageType;
    }

    public Status getStatus() {
        return status;
    }

    public StoreImage status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public StoreImage createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public StoreImage lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public StoreImage createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public StoreImage lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Store getStore() {
        return store;
    }

    public StoreImage store(Store store) {
        this.store = store;
        return this;
    }

    public void setStore(Store store) {
        this.store = store;
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
        StoreImage storeImage = (StoreImage) o;
        if (storeImage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), storeImage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StoreImage{" +
            "id=" + getId() +
            ", storeImageName='" + getStoreImageName() + "'" +
            ", storeImageDesc='" + getStoreImageDesc() + "'" +
            ", storeImageSize=" + getStoreImageSize() +
            ", storeImage='" + getStoreImage() + "'" +
            ", storeImageContentType='" + getStoreImageContentType() + "'" +
            ", storeImageType='" + getStoreImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
