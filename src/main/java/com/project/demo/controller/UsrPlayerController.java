package com.project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.service.MemberService;
import com.project.demo.service.PlayerService;
import com.project.demo.util.Util;
import com.project.demo.vo.Player;
import com.project.demo.vo.ResultData;
import com.project.demo.vo.Rq;

@Controller
public class UsrPlayerController {
	
	private PlayerService playerService;
	private MemberService memberService;
	private Rq rq;
	
	@Autowired
	public UsrPlayerController(PlayerService playerService, MemberService memberService, Rq rq) {
		this.playerService = playerService;
		this.memberService = memberService;
		this.rq = rq;
	}

	@RequestMapping("/usr/player/participationApp")
	public String showParticipationApp() {
		return "usr/player/participationApp";
	}
	
	@RequestMapping("/usr/player/doParticipationApp")
	@ResponseBody
	public String doParticipationApp(String name, int memberId, String image, int gender) {

		if (Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요");
		}

		ResultData<Integer> doParticipationAppRd = playerService.doParticipationApp(name, memberId, image, gender);
		
		if (doParticipationAppRd.isFail()) {
			return Util.jsHistoryBack(doParticipationAppRd.getMsg());
		}
		
		memberService.increseExistPlayer(memberId);
		
		return Util.jsReplace(doParticipationAppRd.getMsg(), "/");
	}
	@RequestMapping("/usr/player/playerNameDupCheck")
	@ResponseBody
	public ResultData playerNameDupCheck(String name) {
		
		if (Util.empty(name)) {
			return ResultData.from("F-1", "이름을 입력해주세요");
		}
		
		Player player = playerService.getPlayerByName(name);
		
		if (player != null) {
			return ResultData.from("F-2", "이미 사용중인 이름입니다", "loginId", name);
		}
		
		return ResultData.from("S-1", "사용 가능한 이름입니다", "loginId", name);
	}
	
	@RequestMapping("/usr/player/battle")
	public String showBattle() {
		return "usr/player/battle";
	}
}
