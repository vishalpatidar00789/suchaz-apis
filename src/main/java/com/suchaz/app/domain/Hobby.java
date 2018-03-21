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
 * A Hobby.
 */
@Entity
@Table(name = "hobby")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Hobby implements Serializable {

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
    @Column(name = "hobby_code", nullable = false)
    private String hobbyCode;

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

    @OneToMany(mappedBy = "hobby", fetch=FetchType.EAGER)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HobbyImage> hobbyImages = new HashSet<>();

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

    public Hobby name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Hobby description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHobbyCode() {
        return hobbyCode;
    }

    public Hobby hobbyCode(String hobbyCode) {
        this.hobbyCode = hobbyCode;
        return this;
    }

    public void setHobbyCode(String hobbyCode) {
        this.hobbyCode = hobbyCode;
    }

    public Status getIsExposedToMenu() {
        return isExposedToMenu;
    }

    public Hobby isExposedToMenu(Status isExposedToMenu) {
        this.isExposedToMenu = isExposedToMenu;
        return this;
    }

    public void setIsExposedToMenu(Status isExposedToMenu) {
        this.isExposedToMenu = isExposedToMenu;
    }

    public Status getStatus() {
        return status;
    }

    public Hobby status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Hobby createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Hobby lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Hobby createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Hobby lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Set<HobbyImage> getHobbyImages() {
        return hobbyImages;
    }

    public Hobby hobbyImages(Set<HobbyImage> hobbyImages) {
        this.hobbyImages = hobbyImages;
        return this;
    }

    public Hobby addHobbyImage(HobbyImage hobbyImage) {
        this.hobbyImages.add(hobbyImage);
        hobbyImage.setHobby(this);
        return this;
    }

    public Hobby removeHobbyImage(HobbyImage hobbyImage) {
        this.hobbyImages.remove(hobbyImage);
        hobbyImage.setHobby(null);
        return this;
    }

    public void setHobbyImages(Set<HobbyImage> hobbyImages) {
        this.hobbyImages = hobbyImages;
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
        Hobby hobby = (Hobby) o;
        if (hobby.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hobby.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Hobby{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", hobbyCode='" + getHobbyCode() + "'" +
            ", isExposedToMenu='" + getIsExposedToMenu() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
