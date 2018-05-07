package com.suchaz.app.service.dto;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * A DTO for the ProductSearchResults entity.
 */
public class ProductSearchResultDTO implements Serializable {

   

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long suchAzUserId;
	private ArrayList<QuickViewDTO> listOfItems;
	public Long getSuchAzUserId() {
		return suchAzUserId;
	}
	public void setSuchAzUserId(Long suchAzUserId) {
		this.suchAzUserId = suchAzUserId;
	}
	public ArrayList<QuickViewDTO> getListOfItems() {
		return listOfItems;
	}
	public void setListOfItems(ArrayList<QuickViewDTO> listOfItems) {
		this.listOfItems = listOfItems;
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
