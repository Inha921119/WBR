package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.demo.service.ContentService;
import com.project.demo.service.GameLogService;
import com.project.demo.service.SkillService;
import com.project.demo.vo.GameLog;
import com.project.demo.vo.Rq;
import com.project.demo.vo.Skill;

@Controller
public class UsrContentContorller {
	
	private ContentService contentService;
	private SkillService skillService;
	private GameLogService gameLogService;
	private Rq rq;
	
	@Autowired
	public UsrContentContorller(ContentService contentService, SkillService skillService, GameLogService gameLogService, Rq rq) {
		this.contentService = contentService;
		this.skillService = skillService;
		this.gameLogService = gameLogService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/content/itemListAndRecipe")
	public String showItemListAndRecipe() {
		return "usr/content/itemListAndRecipe";
	}
		
	@RequestMapping("/usr/content/playingGameLog")
	public String showPlayingGameLog(Model model) {
		
		List<GameLog> gameLogs = gameLogService.getGameLogs();
		
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
	public String showWinnerRanking() {
		return "usr/content/winnerRanking";
	}
}
