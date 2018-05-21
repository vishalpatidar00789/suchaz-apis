package com.suchaz.app.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Offer.
 */
@Entity
@Table(name = "offer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "offer_name", nullable = false)
    private String offerName;

    @Column(name = "offer_desc")
    private String offerDesc;

    @NotNull
    @Column(name = "offer_code", nullable = false)
    private String offerCode;

    @Lob
    @Column(name = "offer_tc")
    private String offerTC;

    @ManyToMany(mappedBy = "offers")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Item> items = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfferName() {
        return offerName;
    }

    public Offer offerName(String offerName) {
        this.offerName = offerName;
        return this;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferDesc() {
        return offerDesc;
    }

    public Offer offerDesc(String offerDesc) {
        this.offerDesc = offerDesc;
        return this;
    }

    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public Offer offerCode(String offerCode) {
        this.offerCode = offerCode;
        return this;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    public String getOfferTC() {
        return offerTC;
    }

    public Offer offerTC(String offerTC) {
        this.offerTC = offerTC;
        return this;
    }

    public void setOfferTC(String offerTC) {
        this.offerTC = offerTC;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Offer items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public Offer addItem(Item item) {
        this.items.add(item);
        item.getOffers().add(this);
        return this;
    }

    public Offer removeItem(Item item) {
        this.items.remove(item);
        item.getOffers().remove(this);
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
        Offer offer = (Offer) o;
        if (offer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), offer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Offer{" +
            "id=" + getId() +
            ", offerName='" + getOfferName() + "'" +
            ", offerDesc='" + getOfferDesc() + "'" +
            ", offerCode='" + getOfferCode() + "'" +
            ", offerTC='" + getOfferTC() + "'" +
            "}";
    }
}
