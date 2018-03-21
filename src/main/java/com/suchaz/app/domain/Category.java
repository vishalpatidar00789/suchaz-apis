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
 * A Category.
 */
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category implements Serializable {

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
    @Column(name = "category_code", nullable = false)
    private String categoryCode;

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

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Item> items = new HashSet<>();

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Category> children = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAttributeType> itemAttributeTypes = new HashSet<>();

    @OneToMany(mappedBy = "category", fetch=FetchType.EAGER)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CategoryImage> categoryImages = new HashSet<>();

    @ManyToOne
    private Category parent;

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

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Category description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public Category categoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
        return this;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Status getStatus() {
        return status;
    }

    public Category status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Category createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Category lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Category createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Category lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Category items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public Category addItem(Item item) {
        this.items.add(item);
        item.setCategory(this);
        return this;
    }

    public Category removeItem(Item item) {
        this.items.remove(item);
        item.setCategory(null);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public Category children(Set<Category> categories) {
        this.children = categories;
        return this;
    }

    public Category addChild(Category category) {
        this.children.add(category);
        category.setParent(this);
        return this;
    }

    public Category removeChild(Category category) {
        this.children.remove(category);
        category.setParent(null);
        return this;
    }

    public void setChildren(Set<Category> categories) {
        this.children = categories;
    }

    public Set<ItemAttributeType> getItemAttributeTypes() {
        return itemAttributeTypes;
    }

    public Category itemAttributeTypes(Set<ItemAttributeType> itemAttributeTypes) {
        this.itemAttributeTypes = itemAttributeTypes;
        return this;
    }

    public Category addItemAttributeType(ItemAttributeType itemAttributeType) {
        this.itemAttributeTypes.add(itemAttributeType);
        itemAttributeType.setCategory(this);
        return this;
    }

    public Category removeItemAttributeType(ItemAttributeType itemAttributeType) {
        this.itemAttributeTypes.remove(itemAttributeType);
        itemAttributeType.setCategory(null);
        return this;
    }

    public void setItemAttributeTypes(Set<ItemAttributeType> itemAttributeTypes) {
        this.itemAttributeTypes = itemAttributeTypes;
    }

    public Set<CategoryImage> getCategoryImages() {
        return categoryImages;
    }

    public Category categoryImages(Set<CategoryImage> categoryImages) {
        this.categoryImages = categoryImages;
        return this;
    }

    public Category addCategoryImage(CategoryImage categoryImage) {
        this.categoryImages.add(categoryImage);
        categoryImage.setCategory(this);
        return this;
    }

    public Category removeCategoryImage(CategoryImage categoryImage) {
        this.categoryImages.remove(categoryImage);
        categoryImage.setCategory(null);
        return this;
    }

    public void setCategoryImages(Set<CategoryImage> categoryImages) {
        this.categoryImages = categoryImages;
    }

    public Category getParent() {
        return parent;
    }

    public Category parent(Category category) {
        this.parent = category;
        return this;
    }

    public void setParent(Category category) {
        this.parent = category;
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
        Category category = (Category) o;
        if (category.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", categoryCode='" + getCategoryCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
