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

	public List<Equipment> getEquipmentById(int playerId) {
		
		return equipmentRepository.getEquipmentById(playerId);
	}

	public void equipItem(int playerId, int itemId, int categoryNum) {
		equipmentRepository.equipItem(playerId, itemId, categoryNum);
		
	}

	public void equipOff(int playerId, int categoryNum) {
		equipmentRepository.equipOff(playerId, categoryNum);
	}
}
