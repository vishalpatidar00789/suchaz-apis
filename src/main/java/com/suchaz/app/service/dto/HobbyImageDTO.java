package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the HobbyImage entity.
 */
public class HobbyImageDTO implements Serializable {

    private Long id;

    @NotNull
    private String hobbyImageName;

    private String hobbyImageDesc;

    @NotNull
    private Long hobbyImageSize;

    @Lob
    private byte[] hobbyImage;
    private String hobbyImageContentType;

    private String hobbyImageType;

    @NotNull
    private Status status;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Long hobbyId;

    private String hobbyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHobbyImageName() {
        return hobbyImageName;
    }

    public void setHobbyImageName(String hobbyImageName) {
        this.hobbyImageName = hobbyImageName;
    }

    public String getHobbyImageDesc() {
        return hobbyImageDesc;
    }

    public void setHobbyImageDesc(String hobbyImageDesc) {
        this.hobbyImageDesc = hobbyImageDesc;
    }

    public Long getHobbyImageSize() {
        return hobbyImageSize;
    }

    public void setHobbyImageSize(Long hobbyImageSize) {
        this.hobbyImageSize = hobbyImageSize;
    }

    public byte[] getHobbyImage() {
        return hobbyImage;
    }

    public void setHobbyImage(byte[] hobbyImage) {
        this.hobbyImage = hobbyImage;
    }

    public String getHobbyImageContentType() {
        return hobbyImageContentType;
    }

    public void setHobbyImageContentType(String hobbyImageContentType) {
        this.hobbyImageContentType = hobbyImageContentType;
    }

    public String getHobbyImageType() {
        return hobbyImageType;
    }

    public void setHobbyImageType(String hobbyImageType) {
        this.hobbyImageType = hobbyImageType;
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

    public Long getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Long hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HobbyImageDTO hobbyImageDTO = (HobbyImageDTO) o;
        if(hobbyImageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hobbyImageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HobbyImageDTO{" +
            "id=" + getId() +
            ", hobbyImageName='" + getHobbyImageName() + "'" +
            ", hobbyImageDesc='" + getHobbyImageDesc() + "'" +
            ", hobbyImageSize=" + getHobbyImageSize() +
            ", hobbyImage='" + getHobbyImage() + "'" +
            ", hobbyImageType='" + getHobbyImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
