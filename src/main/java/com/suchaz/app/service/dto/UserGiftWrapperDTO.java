package com.suchaz.app.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the UserGiftWrapper entity.
 */
public class UserGiftWrapperDTO implements Serializable {

    private Long id;

    @Lob
    private byte[] giftWrapperImg;
    private String giftWrapperImgContentType;

    @NotNull
    private Status status;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Long suchAzUserId;

    private String suchAzUserEmail;

    private Long itemId;

    private String itemTitle;

    private Long giftWrapperId;

    private String giftWrapperName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getGiftWrapperImg() {
        return giftWrapperImg;
    }

    public void setGiftWrapperImg(byte[] giftWrapperImg) {
        this.giftWrapperImg = giftWrapperImg;
    }

    public String getGiftWrapperImgContentType() {
        return giftWrapperImgContentType;
    }

    public void setGiftWrapperImgContentType(String giftWrapperImgContentType) {
        this.giftWrapperImgContentType = giftWrapperImgContentType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Long getSuchAzUserId() {
        return suchAzUserId;
    }

    public void setSuchAzUserId(Long suchAzUserId) {
        this.suchAzUserId = suchAzUserId;
    }

    public String getSuchAzUserEmail() {
        return suchAzUserEmail;
    }

    public void setSuchAzUserEmail(String suchAzUserEmail) {
        this.suchAzUserEmail = suchAzUserEmail;
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

    public Long getGiftWrapperId() {
        return giftWrapperId;
    }

    public void setGiftWrapperId(Long giftWrapperId) {
        this.giftWrapperId = giftWrapperId;
    }

    public String getGiftWrapperName() {
        return giftWrapperName;
    }

    public void setGiftWrapperName(String giftWrapperName) {
        this.giftWrapperName = giftWrapperName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserGiftWrapperDTO userGiftWrapperDTO = (UserGiftWrapperDTO) o;
        if(userGiftWrapperDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userGiftWrapperDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserGiftWrapperDTO{" +
            "id=" + getId() +
            ", giftWrapperImg='" + getGiftWrapperImg() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
