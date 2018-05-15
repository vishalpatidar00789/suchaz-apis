package com.suchaz.app.service.dto;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * A DTO for the Item Details Page.
 */
public class ItemDetailsDTO implements Serializable {

   

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemDTO itemDTO;
	private ArrayList<ItemImageDTO> listOfImageDTOs;
	private ArrayList<ItemReviewDTO> listOfItemReviewDTOs;
	public ItemDTO getItemDTO() {
		return itemDTO;
	}
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}
	public ArrayList<ItemImageDTO> getListOfImageDTOs() {
		return listOfImageDTOs;
	}
	public void setListOfImageDTOs(ArrayList<ItemImageDTO> listOfImageDTOs) {
		this.listOfImageDTOs = listOfImageDTOs;
	}
	public ArrayList<ItemReviewDTO> getListOfItemReviewDTOs() {
		return listOfItemReviewDTOs;
	}
	public void setListOfItemReviewDTOs(ArrayList<ItemReviewDTO> listOfItemReviewDTOs) {
		this.listOfItemReviewDTOs = listOfItemReviewDTOs;
	}
	
}
