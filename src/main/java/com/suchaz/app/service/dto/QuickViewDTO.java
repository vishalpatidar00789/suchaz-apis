package com.suchaz.app.service.dto;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import com.suchaz.app.domain.enumeration.Status;

/**
 * A DTO for the Item entity.
 */
public class QuickViewDTO implements Serializable {


    @NotNull
    private String itemId;

    @NotNull
    private String title;

    private Double bestPrice;

    private String brand;

    private String colors;

    @NotNull
    private Status availibity;

    private Boolean isFeatured;

    private Long lastFeaturedUPDDate;

    private Long categoryId;

    private String categoryName;

    private Long vendorId;

    private String vendorVendorName;
    
    @Lob
    private byte[] vendorImage;
    
    private String customerAvgRating;
    
    //private HashMap<String,String> mapOfItemAttributeAndValues;
    
    private Set<String> offers;
    
    @Lob
    private byte[] itemImageTop;
    
    @Lob
    private byte[] itemImageHover;


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


    public Long getCategoryId() {
        return categoryId;
    }

    public Double getBestPrice() {
		return bestPrice;
	}

	public void setBestPrice(Double bestPrice) {
		this.bestPrice = bestPrice;
	}

	public byte[] getItemImageTop() {
		return itemImageTop;
	}

	public void setItemImageTop(byte[] itemImageTop) {
		this.itemImageTop = itemImageTop;
	}

	public byte[] getItemImageHover() {
		return itemImageHover;
	}

	public void setItemImageHover(byte[] itemImageHover) {
		this.itemImageHover = itemImageHover;
	}

	public Boolean getIsFeatured() {
		return isFeatured;
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
    
	public byte[] getVendorImage() {
		return vendorImage;
	}

	public void setVendorImage(byte[] vendorImage) {
		this.vendorImage = vendorImage;
	}

	public String getCustomerAvgRating() {
		return customerAvgRating;
	}

	public void setCustomerAvgRating(String customerAvgRating) {
		this.customerAvgRating = customerAvgRating;
	}

	/*public HashMap<String, String> getMapOfItemAttributeAndValues() {
		return mapOfItemAttributeAndValues;
	}

	public void setMapOfItemAttributeAndValues(HashMap<String, String> mapOfItemAttributeAndValues) {
		this.mapOfItemAttributeAndValues = mapOfItemAttributeAndValues;
	}*/

	public Set<String> getOffers() {
		return offers;
	}

	public void setOffers(Set<String> offers) {
		this.offers = offers;
	}


}
