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

/**
 * A ItemAttributeType.
 */
@Entity
@Table(name = "item_attribute_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemAttributeType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "attribute_type_name", nullable = false)
    private String attributeTypeName;

    @OneToMany(mappedBy = "itemAttributeType")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemCommonAttribute> itemCommonAttributes = new HashSet<>();

    @ManyToOne
    private Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeTypeName() {
        return attributeTypeName;
    }

    public ItemAttributeType attributeTypeName(String attributeTypeName) {
        this.attributeTypeName = attributeTypeName;
        return this;
    }

    public void setAttributeTypeName(String attributeTypeName) {
        this.attributeTypeName = attributeTypeName;
    }

    public Set<ItemCommonAttribute> getItemCommonAttributes() {
        return itemCommonAttributes;
    }

    public ItemAttributeType itemCommonAttributes(Set<ItemCommonAttribute> itemCommonAttributes) {
        this.itemCommonAttributes = itemCommonAttributes;
        return this;
    }

    public ItemAttributeType addItemCommonAttribute(ItemCommonAttribute itemCommonAttribute) {
        this.itemCommonAttributes.add(itemCommonAttribute);
        itemCommonAttribute.setItemAttributeType(this);
        return this;
    }

    public ItemAttributeType removeItemCommonAttribute(ItemCommonAttribute itemCommonAttribute) {
        this.itemCommonAttributes.remove(itemCommonAttribute);
        itemCommonAttribute.setItemAttributeType(null);
        return this;
    }

    public void setItemCommonAttributes(Set<ItemCommonAttribute> itemCommonAttributes) {
        this.itemCommonAttributes = itemCommonAttributes;
    }

    public Category getCategory() {
        return category;
    }

    public ItemAttributeType category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        ItemAttributeType itemAttributeType = (ItemAttributeType) o;
        if (itemAttributeType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAttributeType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemAttributeType{" +
            "id=" + getId() +
            ", attributeTypeName='" + getAttributeTypeName() + "'" +
            "}";
    }
}
