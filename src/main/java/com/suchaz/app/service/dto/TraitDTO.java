package com.suchaz.app.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.suchaz.app.domain.TraitImage;
import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the Trait entity.
 */
public class TraitDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String traitCode;

    @NotNull
    private Status isExposedToMenu;

    @NotNull
    private Status status;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;
    
    private Set<TraitImage> traitImages = new HashSet<>();
    
    public Set<TraitImage> getTraitImages() {
		return traitImages;
	}

	public void setTraitImages(Set<TraitImage> traitImages) {
		this.traitImages = traitImages;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTraitCode() {
        return traitCode;
    }

    public void setTraitCode(String traitCode) {
        this.traitCode = traitCode;
    }

    public Status getIsExposedToMenu() {
        return isExposedToMenu;
    }

    public void setIsExposedToMenu(Status isExposedToMenu) {
        this.isExposedToMenu = isExposedToMenu;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TraitDTO traitDTO = (TraitDTO) o;
        if(traitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), traitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TraitDTO{" +
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
