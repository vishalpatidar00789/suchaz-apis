package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the ItemCommonAttribute entity.
 */
public class ItemCommonAttributeDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String value;

    private Long itemId;

    private String itemTitle;

    private Long itemAttributeTypeId;

    private String itemAttributeTypeAttributeTypeName;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Long getItemAttributeTypeId() {
        return itemAttributeTypeId;
    }

    public void setItemAttributeTypeId(Long itemAttributeTypeId) {
        this.itemAttributeTypeId = itemAttributeTypeId;
    }

    public String getItemAttributeTypeAttributeTypeName() {
        return itemAttributeTypeAttributeTypeName;
    }

    public void setItemAttributeTypeAttributeTypeName(String itemAttributeTypeAttributeTypeName) {
        this.itemAttributeTypeAttributeTypeName = itemAttributeTypeAttributeTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemCommonAttributeDTO itemCommonAttributeDTO = (ItemCommonAttributeDTO) o;
        if(itemCommonAttributeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemCommonAttributeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemCommonAttributeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
