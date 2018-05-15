package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the SuchAzMenu entity.
 */
public class SuchAzMenuDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String menu;

    private String menuCode;

    private String discription;

    @Lob
    private byte[] menuImage;
    private String menuImageContentType;

    @NotNull
    private Status status;

    @NotNull
    private Status isExposedToMenu;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Set<ItemDTO> items = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public byte[] getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(byte[] menuImage) {
        this.menuImage = menuImage;
    }

    public String getMenuImageContentType() {
        return menuImageContentType;
    }

    public void setMenuImageContentType(String menuImageContentType) {
        this.menuImageContentType = menuImageContentType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getIsExposedToMenu() {
        return isExposedToMenu;
    }

    public void setIsExposedToMenu(Status isExposedToMenu) {
        this.isExposedToMenu = isExposedToMenu;
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

    public Set<ItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<ItemDTO> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SuchAzMenuDTO suchAzMenuDTO = (SuchAzMenuDTO) o;
        if(suchAzMenuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), suchAzMenuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SuchAzMenuDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", menu='" + getMenu() + "'" +
            ", menuCode='" + getMenuCode() + "'" +
            ", discription='" + getDiscription() + "'" +
            ", menuImage='" + getMenuImage() + "'" +
            ", status='" + getStatus() + "'" +
            ", isExposedToMenu='" + getIsExposedToMenu() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
