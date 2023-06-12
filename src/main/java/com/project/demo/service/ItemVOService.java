package com.project.demo.service;

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
	
}