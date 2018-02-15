package com.suchaz.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A Relationship.
 */
@Entity
@Table(name = "relationship")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Relationship implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "relationship_code", nullable = false)
    private String relationshipCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "is_exposed_to_menu", nullable = false)
    private Status isExposedToMenu;

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

    @OneToMany(mappedBy = "relationship")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RelationshipImage> relationshipImages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Relationship name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Relationship description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelationshipCode() {
        return relationshipCode;
    }

    public Relationship relationshipCode(String relationshipCode) {
        this.relationshipCode = relationshipCode;
        return this;
    }

    public void setRelationshipCode(String relationshipCode) {
        this.relationshipCode = relationshipCode;
    }

    public Status getIsExposedToMenu() {
        return isExposedToMenu;
    }

    public Relationship isExposedToMenu(Status isExposedToMenu) {
        this.isExposedToMenu = isExposedToMenu;
        return this;
    }

    public void setIsExposedToMenu(Status isExposedToMenu) {
        this.isExposedToMenu = isExposedToMenu;
    }

    public Status getStatus() {
        return status;
    }

    public Relationship status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Relationship createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Relationship lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Relationship createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Relationship lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Set<RelationshipImage> getRelationshipImages() {
        return relationshipImages;
    }

    public Relationship relationshipImages(Set<RelationshipImage> relationshipImages) {
        this.relationshipImages = relationshipImages;
        return this;
    }

    public Relationship addRelationshipImage(RelationshipImage relationshipImage) {
        this.relationshipImages.add(relationshipImage);
        relationshipImage.setRelationship(this);
        return this;
    }

    public Relationship removeRelationshipImage(RelationshipImage relationshipImage) {
        this.relationshipImages.remove(relationshipImage);
        relationshipImage.setRelationship(null);
        return this;
    }

    public void setRelationshipImages(Set<RelationshipImage> relationshipImages) {
        this.relationshipImages = relationshipImages;
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
        Relationship relationship = (Relationship) o;
        if (relationship.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), relationship.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Relationship{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", relationshipCode='" + getRelationshipCode() + "'" +
            ", isExposedToMenu='" + getIsExposedToMenu() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
