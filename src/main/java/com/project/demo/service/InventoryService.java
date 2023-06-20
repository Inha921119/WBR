package com.project.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.InventoryRepository;
import com.project.demo.vo.Equipment;
import com.project.demo.vo.Inventory;

@Service
public class InventoryService {
	private InventoryRepository inventoryRepository;
	
	@Autowired
	public InventoryService(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	public List<Inventory> getInventoryById(int playerId) {
		return inventoryRepository.getInventoryById(playerId);
	}

	public Inventory getInventoryItemByItemId(int playerId, int itemId) {
		return inventoryRepository.getInventoryItemByItemId(playerId, itemId);
	}

	public void useItem(int playerId, int itemId) {
		inventoryRepository.useItem(playerId, itemId);
	}
}
