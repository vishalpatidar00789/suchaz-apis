package com.suchaz.app.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the ItemAttributeType entity.
 */
public class ItemAttributeTypeDTO implements Serializable {

    private Long id;

    @NotNull
    private String attributeTypeName;

    private Long categoryId;

    private String categoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeTypeName() {
        return attributeTypeName;
    }

    public void setAttributeTypeName(String attributeTypeName) {
        this.attributeTypeName = attributeTypeName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemAttributeTypeDTO itemAttributeTypeDTO = (ItemAttributeTypeDTO) o;
        if(itemAttributeTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAttributeTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemAttributeTypeDTO{" +
            "id=" + getId() +
            ", attributeTypeName='" + getAttributeTypeName() + "'" +
            "}";
    }
}
