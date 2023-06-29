package com.project.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.EquipmentRepository;
import com.project.demo.vo.Equipment;

@Service
public class EquipmentService {
	
	private EquipmentRepository equipmentRepository;
	
	@Autowired
	public EquipmentService(EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}

	public void createEquipment(int id) {
		equipmentRepository.createEquipment(id);
	}

	public List<Equipment> getEquipmentsByPlayerId(int playerId) {
		
		return equipmentRepository.getEquipmentsByPlayerId(playerId);
	}
	
	public Equipment getEquipmentById(int equipId) {
		return equipmentRepository.getEquipmentById(equipId);
	}

	public void equipItem(int playerId, int itemId, int categoryNum, int equipId) {
		equipmentRepository.equipItem(playerId, itemId, categoryNum, equipId);
		
	}

	public void equipOff(int playerId, int categoryNum, int equipId) {
		equipmentRepository.equipOff(playerId, categoryNum, equipId);
	}

	public int getMaxEquipIdByItemId(int playerId, int categoryNum) {
		return equipmentRepository.getMaxEquipIdByItemId(playerId, categoryNum);
	}
	public int getMinEquipIdByItemId(int playerId, int categoryNum) {
		return equipmentRepository.getMinEquipIdByItemId(playerId, categoryNum);
	}

	
}
