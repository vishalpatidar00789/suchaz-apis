package com.suchaz.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.domain.Item;
import com.suchaz.app.domain.ItemImage;
import com.suchaz.app.repository.ItemRepository;
import com.suchaz.app.service.QuickViewService;
import com.suchaz.app.service.dto.QuickViewDTO;

/**
 * Service Implementation for managing Item.
 */
@Service
@Transactional
public class QuickViewServiceImpl implements QuickViewService {

    private final Logger log = LoggerFactory.getLogger(QuickViewServiceImpl.class);

    private final ItemRepository itemRepository;


    public QuickViewServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Get one item by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public QuickViewDTO findOne(Long id) {
        log.debug("Request to get Item : {}", id);
        Item item = itemRepository.findOneWithEagerRelationships(id);
        return mapItemtoQuickViewDTO(item);
    }

	@Override
	public List<QuickViewDTO> findRangeOfItem(Long[] idList) {
		log.debug("Request to get ItemList");
		ArrayList<QuickViewDTO> listQuickViewDTO = null;
		if(idList.length!=0)
		{
			int counter;
			listQuickViewDTO = new ArrayList<>();
			for(counter =0;counter<idList.length;counter++)
			{
				Item item = itemRepository.findOne(idList[counter]);
				QuickViewDTO quickViewDTO =  null;
				if(item!=null)
				{
				 quickViewDTO = mapItemtoQuickViewDTO(item);
				}
				else
				{
					//Throw exception here.
				}
				listQuickViewDTO.add(quickViewDTO);
			}
		}
		return listQuickViewDTO;
	}
	
	private QuickViewDTO mapItemtoQuickViewDTO(Item item)
	{
		QuickViewDTO quickViewDTO = null;
		
		if(item!=null)
		{
			quickViewDTO = new QuickViewDTO();
			quickViewDTO.setAvailibity(item.getAvailibity());
			quickViewDTO.setBestPrice(item.getBestPrice()); // Subject to discussion when we need to filter among best price against vendor
			quickViewDTO.setBrand(item.getBrand());
			quickViewDTO.setCategoryId(item.getCategory().getId());
			quickViewDTO.setCategoryName(item.getCategory().getName());
			quickViewDTO.setColors(item.getColors());
			quickViewDTO.setIsFeatured(item.isIsFeatured());
			quickViewDTO.setItemId(item.getItemId());
			quickViewDTO.setLastFeaturedUPDDate(item.getLastFeaturedUPDDate());
			quickViewDTO.setTitle(item.getTitle());
			quickViewDTO.setVendorId(item.getVendor().getId());
			quickViewDTO.setVendorVendorName(item.getVendor().getVendorName());
			
			// Adding Images
			Set<ItemImage> itemImageSet = item.getItemImages();
			addItemImageToQuickViewDTO(quickViewDTO,itemImageSet);
			
		}
		
		return quickViewDTO;
		
	}

	private void addItemImageToQuickViewDTO(QuickViewDTO quickViewDTO, Set<ItemImage> itemImageSet) {
		
		
		// Need to add Logic here for filtering images to top and hover
		if(itemImageSet.iterator().hasNext()) {
		quickViewDTO.setItemImageTop(itemImageSet.iterator().next().getItemImage());
		}
		if(itemImageSet.iterator().hasNext()) {
			quickViewDTO.setItemImageHover(itemImageSet.iterator().next().getItemImage());
		}
		
	}
	
    
}