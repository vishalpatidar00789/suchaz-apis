package com.suchaz.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ItemCommonAttribute.
 */
@Entity
@Table(name = "item_common_attribute")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemCommonAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "jhi_value", nullable = false)
    private String value;

    @ManyToOne
    private Item item;

    @ManyToOne
    private ItemAttributeType itemAttributeType;

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

    public ItemCommonAttribute name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public ItemCommonAttribute value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Item getItem() {
        return item;
    }

    public ItemCommonAttribute item(Item item) {
        this.item = item;
        return this;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemAttributeType getItemAttributeType() {
        return itemAttributeType;
    }

    public ItemCommonAttribute itemAttributeType(ItemAttributeType itemAttributeType) {
        this.itemAttributeType = itemAttributeType;
        return this;
    }

    public void setItemAttributeType(ItemAttributeType itemAttributeType) {
        this.itemAttributeType = itemAttributeType;
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
        ItemCommonAttribute itemCommonAttribute = (ItemCommonAttribute) o;
        if (itemCommonAttribute.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemCommonAttribute.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemCommonAttribute{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
