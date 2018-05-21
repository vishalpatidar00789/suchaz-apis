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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.suchaz.app.domain.enumeration.RelationshipStatus;

/**
 * A UserProfile.
 */
@Entity
@Table(name = "user_profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "dob")
    private Long dob;

    @Column(name = "about_me")
    private String aboutMe;

    @Enumerated(EnumType.STRING)
    @Column(name = "relationship_status")
    private RelationshipStatus relationshipStatus;

    @Column(name = "geo_location")
    private String geoLocation;

    @Lob
    @Column(name = "prifile_pic")
    private byte[] prifilePic;

    @Column(name = "prifile_pic_content_type")
    private String prifilePicContentType;

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

    @OneToOne
    @JoinColumn(unique = true)
    private SuchAzUser suchAzUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfile fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getDob() {
        return dob;
    }

    public UserProfile dob(Long dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(Long dob) {
        this.dob = dob;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public UserProfile aboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
        return this;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public RelationshipStatus getRelationshipStatus() {
        return relationshipStatus;
    }

    public UserProfile relationshipStatus(RelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
        return this;
    }

    public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public UserProfile geoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
        return this;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public byte[] getPrifilePic() {
        return prifilePic;
    }

    public UserProfile prifilePic(byte[] prifilePic) {
        this.prifilePic = prifilePic;
        return this;
    }

    public void setPrifilePic(byte[] prifilePic) {
        this.prifilePic = prifilePic;
    }

    public String getPrifilePicContentType() {
        return prifilePicContentType;
    }

    public UserProfile prifilePicContentType(String prifilePicContentType) {
        this.prifilePicContentType = prifilePicContentType;
        return this;
    }

    public void setPrifilePicContentType(String prifilePicContentType) {
        this.prifilePicContentType = prifilePicContentType;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public UserProfile createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public UserProfile lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public UserProfile createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public UserProfile lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public SuchAzUser getSuchAzUser() {
        return suchAzUser;
    }

    public UserProfile suchAzUser(SuchAzUser suchAzUser) {
        this.suchAzUser = suchAzUser;
        return this;
    }

    public void setSuchAzUser(SuchAzUser suchAzUser) {
        this.suchAzUser = suchAzUser;
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
        UserProfile userProfile = (UserProfile) o;
        if (userProfile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userProfile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserProfile{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", dob=" + getDob() +
            ", aboutMe='" + getAboutMe() + "'" +
            ", relationshipStatus='" + getRelationshipStatus() + "'" +
            ", geoLocation='" + getGeoLocation() + "'" +
            ", prifilePic='" + getPrifilePic() + "'" +
            ", prifilePicContentType='" + getPrifilePicContentType() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
