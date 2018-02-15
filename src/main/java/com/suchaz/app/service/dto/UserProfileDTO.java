package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.suchaz.app.domain.enumeration.RelationshipStatus;

/**
 * A DTO for the UserProfile entity.
 */
public class UserProfileDTO implements Serializable {

    private Long id;

    @NotNull
    private String fullName;

    private Long dob;

    private String aboutMe;

    private RelationshipStatus relationshipStatus;

    private String geoLocation;

    @Lob
    private byte[] prifilePic;
    private String prifilePicContentType;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Long suchAzUserId;

    private String suchAzUserEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getDob() {
        return dob;
    }

    public void setDob(Long dob) {
        this.dob = dob;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public RelationshipStatus getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public byte[] getPrifilePic() {
        return prifilePic;
    }

    public void setPrifilePic(byte[] prifilePic) {
        this.prifilePic = prifilePic;
    }

    public String getPrifilePicContentType() {
        return prifilePicContentType;
    }

    public void setPrifilePicContentType(String prifilePicContentType) {
        this.prifilePicContentType = prifilePicContentType;
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

    public Long getSuchAzUserId() {
        return suchAzUserId;
    }

    public void setSuchAzUserId(Long suchAzUserId) {
        this.suchAzUserId = suchAzUserId;
    }

    public String getSuchAzUserEmail() {
        return suchAzUserEmail;
    }

    public void setSuchAzUserEmail(String suchAzUserEmail) {
        this.suchAzUserEmail = suchAzUserEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserProfileDTO userProfileDTO = (UserProfileDTO) o;
        if(userProfileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userProfileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", dob=" + getDob() +
            ", aboutMe='" + getAboutMe() + "'" +
            ", relationshipStatus='" + getRelationshipStatus() + "'" +
            ", geoLocation='" + getGeoLocation() + "'" +
            ", prifilePic='" + getPrifilePic() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
