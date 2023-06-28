package com.project.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.InventoryRepository;
import com.project.demo.vo.Inventory;

@Service
public class InventoryService {
	private InventoryRepository inventoryRepository;
	
	@Autowired
	public InventoryService(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	public List<Inventory> getInventoryByPlayerId(int playerId) {
		return inventoryRepository.getInventoryByPlayerId(playerId);
	}
	public List<Inventory> getInventoryUsefulItemCodeByPlayerId(int playerId) {
		return inventoryRepository.getInventoryUsefulItemCodeByPlayerId(playerId);
	}

	public Inventory getInventoryItemByItemId(int playerId, int itemId) {
		return inventoryRepository.getInventoryItemByItemId(playerId, itemId);
	}

	public void useItem(int playerId, int itemId) {
		inventoryRepository.useItem(playerId, itemId);
	}

	public int checkExistItem(int playerId, int itemId) {
		return inventoryRepository.checkExistItem(playerId, itemId);
	}

	public void addItem(int playerId, int itemId) {
		inventoryRepository.addItem(playerId, itemId);
	}

	public void getItem(int playerId, int itemId) {
		inventoryRepository.getItem(playerId, itemId);
	}

	public void useEquip(int playerId, int itemId, int invenId) {
		inventoryRepository.useEquip(playerId, itemId, invenId);		
	}

	public Inventory getInventoryItemById(int invenId) {
		return inventoryRepository.getInventoryItemById(invenId);
	}

	public void reviveEquip(int playerId, int itemId, int invenId) {
		inventoryRepository.reviveEquip(playerId, itemId, invenId);				
	}

	public int getInventoryIdByPlayerIdAndItemIdAndDel(int playerId, int itemId, int delStatus) {
		return inventoryRepository.getInventoryIdByPlayerIdAndItemIdAndDel(playerId, itemId, delStatus);
	}

}
