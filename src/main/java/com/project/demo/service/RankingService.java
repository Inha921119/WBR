package com.project.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.RankingRepository;
import com.project.demo.vo.Equipment;
import com.project.demo.vo.Inventory;
import com.project.demo.vo.Player;
import com.project.demo.vo.Skill;

@Service
public class RankingService {
	private RankingRepository rankingRepository;
	
	@Autowired
	public RankingService(RankingRepository rankingRepository) {
		this.rankingRepository = rankingRepository;
	}

	public void registWinnerData(int playerId, int winRound) {
		rankingRepository.registWinPlayer(playerId, winRound);
		rankingRepository.registWinnerEquipment(playerId, winRound);
		rankingRepository.registWinnerInventory(playerId, winRound);
		rankingRepository.registWinnerSkill(playerId, winRound);
	}

	public List<Player> getWinPlayers() {
		return rankingRepository.getWinPlayers();
	}

	public List<Equipment> getWinnerEquipments() {
		return rankingRepository.getWinnerEquipments();
	}

	public List<Inventory> getWinnerInventorys() {
		return rankingRepository.getWinnerInventorys();
	}

	public List<Skill> getWinnerSkills() {
		return rankingRepository.getWinnerSkills();
	}
	
}
