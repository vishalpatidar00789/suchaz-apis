package com.suchaz.app.domain;

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
 * A SuchAzMenu.
 */
@Entity
@Table(name = "such_az_menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SuchAzMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "menu", nullable = false)
    private String menu;

    @Column(name = "menu_code")
    private String menuCode;

    @Column(name = "discription")
    private String discription;

    @Lob
    @Column(name = "menu_image")
    private byte[] menuImage;

    @Column(name = "menu_image_content_type")
    private String menuImageContentType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "is_exposed_to_menu", nullable = false)
    private Status isExposedToMenu;

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

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "such_az_menu_item",
               joinColumns = @JoinColumn(name="such_az_menus_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="items_id", referencedColumnName="id"))
    private Set<Item> items = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public SuchAzMenu title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMenu() {
        return menu;
    }

    public SuchAzMenu menu(String menu) {
        this.menu = menu;
        return this;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public SuchAzMenu menuCode(String menuCode) {
        this.menuCode = menuCode;
        return this;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getDiscription() {
        return discription;
    }

    public SuchAzMenu discription(String discription) {
        this.discription = discription;
        return this;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public byte[] getMenuImage() {
        return menuImage;
    }

    public SuchAzMenu menuImage(byte[] menuImage) {
        this.menuImage = menuImage;
        return this;
    }

    public void setMenuImage(byte[] menuImage) {
        this.menuImage = menuImage;
    }

    public String getMenuImageContentType() {
        return menuImageContentType;
    }

    public SuchAzMenu menuImageContentType(String menuImageContentType) {
        this.menuImageContentType = menuImageContentType;
        return this;
    }

    public void setMenuImageContentType(String menuImageContentType) {
        this.menuImageContentType = menuImageContentType;
    }

    public Status getStatus() {
        return status;
    }

    public SuchAzMenu status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getIsExposedToMenu() {
        return isExposedToMenu;
    }

    public SuchAzMenu isExposedToMenu(Status isExposedToMenu) {
        this.isExposedToMenu = isExposedToMenu;
        return this;
    }

    public void setIsExposedToMenu(Status isExposedToMenu) {
        this.isExposedToMenu = isExposedToMenu;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public SuchAzMenu createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SuchAzMenu lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SuchAzMenu createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SuchAzMenu lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Set<Item> getItems() {
        return items;
    }

    public SuchAzMenu items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public SuchAzMenu addItem(Item item) {
        this.items.add(item);
        item.getMenus().add(this);
        return this;
    }

    public SuchAzMenu removeItem(Item item) {
        this.items.remove(item);
        item.getMenus().remove(this);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
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
        SuchAzMenu suchAzMenu = (SuchAzMenu) o;
        if (suchAzMenu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), suchAzMenu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SuchAzMenu{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", menu='" + getMenu() + "'" +
            ", menuCode='" + getMenuCode() + "'" +
            ", discription='" + getDiscription() + "'" +
            ", menuImage='" + getMenuImage() + "'" +
            ", menuImageContentType='" + getMenuImageContentType() + "'" +
            ", status='" + getStatus() + "'" +
            ", isExposedToMenu='" + getIsExposedToMenu() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
