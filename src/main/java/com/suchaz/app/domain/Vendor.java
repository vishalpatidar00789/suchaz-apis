package com.suchaz.app.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suchaz.app.domain.enumeration.Status;

/**
 * A Vendor.
 */
@Entity
@Table(name = "vendor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vendor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "vendor_name", nullable = false)
    private String vendorName;

    @Column(name = "rate_limit")
    private Long rateLimit;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "end_date")
    private Long endDate;

    @Column(name = "access_key")
    private String accessKey;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "associate_key")
    private String associateKey;

    @Column(name = "affilite_id")
    private String affiliteId;

    @Column(name = "access_key_exp_date")
    private Long accessKeyExpDate;

    @Column(name = "scret_key_exp_date")
    private Long scretKeyExpDate;

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

    @OneToMany(mappedBy = "vendor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Item> items = new HashSet<>();

    @OneToMany(mappedBy = "vendor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<VendorImage> vendorImages = new HashSet<>();

    @ManyToOne
    private Country country;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public Vendor vendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Long getRateLimit() {
        return rateLimit;
    }

    public Vendor rateLimit(Long rateLimit) {
        this.rateLimit = rateLimit;
        return this;
    }

    public void setRateLimit(Long rateLimit) {
        this.rateLimit = rateLimit;
    }

    public Long getStartDate() {
        return startDate;
    }

    public Vendor startDate(Long startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public Vendor endDate(Long endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public Vendor accessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Vendor secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAssociateKey() {
        return associateKey;
    }

    public Vendor associateKey(String associateKey) {
        this.associateKey = associateKey;
        return this;
    }

    public void setAssociateKey(String associateKey) {
        this.associateKey = associateKey;
    }

    public String getAffiliteId() {
        return affiliteId;
    }

    public Vendor affiliteId(String affiliteId) {
        this.affiliteId = affiliteId;
        return this;
    }

    public void setAffiliteId(String affiliteId) {
        this.affiliteId = affiliteId;
    }

    public Long getAccessKeyExpDate() {
        return accessKeyExpDate;
    }

    public Vendor accessKeyExpDate(Long accessKeyExpDate) {
        this.accessKeyExpDate = accessKeyExpDate;
        return this;
    }

    public void setAccessKeyExpDate(Long accessKeyExpDate) {
        this.accessKeyExpDate = accessKeyExpDate;
    }

    public Long getScretKeyExpDate() {
        return scretKeyExpDate;
    }

    public Vendor scretKeyExpDate(Long scretKeyExpDate) {
        this.scretKeyExpDate = scretKeyExpDate;
        return this;
    }

    public void setScretKeyExpDate(Long scretKeyExpDate) {
        this.scretKeyExpDate = scretKeyExpDate;
    }

    public Status getStatus() {
        return status;
    }

    public Vendor status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Vendor createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public Vendor lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Vendor createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Vendor lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Vendor items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public Vendor addItem(Item item) {
        this.items.add(item);
        item.setVendor(this);
        return this;
    }

    public Vendor removeItem(Item item) {
        this.items.remove(item);
        item.setVendor(null);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<VendorImage> getVendorImages() {
        return vendorImages;
    }

    public Vendor vendorImages(Set<VendorImage> vendorImages) {
        this.vendorImages = vendorImages;
        return this;
    }

    public Vendor addVendorImage(VendorImage vendorImage) {
        this.vendorImages.add(vendorImage);
        vendorImage.setVendor(this);
        return this;
    }

    public Vendor removeVendorImage(VendorImage vendorImage) {
        this.vendorImages.remove(vendorImage);
        vendorImage.setVendor(null);
        return this;
    }

    public void setVendorImages(Set<VendorImage> vendorImages) {
        this.vendorImages = vendorImages;
    }

    public Country getCountry() {
        return country;
    }

    public Vendor country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
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
        Vendor vendor = (Vendor) o;
        if (vendor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vendor{" +
            "id=" + getId() +
            ", vendorName='" + getVendorName() + "'" +
            ", rateLimit=" + getRateLimit() +
            ", startDate=" + getStartDate() +
            ", endDate=" + getEndDate() +
            ", accessKey='" + getAccessKey() + "'" +
            ", secretKey='" + getSecretKey() + "'" +
            ", associateKey='" + getAssociateKey() + "'" +
            ", affiliteId='" + getAffiliteId() + "'" +
            ", accessKeyExpDate=" + getAccessKeyExpDate() +
            ", scretKeyExpDate=" + getScretKeyExpDate() +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
