package com.project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.demo.service.ContentService;
import com.project.demo.vo.Rq;

@Controller
public class UsrContentContorller {
	
	private ContentService contentService;
	private Rq rq;
	
	@Autowired
	public UsrContentContorller(ContentService contentService, Rq rq) {
		this.contentService = contentService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/content/itemListAndRecipe")
	public String showItemListAndRecipe() {
		return "usr/content/itemListAndRecipe";
	}
	
	@RequestMapping("/usr/content/map")
	public String showMap() {
		return "usr/content/map";
	}
		
	@RequestMapping("/usr/content/playingGameLog")
	public String showPlayingGameLog() {
		return "usr/content/playingGameLog";
	}
	
	@RequestMapping("/usr/content/skillList")
	public String showSkillList() {
		return "usr/content/skillList";
	}
	
	@RequestMapping("/usr/content/winnerRanking")
	public String showWinnerRanking() {
		return "usr/content/winnerRanking";
	}
}
