package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the TraitImage entity.
 */
public class TraitImageDTO implements Serializable {

    private Long id;

    @NotNull
    private String personalityImageName;

    private String personalityImageDesc;

    @NotNull
    private Long personalityImageSize;

    @Lob
    private byte[] personalityImage;
    private String personalityImageContentType;

    private String personalityImageType;

    @NotNull
    private Status status;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Long traitId;

    private String traitName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonalityImageName() {
        return personalityImageName;
    }

    public void setPersonalityImageName(String personalityImageName) {
        this.personalityImageName = personalityImageName;
    }

    public String getPersonalityImageDesc() {
        return personalityImageDesc;
    }

    public void setPersonalityImageDesc(String personalityImageDesc) {
        this.personalityImageDesc = personalityImageDesc;
    }

    public Long getPersonalityImageSize() {
        return personalityImageSize;
    }

    public void setPersonalityImageSize(Long personalityImageSize) {
        this.personalityImageSize = personalityImageSize;
    }

    public byte[] getPersonalityImage() {
        return personalityImage;
    }

    public void setPersonalityImage(byte[] personalityImage) {
        this.personalityImage = personalityImage;
    }

    public String getPersonalityImageContentType() {
        return personalityImageContentType;
    }

    public void setPersonalityImageContentType(String personalityImageContentType) {
        this.personalityImageContentType = personalityImageContentType;
    }

    public String getPersonalityImageType() {
        return personalityImageType;
    }

    public void setPersonalityImageType(String personalityImageType) {
        this.personalityImageType = personalityImageType;
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

    public Long getTraitId() {
        return traitId;
    }

    public void setTraitId(Long traitId) {
        this.traitId = traitId;
    }

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TraitImageDTO traitImageDTO = (TraitImageDTO) o;
        if(traitImageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), traitImageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TraitImageDTO{" +
            "id=" + getId() +
            ", personalityImageName='" + getPersonalityImageName() + "'" +
            ", personalityImageDesc='" + getPersonalityImageDesc() + "'" +
            ", personalityImageSize=" + getPersonalityImageSize() +
            ", personalityImage='" + getPersonalityImage() + "'" +
            ", personalityImageType='" + getPersonalityImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
