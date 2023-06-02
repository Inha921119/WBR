package com.project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.service.CharacterService;
import com.project.demo.util.Util;
import com.project.demo.vo.ResultData;
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
	
	@RequestMapping("/usr/character/doParticipationApp")
	@ResponseBody
	public String doParticipationApp(String name, int gender) {

		if (Util.empty(name)) {
			return Util.jsHistoryBack("제목을 입력해주세요");
		}

		ResultData<Integer> doParticipationAppRd = characterService.doParticipationApp(name, gender);
		
		if (doParticipationAppRd.isFail()) {
			return Util.jsHistoryBack(doParticipationAppRd.getMsg());
		}
		
		return Util.jsReplace(doParticipationAppRd.getMsg(), "/");
	}
	@RequestMapping("/usr/character/charNameDupCheck")
	@ResponseBody
	public ResultData charNameDupCheck(String charName) {
		
		if (Util.empty(charName)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		
		Character character = characterService.getCharacterByCharName(charName);
		
		if (character != null) {
			return ResultData.from("F-2", "이미 사용중인 이름입니다", "loginId", charName);
		}
		
		return ResultData.from("S-1", "사용 가능한 이름입니다", "loginId", charName);
	}
}
