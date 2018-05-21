package com.suchaz.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A UserGiftWrapper.
 */
@Entity
@Table(name = "user_gift_wrapper")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserGiftWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "gift_wrapper_img")
    private byte[] giftWrapperImg;

    @Column(name = "gift_wrapper_img_content_type")
    private String giftWrapperImgContentType;

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

    @ManyToOne
    private SuchAzUser suchAzUser;

    @ManyToOne
    private Item item;

    @ManyToOne
    private GiftWrapper giftWrapper;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getGiftWrapperImg() {
        return giftWrapperImg;
    }

    public UserGiftWrapper giftWrapperImg(byte[] giftWrapperImg) {
        this.giftWrapperImg = giftWrapperImg;
        return this;
    }

    public void setGiftWrapperImg(byte[] giftWrapperImg) {
        this.giftWrapperImg = giftWrapperImg;
    }

    public String getGiftWrapperImgContentType() {
        return giftWrapperImgContentType;
    }

    public UserGiftWrapper giftWrapperImgContentType(String giftWrapperImgContentType) {
        this.giftWrapperImgContentType = giftWrapperImgContentType;
        return this;
    }

    public void setGiftWrapperImgContentType(String giftWrapperImgContentType) {
        this.giftWrapperImgContentType = giftWrapperImgContentType;
    }

    public Status getStatus() {
        return status;
    }

    public UserGiftWrapper status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public UserGiftWrapper createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public UserGiftWrapper lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public UserGiftWrapper createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public UserGiftWrapper lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public SuchAzUser getSuchAzUser() {
        return suchAzUser;
    }

    public UserGiftWrapper suchAzUser(SuchAzUser suchAzUser) {
        this.suchAzUser = suchAzUser;
        return this;
    }

    public void setSuchAzUser(SuchAzUser suchAzUser) {
        this.suchAzUser = suchAzUser;
    }

    public Item getItem() {
        return item;
    }

    public UserGiftWrapper item(Item item) {
        this.item = item;
        return this;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public GiftWrapper getGiftWrapper() {
        return giftWrapper;
    }

    public UserGiftWrapper giftWrapper(GiftWrapper giftWrapper) {
        this.giftWrapper = giftWrapper;
        return this;
    }

    public void setGiftWrapper(GiftWrapper giftWrapper) {
        this.giftWrapper = giftWrapper;
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
        UserGiftWrapper userGiftWrapper = (UserGiftWrapper) o;
        if (userGiftWrapper.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userGiftWrapper.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserGiftWrapper{" +
            "id=" + getId() +
            ", giftWrapperImg='" + getGiftWrapperImg() + "'" +
            ", giftWrapperImgContentType='" + getGiftWrapperImgContentType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
