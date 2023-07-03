package com.project.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.ItemVORepository;
import com.project.demo.vo.ItemVO;

@Service
public class ItemVOService {
	private ItemVORepository itemVORepository;
	
	@Autowired
	public ItemVOService(ItemVORepository itemVORepository) {
		this.itemVORepository = itemVORepository;
	}
	
	public List<ItemVO> getItemList() {
		
		List<ItemVO> itemList = itemVORepository.getItemList();
		
		return itemList;
	}
	
	public ItemVO getItemByCode(int itemCode) {
		
		return itemVORepository.getItemByCode(itemCode);
	}

	public List<ItemVO> getItemListRecipe() {
		return itemVORepository.getItemListRecipe();
	}

	public ItemVO getRandomItem() {
		return itemVORepository.getRandomItem();
	}
	
}
