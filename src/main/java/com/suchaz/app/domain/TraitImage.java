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
 * A TraitImage.
 */
@Entity
@Table(name = "trait_image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TraitImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "personality_image_name", nullable = false)
    private String personalityImageName;

    @Column(name = "personality_image_desc")
    private String personalityImageDesc;

    @NotNull
    @Column(name = "personality_image_size", nullable = false)
    private Long personalityImageSize;

    @Lob
    @Column(name = "personality_image")
    private byte[] personalityImage;

    @Column(name = "personality_image_content_type")
    private String personalityImageContentType;

    @Column(name = "personality_image_type")
    private String personalityImageType;

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
    private Trait trait;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonalityImageName() {
        return personalityImageName;
    }

    public TraitImage personalityImageName(String personalityImageName) {
        this.personalityImageName = personalityImageName;
        return this;
    }

    public void setPersonalityImageName(String personalityImageName) {
        this.personalityImageName = personalityImageName;
    }

    public String getPersonalityImageDesc() {
        return personalityImageDesc;
    }

    public TraitImage personalityImageDesc(String personalityImageDesc) {
        this.personalityImageDesc = personalityImageDesc;
        return this;
    }

    public void setPersonalityImageDesc(String personalityImageDesc) {
        this.personalityImageDesc = personalityImageDesc;
    }

    public Long getPersonalityImageSize() {
        return personalityImageSize;
    }

    public TraitImage personalityImageSize(Long personalityImageSize) {
        this.personalityImageSize = personalityImageSize;
        return this;
    }

    public void setPersonalityImageSize(Long personalityImageSize) {
        this.personalityImageSize = personalityImageSize;
    }

    public byte[] getPersonalityImage() {
        return personalityImage;
    }

    public TraitImage personalityImage(byte[] personalityImage) {
        this.personalityImage = personalityImage;
        return this;
    }

    public void setPersonalityImage(byte[] personalityImage) {
        this.personalityImage = personalityImage;
    }

    public String getPersonalityImageContentType() {
        return personalityImageContentType;
    }

    public TraitImage personalityImageContentType(String personalityImageContentType) {
        this.personalityImageContentType = personalityImageContentType;
        return this;
    }

    public void setPersonalityImageContentType(String personalityImageContentType) {
        this.personalityImageContentType = personalityImageContentType;
    }

    public String getPersonalityImageType() {
        return personalityImageType;
    }

    public TraitImage personalityImageType(String personalityImageType) {
        this.personalityImageType = personalityImageType;
        return this;
    }

    public void setPersonalityImageType(String personalityImageType) {
        this.personalityImageType = personalityImageType;
    }

    public Status getStatus() {
        return status;
    }

    public TraitImage status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public TraitImage createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TraitImage lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TraitImage createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TraitImage lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Trait getTrait() {
        return trait;
    }

    public TraitImage trait(Trait trait) {
        this.trait = trait;
        return this;
    }

    public void setTrait(Trait trait) {
        this.trait = trait;
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
        TraitImage traitImage = (TraitImage) o;
        if (traitImage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), traitImage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TraitImage{" +
            "id=" + getId() +
            ", personalityImageName='" + getPersonalityImageName() + "'" +
            ", personalityImageDesc='" + getPersonalityImageDesc() + "'" +
            ", personalityImageSize=" + getPersonalityImageSize() +
            ", personalityImage='" + getPersonalityImage() + "'" +
            ", personalityImageContentType='" + getPersonalityImageContentType() + "'" +
            ", personalityImageType='" + getPersonalityImageType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
