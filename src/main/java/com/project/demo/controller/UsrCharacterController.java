package com.project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.demo.service.CharacterService;
import com.project.demo.vo.Rq;

@Controller
public class UsrCharacterController {
	
	private CharacterService characterService;
	private Rq rq;
	
	@Autowired
	public UsrCharacterController(CharacterService characterService, Rq rq) {
		this.characterService = characterService;
		this.rq = rq;
	}

	@RequestMapping("/usr/character/participationApp")
	public String showParticipationApp() {
		return "usr/character/participationApp";
	}
}
