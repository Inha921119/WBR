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
	public String doParticipationApp(int memberId, String name, int gender) {

		if (Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요");
		}

		ResultData<Integer> doParticipationAppRd = characterService.doParticipationApp(memberId, name, gender);
		
		if (doParticipationAppRd.isFail()) {
			return Util.jsHistoryBack(doParticipationAppRd.getMsg());
		}
		
		return Util.jsReplace(doParticipationAppRd.getMsg(), "/");
	}
	@RequestMapping("/usr/character/charNameDupCheck")
	@ResponseBody
	public ResultData charNameDupCheck(String name) {
		
		if (Util.empty(name)) {
			return ResultData.from("F-1", "이름을 입력해주세요");
		}
		
		Character character = characterService.getCharacterByName(name);
		
		if (character != null) {
			return ResultData.from("F-2", "이미 사용중인 이름입니다", "loginId", name);
		}
		
		return ResultData.from("S-1", "사용 가능한 이름입니다", "loginId", name);
	}
	
	@RequestMapping("/usr/character/battle")
	public String showBattle() {
		return "usr/character/battle";
	}
}
