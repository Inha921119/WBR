package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.service.EquipmentService;
import com.project.demo.service.MemberService;
import com.project.demo.service.PlayerService;
import com.project.demo.util.Util;
import com.project.demo.vo.Equipment;
import com.project.demo.vo.Player;
import com.project.demo.vo.Reply;
import com.project.demo.vo.ResultData;
import com.project.demo.vo.Rq;

@Controller
public class UsrPlayerController {
	
	private PlayerService playerService;
	private MemberService memberService;
	private EquipmentService equipmentService;
	private Rq rq;
	
	@Autowired
	public UsrPlayerController(PlayerService playerService, MemberService memberService, EquipmentService equipmentService, Rq rq) {
		this.playerService = playerService;
		this.memberService = memberService;
		this.equipmentService = equipmentService;
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
		equipmentService.createEquipment(doParticipationAppRd.getData1());
		
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
	public String showBattle(Model model, int id) {
		
		Player player = playerService.getPlayerByMemberId(id);
		
		List<Equipment> equipments = equipmentService.getEquipmentById(player.getId());
		
		model.addAttribute("equipments", equipments);
		
		return "usr/player/battle";
	}
	
	@RequestMapping("/usr/player/moveLocation")
	@ResponseBody
	public String moveLocation(int memberId, int location) {
		playerService.moveLocation(rq.getLoginedMemberId(), location);
		
		return playerService.getLocationNameById(location);
	}
	
	@RequestMapping("/usr/player/getNowLocation")
	@ResponseBody
	public ResultData<Player> getNowLocation(int id) {
		
		Player player = playerService.getPlayerByMemberId(id);
		
		if(player == null) {
			return ResultData.from("F-1", "해당 플레이어는 존재하지 않습니다.");
		}
		
		return ResultData.from("S-1", "플레이어 정보 조회 성공", "player", player);
	}
	
	@RequestMapping("/usr/player/changeActiveEffect")
	@ResponseBody
	public String changeActiveEffect(int memberId, int effect) {
		playerService.changeActiveEffect(rq.getLoginedMemberId(), effect);
		
		return playerService.getActionTypeNameById(effect);
	}
}
