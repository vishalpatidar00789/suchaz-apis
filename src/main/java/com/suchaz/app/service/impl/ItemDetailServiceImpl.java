package com.suchaz.app.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchaz.app.repository.ItemImageRepository;
import com.suchaz.app.repository.ItemRepository;
import com.suchaz.app.repository.ItemReviewRepository;
import com.suchaz.app.service.ItemDetailService;
import com.suchaz.app.service.dto.ItemDTO;
import com.suchaz.app.service.dto.ItemDetailsDTO;
import com.suchaz.app.service.dto.ItemImageDTO;
import com.suchaz.app.service.dto.ItemReviewDTO;
import com.suchaz.app.service.mapper.ItemImageMapper;
import com.suchaz.app.service.mapper.ItemMapper;
import com.suchaz.app.service.mapper.ItemReviewMapper;

/**
 * Service Implementation for managing Item.
 */
@Service
@Transactional
public class ItemDetailServiceImpl implements ItemDetailService {

    private final Logger log = LoggerFactory.getLogger(ItemDetailServiceImpl.class);

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final ItemReviewRepository itemReviewRepository;

    private final ItemMapper itemMapper;
    private final ItemImageMapper itemImageMapper;
    private final ItemReviewMapper itemReviewMapper;

    public ItemDetailServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper,ItemImageRepository itemImageRepository,
    		ItemReviewRepository itemReviewRepository, ItemImageMapper itemImageMapper, ItemReviewMapper itemReviewMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.itemImageMapper = itemImageMapper;
        this.itemImageRepository = itemImageRepository;
        this.itemReviewMapper = itemReviewMapper;
        this.itemReviewRepository = itemReviewRepository;
    }

	@Override
	public ItemDetailsDTO findOne(Long itemId) {
		log.warn("Inside ItemDetailServiceImpl and looking for Item with Id: "+itemId);
		ItemDetailsDTO itemDetailsDTO = null;
		if(itemId != null)
		{
			itemDetailsDTO = new ItemDetailsDTO();
			ItemDTO itemDTO = itemMapper.toDto(itemRepository.findOne(itemId));
			ArrayList<ItemImageDTO> listItemImageDTOs = (ArrayList<ItemImageDTO>) itemImageMapper.toDto(itemImageRepository.findItemImagesByItemId(itemId));
			ArrayList<ItemReviewDTO> listItemReviewDTOs = (ArrayList<ItemReviewDTO>) itemReviewMapper.toDto(itemReviewRepository.findItemReviewByItemId(itemId));
			
			itemDetailsDTO.setItemDTO(itemDTO);
			itemDetailsDTO.setListOfImageDTOs(listItemImageDTOs);
			itemDetailsDTO.setListOfItemReviewDTOs(listItemReviewDTOs);
			
			
		}
		return itemDetailsDTO;
	}

   
}
