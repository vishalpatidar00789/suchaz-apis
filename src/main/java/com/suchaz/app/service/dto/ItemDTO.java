package com.suchaz.app.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the Item entity.
 */
public class ItemDTO implements Serializable {

    private Long id;

    @NotNull
    private String itemId;

    @NotNull
    private String title;

    private String vendorItemType;

    private String description;

    @NotNull
    private String vendorItemCode;

    private Double bestPrice;

    @NotNull
    private Double sellingPrice;

    private String customerAvgRating;

    private String suchazAvgRating;

    @NotNull
    private Status status;

    @NotNull
    private String itemURL;

    private String brand;

    private String colors;

    @NotNull
    private Status availibity;

    @NotNull
    private Long lastRefreshed;

    private String searchKeywords;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Boolean isFeatured;

    private Long lastFeaturedUPDDate;

    private Set<OfferDTO> offers = new HashSet<>();

    private Long categoryId;

    private String categoryName;

    private Long vendorId;

    private String vendorVendorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVendorItemType() {
        return vendorItemType;
    }

    public void setVendorItemType(String vendorItemType) {
        this.vendorItemType = vendorItemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendorItemCode() {
        return vendorItemCode;
    }

    public void setVendorItemCode(String vendorItemCode) {
        this.vendorItemCode = vendorItemCode;
    }

    public Double getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(Double bestPrice) {
        this.bestPrice = bestPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getCustomerAvgRating() {
        return customerAvgRating;
    }

    public void setCustomerAvgRating(String customerAvgRating) {
        this.customerAvgRating = customerAvgRating;
    }

    public String getSuchazAvgRating() {
        return suchazAvgRating;
    }

    public void setSuchazAvgRating(String suchazAvgRating) {
        this.suchazAvgRating = suchazAvgRating;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public Status getAvailibity() {
        return availibity;
    }

    public void setAvailibity(Status availibity) {
        this.availibity = availibity;
    }

    public Long getLastRefreshed() {
        return lastRefreshed;
    }

    public void setLastRefreshed(Long lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
    }

    public String getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(String searchKeywords) {
        this.searchKeywords = searchKeywords;
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

    public Boolean isIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Long getLastFeaturedUPDDate() {
        return lastFeaturedUPDDate;
    }

    public void setLastFeaturedUPDDate(Long lastFeaturedUPDDate) {
        this.lastFeaturedUPDDate = lastFeaturedUPDDate;
    }

    public Set<OfferDTO> getOffers() {
        return offers;
    }

    public void setOffers(Set<OfferDTO> offers) {
        this.offers = offers;
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

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorVendorName() {
        return vendorVendorName;
    }

    public void setVendorVendorName(String vendorVendorName) {
        this.vendorVendorName = vendorVendorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemDTO itemDTO = (ItemDTO) o;
        if(itemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
            "id=" + getId() +
            ", itemId='" + getItemId() + "'" +
            ", title='" + getTitle() + "'" +
            ", vendorItemType='" + getVendorItemType() + "'" +
            ", description='" + getDescription() + "'" +
            ", vendorItemCode='" + getVendorItemCode() + "'" +
            ", bestPrice=" + getBestPrice() +
            ", sellingPrice=" + getSellingPrice() +
            ", customerAvgRating='" + getCustomerAvgRating() + "'" +
            ", suchazAvgRating='" + getSuchazAvgRating() + "'" +
            ", status='" + getStatus() + "'" +
            ", itemURL='" + getItemURL() + "'" +
            ", brand='" + getBrand() + "'" +
            ", colors='" + getColors() + "'" +
            ", availibity='" + getAvailibity() + "'" +
            ", lastRefreshed=" + getLastRefreshed() +
            ", searchKeywords='" + getSearchKeywords() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", isFeatured='" + isIsFeatured() + "'" +
            ", lastFeaturedUPDDate=" + getLastFeaturedUPDDate() +
            "}";
    }
}
