package com.suchaz.app.service.dto;


import java.io.Serializable;

/**
 * A DTO for the UserProfile entity.
 */
public class MyAccountDTO implements Serializable {

   

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long suchAzUserId;
	private SuchAzUserDTO suchAzUserDTO;
    private UserProfileDTO userProfileDTO;
    private ActivityListDTO activityListDTOs;
    private WishListDTO wishListDTOs;
    private BuyLaterListDTO buyLaterListDTOs;
    

    public SuchAzUserDTO getSuchAzUserDTO() {
		return suchAzUserDTO;
	}

	public void setSuchAzUserDTO(SuchAzUserDTO suchAzUserDTO) {
		this.suchAzUserDTO = suchAzUserDTO;
	}

	public UserProfileDTO getUserProfileDTO() {
		return userProfileDTO;
	}

	public void setUserProfileDTO(UserProfileDTO userProfileDTO) {
		this.userProfileDTO = userProfileDTO;
	}

	public Long getSuchAzUserId() {
        return suchAzUserId;
    }

    public void setSuchAzUserId(Long suchAzUserId) {
        this.suchAzUserId = suchAzUserId;
    }

	public ActivityListDTO getActivityListDTOs() {
		return activityListDTOs;
	}

	public void setActivityListDTOs(ActivityListDTO activityListDTOs) {
		this.activityListDTOs = activityListDTOs;
	}

	public WishListDTO getWishListDTOs() {
		return wishListDTOs;
	}

	public void setWishListDTOs(WishListDTO wishListDTOs) {
		this.wishListDTOs = wishListDTOs;
	}

	public BuyLaterListDTO getBuyLaterListDTOs() {
		return buyLaterListDTOs;
	}

	public void setBuyLaterListDTOs(BuyLaterListDTO buyLaterListDTOs) {
		this.buyLaterListDTOs = buyLaterListDTOs;
	}

   
    /*@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyAccountDTO MyAccountDTO = (MyAccountDTO) o;
        if(MyAccountDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), MyAccountDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MyAccountDTO{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", dob=" + getDob() +
            ", aboutMe='" + getAboutMe() + "'" +
            ", relationshipStatus='" + getRelationshipStatus() + "'" +
            ", geoLocation='" + getGeoLocation() + "'" +
            ", prifilePic='" + getPrifilePic() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }*/
}
