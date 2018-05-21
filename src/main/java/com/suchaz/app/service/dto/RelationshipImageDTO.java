package com.suchaz.app.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the RelationshipImage entity.
 */
public class RelationshipImageDTO implements Serializable {

    private Long id;

    @NotNull
    private String relationshipImageName;

    private String relationshipImageDesc;

    @NotNull
    private Long relationshipImageSize;

    @Lob
    private byte[] relationshipImage;
    private String relationshipImageContentType;

    private String relationshipImageType;

    @NotNull
    private Status status;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Long relationshipId;

    private String relationshipName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelationshipImageName() {
        return relationshipImageName;
    }

    public void setRelationshipImageName(String relationshipImageName) {
        this.relationshipImageName = relationshipImageName;
    }

    public String getRelationshipImageDesc() {
        return relationshipImageDesc;
    }

    public void setRelationshipImageDesc(String relationshipImageDesc) {
        this.relationshipImageDesc = relationshipImageDesc;
    }

    public Long getRelationshipImageSize() {
        return relationshipImageSize;
    }

    public void setRelationshipImageSize(Long relationshipImageSize) {
        this.relationshipImageSize = relationshipImageSize;
    }

    public byte[] getRelationshipImage() {
        return relationshipImage;
    }

    public void setRelationshipImage(byte[] relationshipImage) {
        this.relationshipImage = relationshipImage;
    }

    public String getRelationshipImageContentType() {
        return relationshipImageContentType;
    }

    public void setRelationshipImageContentType(String relationshipImageContentType) {
        this.relationshipImageContentType = relationshipImageContentType;
    }

    public String getRelationshipImageType() {
        return relationshipImageType;
    }

    public void setRelationshipImageType(String relationshipImageType) {
        this.relationshipImageType = relationshipImageType;
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

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RelationshipImageDTO relationshipImageDTO = (RelationshipImageDTO) o;
        if(relationshipImageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), relationshipImageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RelationshipImageDTO{" +
            "id=" + getId() +
            ", relationshipImageName='" + getRelationshipImageName() + "'" +
            ", relationshipImageDesc='" + getRelationshipImageDesc() + "'" +
            ", relationshipImageSize=" + getRelationshipImageSize() +
            ", relationshipImage='" + getRelationshipImage() + "'" +
            ", relationshipImageType='" + getRelationshipImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
