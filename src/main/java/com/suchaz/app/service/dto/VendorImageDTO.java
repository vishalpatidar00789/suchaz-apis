package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the VendorImage entity.
 */
public class VendorImageDTO implements Serializable {

    private Long id;

    @NotNull
    private String vendorImageName;

    private String vendorImageDesc;

    @NotNull
    private Long vendorImageSize;

    @Lob
    private byte[] vendorImage;
    private String vendorImageContentType;

    private String vendorImageType;

    @NotNull
    private Status status;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Long vendorId;

    private String vendorVendorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorImageName() {
        return vendorImageName;
    }

    public void setVendorImageName(String vendorImageName) {
        this.vendorImageName = vendorImageName;
    }

    public String getVendorImageDesc() {
        return vendorImageDesc;
    }

    public void setVendorImageDesc(String vendorImageDesc) {
        this.vendorImageDesc = vendorImageDesc;
    }

    public Long getVendorImageSize() {
        return vendorImageSize;
    }

    public void setVendorImageSize(Long vendorImageSize) {
        this.vendorImageSize = vendorImageSize;
    }

    public byte[] getVendorImage() {
        return vendorImage;
    }

    public void setVendorImage(byte[] vendorImage) {
        this.vendorImage = vendorImage;
    }

    public String getVendorImageContentType() {
        return vendorImageContentType;
    }

    public void setVendorImageContentType(String vendorImageContentType) {
        this.vendorImageContentType = vendorImageContentType;
    }

    public String getVendorImageType() {
        return vendorImageType;
    }

    public void setVendorImageType(String vendorImageType) {
        this.vendorImageType = vendorImageType;
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

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorVendorName() {
        return vendorVendorName;
    }

    public void setVendorVendorName(String vendorVendorName) {
        this.vendorVendorName = vendorVendorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VendorImageDTO vendorImageDTO = (VendorImageDTO) o;
        if(vendorImageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorImageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorImageDTO{" +
            "id=" + getId() +
            ", vendorImageName='" + getVendorImageName() + "'" +
            ", vendorImageDesc='" + getVendorImageDesc() + "'" +
            ", vendorImageSize=" + getVendorImageSize() +
            ", vendorImage='" + getVendorImage() + "'" +
            ", vendorImageType='" + getVendorImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
