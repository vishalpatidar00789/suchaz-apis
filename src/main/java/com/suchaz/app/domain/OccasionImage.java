package com.suchaz.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A OccasionImage.
 */
@Entity
@Table(name = "occasion_image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OccasionImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "occasion_image_name", nullable = false)
    private String occasionImageName;

    @Column(name = "occasion_image_desc")
    private String occasionImageDesc;

    @NotNull
    @Column(name = "occasion_image_size", nullable = false)
    private Long occasionImageSize;

    @Lob
    @Column(name = "occasion_image")
    private byte[] occasionImage;

    @Column(name = "occasion_image_content_type")
    private String occasionImageContentType;

    @Column(name = "occasion_image_type")
    private String occasionImageType;

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
    private Occassion occasion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOccasionImageName() {
        return occasionImageName;
    }

    public OccasionImage occasionImageName(String occasionImageName) {
        this.occasionImageName = occasionImageName;
        return this;
    }

    public void setOccasionImageName(String occasionImageName) {
        this.occasionImageName = occasionImageName;
    }

    public String getOccasionImageDesc() {
        return occasionImageDesc;
    }

    public OccasionImage occasionImageDesc(String occasionImageDesc) {
        this.occasionImageDesc = occasionImageDesc;
        return this;
    }

    public void setOccasionImageDesc(String occasionImageDesc) {
        this.occasionImageDesc = occasionImageDesc;
    }

    public Long getOccasionImageSize() {
        return occasionImageSize;
    }

    public OccasionImage occasionImageSize(Long occasionImageSize) {
        this.occasionImageSize = occasionImageSize;
        return this;
    }

    public void setOccasionImageSize(Long occasionImageSize) {
        this.occasionImageSize = occasionImageSize;
    }

    public byte[] getOccasionImage() {
        return occasionImage;
    }

    public OccasionImage occasionImage(byte[] occasionImage) {
        this.occasionImage = occasionImage;
        return this;
    }

    public void setOccasionImage(byte[] occasionImage) {
        this.occasionImage = occasionImage;
    }

    public String getOccasionImageContentType() {
        return occasionImageContentType;
    }

    public OccasionImage occasionImageContentType(String occasionImageContentType) {
        this.occasionImageContentType = occasionImageContentType;
        return this;
    }

    public void setOccasionImageContentType(String occasionImageContentType) {
        this.occasionImageContentType = occasionImageContentType;
    }

    public String getOccasionImageType() {
        return occasionImageType;
    }

    public OccasionImage occasionImageType(String occasionImageType) {
        this.occasionImageType = occasionImageType;
        return this;
    }

    public void setOccasionImageType(String occasionImageType) {
        this.occasionImageType = occasionImageType;
    }

    public Status getStatus() {
        return status;
    }

    public OccasionImage status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public OccasionImage createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public OccasionImage lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public OccasionImage createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public OccasionImage lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Occassion getOccasion() {
        return occasion;
    }

    public OccasionImage occasion(Occassion occassion) {
        this.occasion = occassion;
        return this;
    }

    public void setOccasion(Occassion occassion) {
        this.occasion = occassion;
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
        OccasionImage occasionImage = (OccasionImage) o;
        if (occasionImage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), occasionImage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OccasionImage{" +
            "id=" + getId() +
            ", occasionImageName='" + getOccasionImageName() + "'" +
            ", occasionImageDesc='" + getOccasionImageDesc() + "'" +
            ", occasionImageSize=" + getOccasionImageSize() +
            ", occasionImage='" + getOccasionImage() + "'" +
            ", occasionImageContentType='" + getOccasionImageContentType() + "'" +
            ", occasionImageType='" + getOccasionImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
