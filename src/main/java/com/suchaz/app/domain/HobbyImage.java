package com.suchaz.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A HobbyImage.
 */
@Entity
@Table(name = "hobby_image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HobbyImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "hobby_image_name", nullable = false)
    private String hobbyImageName;

    @Column(name = "hobby_image_desc")
    private String hobbyImageDesc;

    @NotNull
    @Column(name = "hobby_image_size", nullable = false)
    private Long hobbyImageSize;

    @Lob
    @Column(name = "hobby_image")
    private byte[] hobbyImage;

    @Column(name = "hobby_image_content_type")
    private String hobbyImageContentType;

    @Column(name = "hobby_image_type")
    private String hobbyImageType;

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
    private Hobby hobby;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHobbyImageName() {
        return hobbyImageName;
    }

    public HobbyImage hobbyImageName(String hobbyImageName) {
        this.hobbyImageName = hobbyImageName;
        return this;
    }

    public void setHobbyImageName(String hobbyImageName) {
        this.hobbyImageName = hobbyImageName;
    }

    public String getHobbyImageDesc() {
        return hobbyImageDesc;
    }

    public HobbyImage hobbyImageDesc(String hobbyImageDesc) {
        this.hobbyImageDesc = hobbyImageDesc;
        return this;
    }

    public void setHobbyImageDesc(String hobbyImageDesc) {
        this.hobbyImageDesc = hobbyImageDesc;
    }

    public Long getHobbyImageSize() {
        return hobbyImageSize;
    }

    public HobbyImage hobbyImageSize(Long hobbyImageSize) {
        this.hobbyImageSize = hobbyImageSize;
        return this;
    }

    public void setHobbyImageSize(Long hobbyImageSize) {
        this.hobbyImageSize = hobbyImageSize;
    }

    public byte[] getHobbyImage() {
        return hobbyImage;
    }

    public HobbyImage hobbyImage(byte[] hobbyImage) {
        this.hobbyImage = hobbyImage;
        return this;
    }

    public void setHobbyImage(byte[] hobbyImage) {
        this.hobbyImage = hobbyImage;
    }

    public String getHobbyImageContentType() {
        return hobbyImageContentType;
    }

    public HobbyImage hobbyImageContentType(String hobbyImageContentType) {
        this.hobbyImageContentType = hobbyImageContentType;
        return this;
    }

    public void setHobbyImageContentType(String hobbyImageContentType) {
        this.hobbyImageContentType = hobbyImageContentType;
    }

    public String getHobbyImageType() {
        return hobbyImageType;
    }

    public HobbyImage hobbyImageType(String hobbyImageType) {
        this.hobbyImageType = hobbyImageType;
        return this;
    }

    public void setHobbyImageType(String hobbyImageType) {
        this.hobbyImageType = hobbyImageType;
    }

    public Status getStatus() {
        return status;
    }

    public HobbyImage status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public HobbyImage createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public HobbyImage lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public HobbyImage createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public HobbyImage lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public HobbyImage hobby(Hobby hobby) {
        this.hobby = hobby;
        return this;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
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
        HobbyImage hobbyImage = (HobbyImage) o;
        if (hobbyImage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hobbyImage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HobbyImage{" +
            "id=" + getId() +
            ", hobbyImageName='" + getHobbyImageName() + "'" +
            ", hobbyImageDesc='" + getHobbyImageDesc() + "'" +
            ", hobbyImageSize=" + getHobbyImageSize() +
            ", hobbyImage='" + getHobbyImage() + "'" +
            ", hobbyImageContentType='" + getHobbyImageContentType() + "'" +
            ", hobbyImageType='" + getHobbyImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
