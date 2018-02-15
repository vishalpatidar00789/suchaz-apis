package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the Hobby entity.
 */
public class HobbyDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String hobbyCode;

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

    public String getHobbyCode() {
        return hobbyCode;
    }

    public void setHobbyCode(String hobbyCode) {
        this.hobbyCode = hobbyCode;
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

        HobbyDTO hobbyDTO = (HobbyDTO) o;
        if(hobbyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hobbyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HobbyDTO{" +
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
