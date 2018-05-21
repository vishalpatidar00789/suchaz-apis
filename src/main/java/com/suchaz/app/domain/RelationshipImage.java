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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A RelationshipImage.
 */
@Entity
@Table(name = "relationship_image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RelationshipImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "relationship_image_name", nullable = false)
    private String relationshipImageName;

    @Column(name = "relationship_image_desc")
    private String relationshipImageDesc;

    @NotNull
    @Column(name = "relationship_image_size", nullable = false)
    private Long relationshipImageSize;

    @Lob
    @Column(name = "relationship_image")
    private byte[] relationshipImage;

    @Column(name = "relationship_image_content_type")
    private String relationshipImageContentType;

    @Column(name = "relationship_image_type")
    private String relationshipImageType;

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
    private Relationship relationship;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelationshipImageName() {
        return relationshipImageName;
    }

    public RelationshipImage relationshipImageName(String relationshipImageName) {
        this.relationshipImageName = relationshipImageName;
        return this;
    }

    public void setRelationshipImageName(String relationshipImageName) {
        this.relationshipImageName = relationshipImageName;
    }

    public String getRelationshipImageDesc() {
        return relationshipImageDesc;
    }

    public RelationshipImage relationshipImageDesc(String relationshipImageDesc) {
        this.relationshipImageDesc = relationshipImageDesc;
        return this;
    }

    public void setRelationshipImageDesc(String relationshipImageDesc) {
        this.relationshipImageDesc = relationshipImageDesc;
    }

    public Long getRelationshipImageSize() {
        return relationshipImageSize;
    }

    public RelationshipImage relationshipImageSize(Long relationshipImageSize) {
        this.relationshipImageSize = relationshipImageSize;
        return this;
    }

    public void setRelationshipImageSize(Long relationshipImageSize) {
        this.relationshipImageSize = relationshipImageSize;
    }

    public byte[] getRelationshipImage() {
        return relationshipImage;
    }

    public RelationshipImage relationshipImage(byte[] relationshipImage) {
        this.relationshipImage = relationshipImage;
        return this;
    }

    public void setRelationshipImage(byte[] relationshipImage) {
        this.relationshipImage = relationshipImage;
    }

    public String getRelationshipImageContentType() {
        return relationshipImageContentType;
    }

    public RelationshipImage relationshipImageContentType(String relationshipImageContentType) {
        this.relationshipImageContentType = relationshipImageContentType;
        return this;
    }

    public void setRelationshipImageContentType(String relationshipImageContentType) {
        this.relationshipImageContentType = relationshipImageContentType;
    }

    public String getRelationshipImageType() {
        return relationshipImageType;
    }

    public RelationshipImage relationshipImageType(String relationshipImageType) {
        this.relationshipImageType = relationshipImageType;
        return this;
    }

    public void setRelationshipImageType(String relationshipImageType) {
        this.relationshipImageType = relationshipImageType;
    }

    public Status getStatus() {
        return status;
    }

    public RelationshipImage status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public RelationshipImage createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public RelationshipImage lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RelationshipImage createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public RelationshipImage lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public RelationshipImage relationship(Relationship relationship) {
        this.relationship = relationship;
        return this;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
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
        RelationshipImage relationshipImage = (RelationshipImage) o;
        if (relationshipImage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), relationshipImage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RelationshipImage{" +
            "id=" + getId() +
            ", relationshipImageName='" + getRelationshipImageName() + "'" +
            ", relationshipImageDesc='" + getRelationshipImageDesc() + "'" +
            ", relationshipImageSize=" + getRelationshipImageSize() +
            ", relationshipImage='" + getRelationshipImage() + "'" +
            ", relationshipImageContentType='" + getRelationshipImageContentType() + "'" +
            ", relationshipImageType='" + getRelationshipImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
