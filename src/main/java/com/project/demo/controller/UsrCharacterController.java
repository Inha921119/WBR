package com.project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
