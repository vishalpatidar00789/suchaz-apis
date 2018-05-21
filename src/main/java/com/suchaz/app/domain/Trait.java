package com.suchaz.app.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suchaz.app.domain.enumeration.Status;

/**
 * A Trait.
 */
@Entity
@Table(name = "trait")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Trait implements Serializable {

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
    @Column(name = "trait_code", nullable = false)
    private String traitCode;

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

    @OneToMany(mappedBy = "trait", fetch=FetchType.EAGER)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TraitImage> traitImages = new HashSet<>();

    @ManyToMany(mappedBy = "traits")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SuchAzUser> suchAzUsers = new HashSet<>();

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

    public Trait name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Trait description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTraitCode() {
        return traitCode;
    }

    public Trait traitCode(String traitCode) {
        this.traitCode = traitCode;
        return this;
    }

    public void setTraitCode(String traitCode) {
        this.traitCode = traitCode;
    }

    public Status getIsExposedToMenu() {
        return isExposedToMenu;
    }

    public Trait isExposedToMenu(Status isExposedToMenu) {
        this.isExposedToMenu = isExposedToMenu;
        return this;
    }

    public void setIsExposedToMenu(Status isExposedToMenu) {
        this.isExposedToMenu = isExposedToMenu;
    }

    public Status getStatus() {
        return status;
    }

    public Trait status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Trait createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Trait lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Trait createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Trait lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Set<TraitImage> getTraitImages() {
        return traitImages;
    }

    public Trait traitImages(Set<TraitImage> traitImages) {
        this.traitImages = traitImages;
        return this;
    }

    public Trait addTraitImage(TraitImage traitImage) {
        this.traitImages.add(traitImage);
        traitImage.setTrait(this);
        return this;
    }

    public Trait removeTraitImage(TraitImage traitImage) {
        this.traitImages.remove(traitImage);
        traitImage.setTrait(null);
        return this;
    }

    public void setTraitImages(Set<TraitImage> traitImages) {
        this.traitImages = traitImages;
    }

    public Set<SuchAzUser> getSuchAzUsers() {
        return suchAzUsers;
    }

    public Trait suchAzUsers(Set<SuchAzUser> suchAzUsers) {
        this.suchAzUsers = suchAzUsers;
        return this;
    }

    public Trait addSuchAzUser(SuchAzUser suchAzUser) {
        this.suchAzUsers.add(suchAzUser);
        suchAzUser.getTraits().add(this);
        return this;
    }

    public Trait removeSuchAzUser(SuchAzUser suchAzUser) {
        this.suchAzUsers.remove(suchAzUser);
        suchAzUser.getTraits().remove(this);
        return this;
    }

    public void setSuchAzUsers(Set<SuchAzUser> suchAzUsers) {
        this.suchAzUsers = suchAzUsers;
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
        Trait trait = (Trait) o;
        if (trait.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trait.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Trait{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", traitCode='" + getTraitCode() + "'" +
            ", isExposedToMenu='" + getIsExposedToMenu() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
