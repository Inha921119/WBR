package com.project.demo.service;

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

	public Equipment getEquipmentById(int playerId) {
		
		Equipment equipment = equipmentRepository.getEquipmentById(playerId);
		
		return equipment;
	}
}
