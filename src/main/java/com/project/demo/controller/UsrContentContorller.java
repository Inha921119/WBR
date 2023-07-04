package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.demo.service.GameLogService;
import com.project.demo.service.GameRoundService;
import com.project.demo.service.RankingService;
import com.project.demo.service.SkillService;
import com.project.demo.vo.Equipment;
import com.project.demo.vo.GameLog;
import com.project.demo.vo.Inventory;
import com.project.demo.vo.Player;
import com.project.demo.vo.Skill;

@Controller
public class UsrContentContorller {
	
	private SkillService skillService;
	private GameLogService gameLogService;
	private GameRoundService gameRoundService;
	private RankingService rankingService;
	
	@Autowired
	public UsrContentContorller(SkillService skillService, GameLogService gameLogService, GameRoundService gameRoundService, RankingService rankingService) {
		this.skillService = skillService;
		this.gameLogService = gameLogService;
		this.gameRoundService = gameRoundService;
		this.rankingService = rankingService;
	}
	
	@RequestMapping("/usr/content/itemListAndRecipe")
	public String showItemListAndRecipe() {
		return "usr/content/itemListAndRecipe";
	}
		
	@RequestMapping("/usr/content/playingGameLog")
	public String showPlayingGameLog(Model model) {
		
		List<GameLog> gameLogs = gameLogService.getGameLogs(gameRoundService.getNowGameRound().getId());
		
		model.addAttribute("gameLogs", gameLogs);
		
		return "usr/content/playingGameLog";
	}
	
	@RequestMapping("/usr/content/skillList")
	public String showSkillList(Model model) {
		
		List<Skill> skillList = skillService.getAllSkill();
		
		model.addAttribute("skillList", skillList);
		
		return "usr/content/skillList";
	}
	
	@RequestMapping("/usr/content/winnerRanking")
	public String showWinnerRanking(Model model) {
		
		List<Player> winPlayers = rankingService.getWinPlayers();
		List<Equipment> winnerEquipments = rankingService.getWinnerEquipments();
		List<Inventory> winnerInventorys = rankingService.getWinnerInventorys();
		List<Skill> winnerSkills = rankingService.getWinnerSkills();
		
		model.addAttribute("winPlayers", winPlayers);
		model.addAttribute("winnerEquipments", winnerEquipments);
		model.addAttribute("winnerInventorys", winnerInventorys);
		model.addAttribute("winnerSkills", winnerSkills);
		
		return "usr/content/winnerRanking";
	}
}
