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
 * A Item.
 */
@Entity
@Table(name = "item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "item_id", nullable = false)
    private String itemId;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "vendor_item_type")
    private String vendorItemType;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "vendor_item_code", nullable = false)
    private String vendorItemCode;

    @Column(name = "best_price")
    private Double bestPrice;

    @NotNull
    @Column(name = "selling_price", nullable = false)
    private Double sellingPrice;

    @Column(name = "customer_avg_rating")
    private String customerAvgRating;

    @Column(name = "suchaz_avg_rating")
    private String suchazAvgRating;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Column(name = "item_url", nullable = false)
    private String itemURL;

    @Column(name = "brand")
    private String brand;

    @Column(name = "colors")
    private String colors;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "availibity", nullable = false)
    private Status availibity;

    @NotNull
    @Column(name = "last_refreshed", nullable = false)
    private Long lastRefreshed;

    @Column(name = "search_keywords")
    private String searchKeywords;

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

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WishListItem> wishListItems = new HashSet<>();

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BuyLaterListItem> buyLaterListItems = new HashSet<>();

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ActivityListItem> activityListItems = new HashSet<>();

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemCommonAttribute> itemCommonAttributes = new HashSet<>();

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemReview> itemReviews = new HashSet<>();

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserGiftWrapper> userGiftWrappers = new HashSet<>();

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemImage> itemImages = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "item_offer",
               joinColumns = @JoinColumn(name="items_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="offers_id", referencedColumnName="id"))
    private Set<Offer> offers = new HashSet<>();

    @ManyToOne
    private Category category;

    @ManyToOne
    private Vendor vendor;

    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Store> stores = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public Item itemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public Item title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVendorItemType() {
        return vendorItemType;
    }

    public Item vendorItemType(String vendorItemType) {
        this.vendorItemType = vendorItemType;
        return this;
    }

    public void setVendorItemType(String vendorItemType) {
        this.vendorItemType = vendorItemType;
    }

    public String getDescription() {
        return description;
    }

    public Item description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendorItemCode() {
        return vendorItemCode;
    }

    public Item vendorItemCode(String vendorItemCode) {
        this.vendorItemCode = vendorItemCode;
        return this;
    }

    public void setVendorItemCode(String vendorItemCode) {
        this.vendorItemCode = vendorItemCode;
    }

    public Double getBestPrice() {
        return bestPrice;
    }

    public Item bestPrice(Double bestPrice) {
        this.bestPrice = bestPrice;
        return this;
    }

    public void setBestPrice(Double bestPrice) {
        this.bestPrice = bestPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public Item sellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
        return this;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getCustomerAvgRating() {
        return customerAvgRating;
    }

    public Item customerAvgRating(String customerAvgRating) {
        this.customerAvgRating = customerAvgRating;
        return this;
    }

    public void setCustomerAvgRating(String customerAvgRating) {
        this.customerAvgRating = customerAvgRating;
    }

    public String getSuchazAvgRating() {
        return suchazAvgRating;
    }

    public Item suchazAvgRating(String suchazAvgRating) {
        this.suchazAvgRating = suchazAvgRating;
        return this;
    }

    public void setSuchazAvgRating(String suchazAvgRating) {
        this.suchazAvgRating = suchazAvgRating;
    }

    public Status getStatus() {
        return status;
    }

    public Item status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getItemURL() {
        return itemURL;
    }

    public Item itemURL(String itemURL) {
        this.itemURL = itemURL;
        return this;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public String getBrand() {
        return brand;
    }

    public Item brand(String brand) {
        this.brand = brand;
        return this;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColors() {
        return colors;
    }

    public Item colors(String colors) {
        this.colors = colors;
        return this;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public Status getAvailibity() {
        return availibity;
    }

    public Item availibity(Status availibity) {
        this.availibity = availibity;
        return this;
    }

    public void setAvailibity(Status availibity) {
        this.availibity = availibity;
    }

    public Long getLastRefreshed() {
        return lastRefreshed;
    }

    public Item lastRefreshed(Long lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
        return this;
    }

    public void setLastRefreshed(Long lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
    }

    public String getSearchKeywords() {
        return searchKeywords;
    }

    public Item searchKeywords(String searchKeywords) {
        this.searchKeywords = searchKeywords;
        return this;
    }

    public void setSearchKeywords(String searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Item createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Item lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Item createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Item lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Set<WishListItem> getWishListItems() {
        return wishListItems;
    }

    public Item wishListItems(Set<WishListItem> wishListItems) {
        this.wishListItems = wishListItems;
        return this;
    }

    public Item addWishListItem(WishListItem wishListItem) {
        this.wishListItems.add(wishListItem);
        wishListItem.setItem(this);
        return this;
    }

    public Item removeWishListItem(WishListItem wishListItem) {
        this.wishListItems.remove(wishListItem);
        wishListItem.setItem(null);
        return this;
    }

    public void setWishListItems(Set<WishListItem> wishListItems) {
        this.wishListItems = wishListItems;
    }

    public Set<BuyLaterListItem> getBuyLaterListItems() {
        return buyLaterListItems;
    }

    public Item buyLaterListItems(Set<BuyLaterListItem> buyLaterListItems) {
        this.buyLaterListItems = buyLaterListItems;
        return this;
    }

    public Item addBuyLaterListItem(BuyLaterListItem buyLaterListItem) {
        this.buyLaterListItems.add(buyLaterListItem);
        buyLaterListItem.setItem(this);
        return this;
    }

    public Item removeBuyLaterListItem(BuyLaterListItem buyLaterListItem) {
        this.buyLaterListItems.remove(buyLaterListItem);
        buyLaterListItem.setItem(null);
        return this;
    }

    public void setBuyLaterListItems(Set<BuyLaterListItem> buyLaterListItems) {
        this.buyLaterListItems = buyLaterListItems;
    }

    public Set<ActivityListItem> getActivityListItems() {
        return activityListItems;
    }

    public Item activityListItems(Set<ActivityListItem> activityListItems) {
        this.activityListItems = activityListItems;
        return this;
    }

    public Item addActivityListItem(ActivityListItem activityListItem) {
        this.activityListItems.add(activityListItem);
        activityListItem.setItem(this);
        return this;
    }

    public Item removeActivityListItem(ActivityListItem activityListItem) {
        this.activityListItems.remove(activityListItem);
        activityListItem.setItem(null);
        return this;
    }

    public void setActivityListItems(Set<ActivityListItem> activityListItems) {
        this.activityListItems = activityListItems;
    }

    public Set<ItemCommonAttribute> getItemCommonAttributes() {
        return itemCommonAttributes;
    }

    public Item itemCommonAttributes(Set<ItemCommonAttribute> itemCommonAttributes) {
        this.itemCommonAttributes = itemCommonAttributes;
        return this;
    }

    public Item addItemCommonAttribute(ItemCommonAttribute itemCommonAttribute) {
        this.itemCommonAttributes.add(itemCommonAttribute);
        itemCommonAttribute.setItem(this);
        return this;
    }

    public Item removeItemCommonAttribute(ItemCommonAttribute itemCommonAttribute) {
        this.itemCommonAttributes.remove(itemCommonAttribute);
        itemCommonAttribute.setItem(null);
        return this;
    }

    public void setItemCommonAttributes(Set<ItemCommonAttribute> itemCommonAttributes) {
        this.itemCommonAttributes = itemCommonAttributes;
    }

    public Set<ItemReview> getItemReviews() {
        return itemReviews;
    }

    public Item itemReviews(Set<ItemReview> itemReviews) {
        this.itemReviews = itemReviews;
        return this;
    }

    public Item addItemReview(ItemReview itemReview) {
        this.itemReviews.add(itemReview);
        itemReview.setItem(this);
        return this;
    }

    public Item removeItemReview(ItemReview itemReview) {
        this.itemReviews.remove(itemReview);
        itemReview.setItem(null);
        return this;
    }

    public void setItemReviews(Set<ItemReview> itemReviews) {
        this.itemReviews = itemReviews;
    }

    public Set<UserGiftWrapper> getUserGiftWrappers() {
        return userGiftWrappers;
    }

    public Item userGiftWrappers(Set<UserGiftWrapper> userGiftWrappers) {
        this.userGiftWrappers = userGiftWrappers;
        return this;
    }

    public Item addUserGiftWrapper(UserGiftWrapper userGiftWrapper) {
        this.userGiftWrappers.add(userGiftWrapper);
        userGiftWrapper.setItem(this);
        return this;
    }

    public Item removeUserGiftWrapper(UserGiftWrapper userGiftWrapper) {
        this.userGiftWrappers.remove(userGiftWrapper);
        userGiftWrapper.setItem(null);
        return this;
    }

    public void setUserGiftWrappers(Set<UserGiftWrapper> userGiftWrappers) {
        this.userGiftWrappers = userGiftWrappers;
    }

    public Set<ItemImage> getItemImages() {
        return itemImages;
    }

    public Item itemImages(Set<ItemImage> itemImages) {
        this.itemImages = itemImages;
        return this;
    }

    public Item addItemImage(ItemImage itemImage) {
        this.itemImages.add(itemImage);
        itemImage.setItem(this);
        return this;
    }

    public Item removeItemImage(ItemImage itemImage) {
        this.itemImages.remove(itemImage);
        itemImage.setItem(null);
        return this;
    }

    public void setItemImages(Set<ItemImage> itemImages) {
        this.itemImages = itemImages;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public Item offers(Set<Offer> offers) {
        this.offers = offers;
        return this;
    }

    public Item addOffer(Offer offer) {
        this.offers.add(offer);
        offer.getItems().add(this);
        return this;
    }

    public Item removeOffer(Offer offer) {
        this.offers.remove(offer);
        offer.getItems().remove(this);
        return this;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public Category getCategory() {
        return category;
    }

    public Item category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Item vendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public Item stores(Set<Store> stores) {
        this.stores = stores;
        return this;
    }

    public Item addStore(Store store) {
        this.stores.add(store);
        store.getItems().add(this);
        return this;
    }

    public Item removeStore(Store store) {
        this.stores.remove(store);
        store.getItems().remove(this);
        return this;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
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
        Item item = (Item) o;
        if (item.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Item{" +
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
            "}";
    }
}
