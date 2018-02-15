package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the OccasionImage entity.
 */
public class OccasionImageDTO implements Serializable {

    private Long id;

    @NotNull
    private String occasionImageName;

    private String occasionImageDesc;

    @NotNull
    private Long occasionImageSize;

    @Lob
    private byte[] occasionImage;
    private String occasionImageContentType;

    private String occasionImageType;

    @NotNull
    private Status status;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Long occasionId;

    private String occasionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOccasionImageName() {
        return occasionImageName;
    }

    public void setOccasionImageName(String occasionImageName) {
        this.occasionImageName = occasionImageName;
    }

    public String getOccasionImageDesc() {
        return occasionImageDesc;
    }

    public void setOccasionImageDesc(String occasionImageDesc) {
        this.occasionImageDesc = occasionImageDesc;
    }

    public Long getOccasionImageSize() {
        return occasionImageSize;
    }

    public void setOccasionImageSize(Long occasionImageSize) {
        this.occasionImageSize = occasionImageSize;
    }

    public byte[] getOccasionImage() {
        return occasionImage;
    }

    public void setOccasionImage(byte[] occasionImage) {
        this.occasionImage = occasionImage;
    }

    public String getOccasionImageContentType() {
        return occasionImageContentType;
    }

    public void setOccasionImageContentType(String occasionImageContentType) {
        this.occasionImageContentType = occasionImageContentType;
    }

    public String getOccasionImageType() {
        return occasionImageType;
    }

    public void setOccasionImageType(String occasionImageType) {
        this.occasionImageType = occasionImageType;
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

    public Long getOccasionId() {
        return occasionId;
    }

    public void setOccasionId(Long occassionId) {
        this.occasionId = occassionId;
    }

    public String getOccasionName() {
        return occasionName;
    }

    public void setOccasionName(String occassionName) {
        this.occasionName = occassionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OccasionImageDTO occasionImageDTO = (OccasionImageDTO) o;
        if(occasionImageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), occasionImageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OccasionImageDTO{" +
            "id=" + getId() +
            ", occasionImageName='" + getOccasionImageName() + "'" +
            ", occasionImageDesc='" + getOccasionImageDesc() + "'" +
            ", occasionImageSize=" + getOccasionImageSize() +
            ", occasionImage='" + getOccasionImage() + "'" +
            ", occasionImageType='" + getOccasionImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
