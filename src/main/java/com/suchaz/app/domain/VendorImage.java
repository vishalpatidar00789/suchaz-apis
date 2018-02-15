package com.suchaz.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A VendorImage.
 */
@Entity
@Table(name = "vendor_image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VendorImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "vendor_image_name", nullable = false)
    private String vendorImageName;

    @Column(name = "vendor_image_desc")
    private String vendorImageDesc;

    @NotNull
    @Column(name = "vendor_image_size", nullable = false)
    private Long vendorImageSize;

    @Lob
    @Column(name = "vendor_image")
    private byte[] vendorImage;

    @Column(name = "vendor_image_content_type")
    private String vendorImageContentType;

    @Column(name = "vendor_image_type")
    private String vendorImageType;

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
    private Vendor vendor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorImageName() {
        return vendorImageName;
    }

    public VendorImage vendorImageName(String vendorImageName) {
        this.vendorImageName = vendorImageName;
        return this;
    }

    public void setVendorImageName(String vendorImageName) {
        this.vendorImageName = vendorImageName;
    }

    public String getVendorImageDesc() {
        return vendorImageDesc;
    }

    public VendorImage vendorImageDesc(String vendorImageDesc) {
        this.vendorImageDesc = vendorImageDesc;
        return this;
    }

    public void setVendorImageDesc(String vendorImageDesc) {
        this.vendorImageDesc = vendorImageDesc;
    }

    public Long getVendorImageSize() {
        return vendorImageSize;
    }

    public VendorImage vendorImageSize(Long vendorImageSize) {
        this.vendorImageSize = vendorImageSize;
        return this;
    }

    public void setVendorImageSize(Long vendorImageSize) {
        this.vendorImageSize = vendorImageSize;
    }

    public byte[] getVendorImage() {
        return vendorImage;
    }

    public VendorImage vendorImage(byte[] vendorImage) {
        this.vendorImage = vendorImage;
        return this;
    }

    public void setVendorImage(byte[] vendorImage) {
        this.vendorImage = vendorImage;
    }

    public String getVendorImageContentType() {
        return vendorImageContentType;
    }

    public VendorImage vendorImageContentType(String vendorImageContentType) {
        this.vendorImageContentType = vendorImageContentType;
        return this;
    }

    public void setVendorImageContentType(String vendorImageContentType) {
        this.vendorImageContentType = vendorImageContentType;
    }

    public String getVendorImageType() {
        return vendorImageType;
    }

    public VendorImage vendorImageType(String vendorImageType) {
        this.vendorImageType = vendorImageType;
        return this;
    }

    public void setVendorImageType(String vendorImageType) {
        this.vendorImageType = vendorImageType;
    }

    public Status getStatus() {
        return status;
    }

    public VendorImage status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public VendorImage createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorImage lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorImage createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorImage lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public VendorImage vendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
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
        VendorImage vendorImage = (VendorImage) o;
        if (vendorImage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorImage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorImage{" +
            "id=" + getId() +
            ", vendorImageName='" + getVendorImageName() + "'" +
            ", vendorImageDesc='" + getVendorImageDesc() + "'" +
            ", vendorImageSize=" + getVendorImageSize() +
            ", vendorImage='" + getVendorImage() + "'" +
            ", vendorImageContentType='" + getVendorImageContentType() + "'" +
            ", vendorImageType='" + getVendorImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
