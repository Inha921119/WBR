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
	
	public ItemVO getItemList() {
		
		ItemVO itemList = itemVORepository.getItemList();
		
		return itemList;
	}
	
	public ItemVO getItemByCode(int itemCode) {
		
		ItemVO item = itemVORepository.getItemByCode(itemCode);
		
		return item;
	}

	public List<ItemVO> getRecipeByItemCode(int recipeItem1, int recipeItem2, int recipeItem3) {
		return itemVORepository.getRecipeByItemCode(recipeItem1, recipeItem2, recipeItem3);
	}
	
}
