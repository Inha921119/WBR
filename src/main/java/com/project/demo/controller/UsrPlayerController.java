package com.project.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.service.EquipmentService;
import com.project.demo.service.InventoryService;
import com.project.demo.service.ItemVOService;
import com.project.demo.service.MemberService;
import com.project.demo.service.PlayerService;
import com.project.demo.service.SkillService;
import com.project.demo.util.Util;
import com.project.demo.vo.Equipment;
import com.project.demo.vo.Inventory;
import com.project.demo.vo.ItemVO;
import com.project.demo.vo.Player;
import com.project.demo.vo.ResultData;
import com.project.demo.vo.Rq;
import com.project.demo.vo.Skill;

@Controller
public class UsrPlayerController {
	
	private PlayerService playerService;
	private MemberService memberService;
	private ItemVOService itemVOService;
	private EquipmentService equipmentService;
	private InventoryService inventoryService;
	private SkillService skillService;
	private Rq rq;
	
	@Autowired
	public UsrPlayerController(PlayerService playerService, MemberService memberService, ItemVOService itemVOService, EquipmentService equipmentService, InventoryService inventoryService, SkillService skillService, Rq rq) {
		this.playerService = playerService;
		this.memberService = memberService;
		this.itemVOService = itemVOService;
		this.equipmentService = equipmentService;
		this.inventoryService = inventoryService;
		this.skillService = skillService;
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
		
		List<Equipment> equipments = equipmentService.getEquipmentsByPlayerId(player.getId());
		List<Inventory> inventory = inventoryService.getInventoryByPlayerId(player.getId());
		List<Skill> skills = skillService.getSkillByPlayerId(player.getId());
		
		model.addAttribute("equipments", equipments);
		model.addAttribute("inventory", inventory);
		model.addAttribute("skills", skills);
		
		return "usr/player/battle";
	}
	
	@RequestMapping("/usr/player/battlePhase")
	public String showbattlePhase(Model model, int id) {
		
		Player player1 = playerService.getPlayerByMemberId(id);
		Player player2 = playerService.getPlayerByLocation(player1.getNowLocation(), id);
		
		List<Equipment> equipments1 = equipmentService.getEquipmentsByPlayerId(player1.getId());
		List<Inventory> inventory1 = inventoryService.getInventoryByPlayerId(player1.getId());
		List<Skill> skills1 = skillService.getSkillByPlayerId(player1.getId());
		List<Equipment> equipments2 = equipmentService.getEquipmentsByPlayerId(player2.getId());
		List<Inventory> inventory2 = inventoryService.getInventoryByPlayerId(player2.getId());
		List<Skill> skills2 = skillService.getSkillByPlayerId(player2.getId());
		
		model.addAttribute("equipments1", equipments1);
		model.addAttribute("inventory1", inventory1);
		model.addAttribute("skills1", skills1);
		model.addAttribute("player1", player1);
		model.addAttribute("equipments2", equipments2);
		model.addAttribute("inventory2", inventory2);
		model.addAttribute("skills2", skills2);
		model.addAttribute("player2", player2);
		
		return "usr/player/battlePhase";
	}
	
	@RequestMapping("/usr/player/moveLocation")
	@ResponseBody
	public String moveLocation(int memberId, int location) {
		playerService.moveLocation(memberId, location);
		
		return playerService.getLocationNameById(location);
	}
	
	@RequestMapping("/usr/player/showStatus")
	@ResponseBody
	public Player showStatus(int memberId) {
		return playerService.getPlayerByMemberId(memberId);
	}
	
	@RequestMapping("/usr/player/changeActionType")
	@ResponseBody
	public String changeActionType(int memberId, int type) {
		int nowActionType = playerService.getNowActionType(memberId);
		
		if (nowActionType != type) {
			switch (nowActionType) {
			case 1:
				playerService.doChangeStatus(memberId, "increseAttackPoint", 0, 0);
				playerService.doChangeStatus(memberId, "increseDefencePoint", 0, 0);
				playerService.doChangeStatus(memberId, "increseHitRate", 0, 0);
				playerService.doChangeStatus(memberId, "increseMissRate", 0, 0);
				playerService.doChangeStatus(memberId, "findEnemyRate", 0, 0);
				playerService.doChangeStatus(memberId, "findItemRate", 0, 0);
				break;
			case 2:
				playerService.doChangeStatus(memberId, "increseAttackPoint", 5, 1);
				playerService.doChangeStatus(memberId, "increseDefencePoint", 5, 0);
				playerService.doChangeStatus(memberId, "increseHitRate", 10, 1);
				playerService.doChangeStatus(memberId, "increseMissRate", 5, 0);
				playerService.doChangeStatus(memberId, "findEnemyRate", 10, 1);
				break;
			case 3:
				playerService.doChangeStatus(memberId, "increseAttackPoint", 3, 0);
				playerService.doChangeStatus(memberId, "increseDefencePoint", 5, 1);
				break;
			case 4:
				playerService.doChangeStatus(memberId, "findEnemyRate", 20, 1);
				playerService.doChangeStatus(memberId, "findItemRate", 20, 1);
				break;
			case 5:
				playerService.doChangeStatus(memberId, "increseDefencePoint", 5, 1);
				playerService.doChangeStatus(memberId, "increseMissRate", 10, 1);
				playerService.doChangeStatus(memberId, "findEnemyRate", 10, 1);
				break;
			case 6:
				playerService.doChangeStatus(memberId, "increseMissRate", 20, 1);
				playerService.doChangeStatus(memberId, "findEnemyRate", 30, 1);
				break;
			}
			switch (type) {
			case 1:
				playerService.doChangeStatus(memberId, "increseAttackPoint", 0, 0);
				playerService.doChangeStatus(memberId, "increseDefencePoint", 0, 0);
				playerService.doChangeStatus(memberId, "increseHitRate", 0, 0);
				playerService.doChangeStatus(memberId, "increseMissRate", 0, 0);
				playerService.doChangeStatus(memberId, "findEnemyRate", 0, 0);
				playerService.doChangeStatus(memberId, "findItemRate", 0, 0);
				break;
			case 2:
				playerService.doChangeStatus(memberId, "increseAttackPoint", 5, 0);
				playerService.doChangeStatus(memberId, "increseDefencePoint", 5, 1);
				playerService.doChangeStatus(memberId, "increseHitRate", 10, 0);
				playerService.doChangeStatus(memberId, "increseMissRate", 5, 1);
				playerService.doChangeStatus(memberId, "findEnemyRate", 10, 0);
				break;
			case 3:
				playerService.doChangeStatus(memberId, "increseAttackPoint", 3, 1);
				playerService.doChangeStatus(memberId, "increseDefencePoint", 5, 0);
				break;
			case 4:
				playerService.doChangeStatus(memberId, "findEnemyRate", 20, 0);
				playerService.doChangeStatus(memberId, "findItemRate", 20, 0);
				break;
			case 5:
				playerService.doChangeStatus(memberId, "increseDefencePoint", 5, 0);
				playerService.doChangeStatus(memberId, "increseMissRate", 10, 0);
				playerService.doChangeStatus(memberId, "findEnemyRate", 10, 0);
				break;
			case 6:
				playerService.doChangeStatus(memberId, "increseMissRate", 20, 0);
				playerService.doChangeStatus(memberId, "findEnemyRate", 30, 0);
				break;
			}
			playerService.changeActionType(memberId, type);
		}
		
		return playerService.getActionTypeNameById(type);
	}
	
	@RequestMapping("/usr/player/battlePhaseAttack")
	@ResponseBody
	public  ResultData battlePhaseAttack(int playerId1, int playerId2, int skillId) {
		
		Player player1 = playerService.getPlayerById(playerId1);
		Player player2 = playerService.getPlayerById(playerId2);
//		List<Inventory> player1Inventory = inventoryService.getInventoryUsefulItemCodeByPlayerId(playerId1);
		List<Inventory> player2Inventory = inventoryService.getInventoryUsefulItemCodeByPlayerId(playerId2);
		Skill skill = skillService.getOneSkillById(skillId);
		Equipment weapon1 = equipmentService.getEquipmentById(equipmentService.getMaxEquipIdByPlayerId(playerId1, 2));
		Equipment weapon2 = equipmentService.getEquipmentById(equipmentService.getMinEquipIdByPlayerId(playerId1, 2));
		
		double damageCalc = 0;
		int damage = 0;
		int levelUp = 0;
		
		if (skillId == 0) {
			damageCalc = (double) ((double)player1.getAttackPoint()+(double)player1.getIncreseAttackPoint())*((100-(double)player2.getDefencePoint()-(double)player2.getIncreseDefencePoint())/100);
			damage = (int) damageCalc;
			
			if (player1.getSp() >= 20) {
				if (player2.getHp() - damage <= 0) {
					playerService.doChangeStatus(player2.getMemberId(), "hp", player2.getHp(), 1);
					playerService.doChangeStatus(player1.getMemberId(), "sp", 20, 1);
					playerService.doChangeStatus(player1.getMemberId(), "killPoint", 1, 0);
					playerService.changeDeathStatus(player2.getMemberId(), 1);
					levelUp = getExp(player1.getId(), 5);
					return ResultData.from("S-2", "데미지를 입혔습니다.", "player1", player1, damage, "player2", player2, levelUp, "player2Inventory", player2Inventory, 0);
				} else {
					playerService.doChangeStatus(player2.getMemberId(), "hp", damage, 1);
					playerService.doChangeStatus(player1.getMemberId(), "sp", 20, 1);
					return ResultData.from("S-1", "데미지를 입혔습니다.", "player1", player1, damage, "player2", player2, damage);
				}
			} else {
				return ResultData.from("F-1", "스테미나가 부족합니다.", "player1", player1, 0, "player2", player2, 0);
			}
		} else {
			damageCalc = (double) ((double)player1.getAttackPoint()+(double)player1.getIncreseAttackPoint()+(double)skill.getIncreseAttackPoint())*((100-(double)player2.getDefencePoint()-(double)player2.getIncreseDefencePoint())/100);
			damage = (int) damageCalc;
			
			switch(skillId) {
			case 1: case 2: // 총기
				if(weapon1.getItemCode() == 152 || weapon1.getItemCode() == 153 || weapon2.getItemCode() == 152 || weapon2.getItemCode() == 153) {
					if (player1.getSp() >= skill.getUseSP()) {
						playerService.doChangeStatus(player2.getMemberId(), "hp", damage, 1);
						playerService.doChangeStatus(player1.getMemberId(), "sp", 20, 1);
						return ResultData.from("S-1", "데미지를 입혔습니다.", "player1", player1, damage, "player2", player2, damage);
					} else {
						return ResultData.from("F-1", "스테미나가 부족합니다.", "player1", player1, 0, "player2", player2, 0);
					}
				} else {
					return ResultData.from("F-2", "현재 무기로는 사용할 수 없는 스킬입니다.", "player1", player1, 0, "player2", player2, 0);
				}
			case 3: case 4: // 활
				if(weapon1.getItemCode() == 150 || weapon1.getItemCode() == 151 || weapon2.getItemCode() == 150 || weapon2.getItemCode() == 151) {
					if (player1.getSp() >= skill.getUseSP()) {
						playerService.doChangeStatus(player2.getMemberId(), "hp", damage, 1);
						playerService.doChangeStatus(player1.getMemberId(), "sp", 20, 1);
						return ResultData.from("S-1", "데미지를 입혔습니다.", "player1", player1, damage, "player2", player2, damage);
					} else {
						return ResultData.from("F-1", "스테미나가 부족합니다.", "player1", player1, 0, "player2", player2, 0);
					}
				} else {
					return ResultData.from("F-2", "현재 무기로는 사용할 수 없는 스킬입니다.", "player1", player1, 0, "player2", player2, 0);
				}
			case 5: // 투척
				if((weapon1.getItemCode() >= 201 && weapon1.getItemCode() <= 250) || (weapon2.getItemCode() >= 201 && weapon2.getItemCode() <= 250)) {
					if (player1.getSp() >= skill.getUseSP()) {
						playerService.doChangeStatus(player2.getMemberId(), "hp", damage, 1);
						playerService.doChangeStatus(player1.getMemberId(), "sp", 20, 1);
						return ResultData.from("S-1", "데미지를 입혔습니다.", "player1", player1, damage, "player2", player2, damage);
					} else {
						return ResultData.from("F-1", "스테미나가 부족합니다.", "player1", player1, 0, "player2", player2, 0);
					}
				} else {
					return ResultData.from("F-2", "현재 무기로는 사용할 수 없는 스킬입니다.", "player1", player1, 0, "player2", player2, 0);
				}
			case 7: case 8: // 폭탄
				if(weapon1.getItemCode() == 208 || weapon2.getItemCode() == 208) {
					if (player1.getSp() >= skill.getUseSP()) {
						playerService.doChangeStatus(player2.getMemberId(), "hp", damage, 1);
						playerService.doChangeStatus(player1.getMemberId(), "sp", 20, 1);
						return ResultData.from("S-1", "데미지를 입혔습니다.", "player1", player1, damage, "player2", player2, damage);
					} else {
						return ResultData.from("F-1", "스테미나가 부족합니다.", "player1", player1, 0, "player2", player2, 0);
					}
				} else {
					return ResultData.from("F-2", "현재 무기로는 사용할 수 없는 스킬입니다.", "player1", player1, 0, "player2", player2, 0);
				}
			case 9: case 10: case 11: // 검, 단검
				if((weapon1.getItemCode() >= 101 && weapon1.getItemCode() <= 106) || (weapon2.getItemCode() >= 101 && weapon2.getItemCode() <= 106)
					|| weapon1.getItemCode() == 113 || weapon2.getItemCode() == 113 || weapon1.getItemCode() == 116 || weapon2.getItemCode() == 116
					|| weapon1.getItemCode() == 118 || weapon2.getItemCode() == 118) {
					if (player1.getSp() >= skill.getUseSP()) {
						playerService.doChangeStatus(player2.getMemberId(), "hp", damage, 1);
						playerService.doChangeStatus(player1.getMemberId(), "sp", 20, 1);
						return ResultData.from("S-1", "데미지를 입혔습니다.", "player1", player1, damage, "player2", player2, damage);
					} else {
						return ResultData.from("F-1", "스테미나가 부족합니다.", "player1", player1, 0, "player2", player2, 0);
					}
				} else {
					return ResultData.from("F-2", "현재 무기로는 사용할 수 없는 스킬입니다.", "player1", player1, 0, "player2", player2, 0);
				}
			case 12: case 13: case 14: // 둔기
				if(weapon1.getItemCode() == 107 || weapon2.getItemCode() == 107 || weapon1.getItemCode() == 108 || weapon2.getItemCode() == 108
					|| weapon1.getItemCode() == 111 || weapon2.getItemCode() == 111 || weapon1.getItemCode() == 112 || weapon2.getItemCode() == 112
					|| weapon1.getItemCode() == 114 || weapon2.getItemCode() == 114 || weapon1.getItemCode() == 115 || weapon2.getItemCode() == 115
					|| weapon1.getItemCode() == 117 || weapon2.getItemCode() == 117) {
					if (player1.getSp() >= skill.getUseSP()) {
						playerService.doChangeStatus(player2.getMemberId(), "hp", damage, 1);
						playerService.doChangeStatus(player1.getMemberId(), "sp", 20, 1);
						return ResultData.from("S-1", "데미지를 입혔습니다.", "player1", player1, damage, "player2", player2, damage);
					} else {
						return ResultData.from("F-1", "스테미나가 부족합니다.", "player1", player1, 0, "player2", player2, 0);
					}
				} else {
					return ResultData.from("F-2", "현재 무기로는 사용할 수 없는 스킬입니다.", "player1", player1, 0, "player2", player2, 0);
				}
			case 15: case 16: case 17: // 주먹
				if(weapon1.getItemCode() == 119 || weapon2.getItemCode() == 119) {
					if (player1.getSp() >= skill.getUseSP()) {
						playerService.doChangeStatus(player2.getMemberId(), "hp", damage, 1);
						playerService.doChangeStatus(player1.getMemberId(), "sp", 20, 1);
						return ResultData.from("S-1", "데미지를 입혔습니다.", "player1", player1, damage, "player2", player2, damage);
					} else {
						return ResultData.from("F-1", "스테미나가 부족합니다.", "player1", player1, 0, "player2", player2, 0);
					}
				} else {
					return ResultData.from("F-2", "현재 무기로는 사용할 수 없는 스킬입니다.", "player1", player1, 0, "player2", player2, 0);
				}
			default:
				if (player1.getSp() >= skill.getUseSP()) {
					playerService.doChangeStatus(player2.getMemberId(), "hp", damage, 1);
					playerService.doChangeStatus(player1.getMemberId(), "sp", 20, 1);
					return ResultData.from("S-1", "데미지를 입혔습니다.", "player1", player1, damage, "player2", player2, damage);
				} else {
					return ResultData.from("F-1", "스테미나가 부족합니다.", "player1", player1, 0, "player2", player2, 0);
				}
			}
		}
	}
	
	@RequestMapping("/usr/player/getEnemyItem")
	@ResponseBody
	public String getEnemyItem(@RequestParam(defaultValue = "") String ids) {
		int playerId = playerService.getPlayerByMemberId(rq.getLoginedMemberId()).getId();
		
		if (Util.empty(ids)) {
			return Util.jsHistoryBack("선택한 아이템이 없습니다");
		}

		List<Integer> itemIdQuanDPs = new ArrayList<>();

		for (String idStr : ids.split(",")) {
			itemIdQuanDPs.add(Integer.parseInt(idStr));
		}
		
		for (int i = 0; i < itemIdQuanDPs.size()/3; i++) {

			int itemId = itemIdQuanDPs.get(i*3);
			int quan = itemIdQuanDPs.get((i*3)+1);
			int itemDP = itemIdQuanDPs.get((i*3)+2);
		
			if (itemVOService.getItemByCode(itemId).getCategoryNum() >= 2 && itemVOService.getItemByCode(itemId).getCategoryNum() <= 7) {
				quan = -1;
				inventoryService.addItem(playerId, itemId, quan, itemDP);
			} else {
				int chkExistItem = inventoryService.checkExistItem(playerId, itemId);
				itemDP = 0;
				if(chkExistItem == 0) {
					inventoryService.addItem(playerId, itemId, quan, itemDP);
				} else {
					inventoryService.getItem(playerId, itemId, quan);
				}
			}
		}

		return Util.jsLocateReplace("battle?id="+rq.getLoginedMemberId());
	}
	
	@RequestMapping("/usr/player/getNowActionType")
	@ResponseBody
	public int getNowActionType(int memberId) {
		return playerService.getNowActionType(memberId);
	}
	
	@RequestMapping("/usr/player/useItem")
	@ResponseBody
	public Inventory useItem(int playerId, int itemId) {
		
		ItemVO item = itemVOService.getItemByCode(itemId);
		Player player = playerService.getPlayerById(playerId);
		
		if (player.getHp() + item.getRecoveryHP() >= player.getMaxHp()) {
			playerService.doChangeStatus(rq.getLoginedMemberId(), "hp", player.getHp(), 1);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "hp", player.getMaxHp(), 0);
		} else {
			playerService.doChangeStatus(rq.getLoginedMemberId(), "hp", item.getRecoveryHP(), 0); 
		}
		
		if (player.getSp() + item.getRecoverySP() >= player.getMaxSp()) {
			playerService.doChangeStatus(rq.getLoginedMemberId(), "sp", player.getSp(), 1);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "sp", player.getMaxSp(), 0);
		} else {
			playerService.doChangeStatus(rq.getLoginedMemberId(), "sp", item.getRecoverySP(), 0);
		}
		
		playerService.doChangeStatus(rq.getLoginedMemberId(), "maxHp", item.getIncreseHP(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "maxSp", item.getIncreseSP(), 0);
		
		inventoryService.useItem(playerId, itemId);
		
		Inventory inventory = inventoryService.getInventoryItemByItemId(playerId, itemId);
		
		return inventory;
	}
	
	@RequestMapping("/usr/player/deleteItem")
	@ResponseBody
	public Inventory deleteItem(int playerId, int itemId, int invenId) {
		
		ItemVO item = itemVOService.getItemByCode(itemId);
		
		if (item.getCategoryNum() >= 2 && item.getCategoryNum() <= 7) {
			inventoryService.useEquip(playerId, itemId, invenId, 0); // 장비 버림 : 0, 장착 : 1
			return inventoryService.getInventoryItemById(inventoryService.getInventoryIdByPlayerIdAndItemIdAndDel(playerId, itemId, 0));
		} else {
			inventoryService.useItem(playerId, itemId); // 아이템 갯수 깎
			return inventoryService.getInventoryItemByItemId(playerId, itemId);
		}
	}
	
	@RequestMapping("/usr/player/equipItem")
	@ResponseBody
	public List<Equipment> equipItem(int playerId, int itemId) {
		
		ItemVO item = itemVOService.getItemByCode(itemId);
		
		List<Equipment> equipItems = equipmentService.getEquipmentsByPlayerId(playerId);
		
		int equipId = 0;
		int equipDP = 0;
		int oldEquipItemCode = 0;
		int oldEquipDP = 0;
		int invenId = 0;
		
		if (item.getCategoryNum() == 2) {
			if (equipItems.get(0).getUsedItemCode() == 994){
				equipId = equipItems.get(0).getId();
				equipDP = equipItems.get(0).getDurabilityPoint();
			} else if (equipItems.get(1).getUsedItemCode() == 994){
				equipId = equipItems.get(1).getId();
				equipDP = equipItems.get(1).getDurabilityPoint();
			} else if (equipItems.get(0).getUsedItemCode() != 994 && equipItems.get(0).getUsedItemCode() != itemId) {
				equipId = equipItems.get(0).getId();
				equipDP = equipItems.get(0).getDurabilityPoint();
				oldEquipItemCode = equipItems.get(0).getUsedItemCode();
				oldEquipDP = equipItems.get(0).getUsedItemDP();
			} else if (equipItems.get(1).getUsedItemCode() != 994) {
				equipId = equipItems.get(1).getId();
				equipDP = equipItems.get(1).getDurabilityPoint();
				oldEquipItemCode = equipItems.get(1).getUsedItemCode();
				oldEquipDP = equipItems.get(1).getUsedItemDP();
			} 
			
			if (item.getUseHand() == 2) {
				equipId = 0;
			}
		}
		
		// 아이템에 따른 능력치 변경
		playerService.doChangeStatus(rq.getLoginedMemberId(), "maxHp", item.getIncreseHP(), 0); 
		playerService.doChangeStatus(rq.getLoginedMemberId(), "maxHp", item.getDecreseHP(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "maxSp", item.getIncreseSP(), 0); 
		playerService.doChangeStatus(rq.getLoginedMemberId(), "maxSp", item.getDecreseSP(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseAttackPoint", item.getIncreseAttackPoint(), 0); 
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseAttackPoint", item.getDecreseAttackPoint(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseDefencePoint", item.getIncreseDefencePoint(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseDefencePoint", item.getDecreseDefencePoint(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseHitRate", item.getIncreseHitRate(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseHitRate", item.getDecreseHitRate(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseMissRate", item.getIncreseMissRate(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseMissRate", item.getDecreseMissRate(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "findEnemyRate", item.getIncreseFindEnemyRate(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "findEnemyRate", item.getDecreseFindEnemyRate(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "findItemRate", item.getIncreseFindItemRate(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "findItemRate", item.getDecreseFindItemRate(), 1);
		
		if (item.getCategoryNum() >= 2 && item.getCategoryNum() <= 7) {
			invenId = inventoryService.getInventoryIdByPlayerIdAndItemIdAndDel(playerId, itemId, 1);
			inventoryService.useEquip(playerId, itemId, invenId, 1); // 아이템 갯수 깎
			
			if (item.getCategoryNum() !=2) {
				int eId = equipmentService.getMinEquipIdByPlayerId(playerId, item.getCategoryNum());
				Equipment equip = equipmentService.getEquipmentById(eId);
				oldEquipItemCode = equip.getUsedItemCode();
				oldEquipDP = equip.getUsedItemDP();
			}
			
			inventoryService.changeEquipItem(playerId, oldEquipItemCode, oldEquipDP); // 빠지는 장비 인벤토리에 받기
			playerService.doChangeStatus(rq.getLoginedMemberId(), "maxHp", itemVOService.getItemByCode(oldEquipItemCode).getIncreseHP(), 1); 
			playerService.doChangeStatus(rq.getLoginedMemberId(), "maxHp", itemVOService.getItemByCode(oldEquipItemCode).getDecreseHP(), 0);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "maxSp", itemVOService.getItemByCode(oldEquipItemCode).getIncreseSP(), 1); 
			playerService.doChangeStatus(rq.getLoginedMemberId(), "maxSp", itemVOService.getItemByCode(oldEquipItemCode).getDecreseSP(), 0);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "increseAttackPoint", itemVOService.getItemByCode(oldEquipItemCode).getIncreseAttackPoint(), 1);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "increseAttackPoint", itemVOService.getItemByCode(oldEquipItemCode).getDecreseAttackPoint(), 0);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "increseDefencePoint", itemVOService.getItemByCode(oldEquipItemCode).getIncreseDefencePoint(), 1);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "increseDefencePoint", itemVOService.getItemByCode(oldEquipItemCode).getDecreseDefencePoint(), 0);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "increseHitRate", itemVOService.getItemByCode(oldEquipItemCode).getIncreseHitRate(), 1);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "increseHitRate", itemVOService.getItemByCode(oldEquipItemCode).getDecreseHitRate(), 0);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "increseMissRate", itemVOService.getItemByCode(oldEquipItemCode).getIncreseMissRate(), 1);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "increseMissRate", itemVOService.getItemByCode(oldEquipItemCode).getDecreseMissRate(), 0);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "findEnemyRate", itemVOService.getItemByCode(oldEquipItemCode).getIncreseFindEnemyRate(), 1);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "findEnemyRate", itemVOService.getItemByCode(oldEquipItemCode).getDecreseFindEnemyRate(), 0);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "findItemRate", itemVOService.getItemByCode(oldEquipItemCode).getIncreseFindItemRate(), 1);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "findItemRate", itemVOService.getItemByCode(oldEquipItemCode).getDecreseFindItemRate(), 0);
		} 
		
		equipmentService.equipItem(playerId, itemId, item.getCategoryNum(), equipId); // DB가서 장비아이템 코드 변경(실제 장착)
		
		equipItems = equipmentService.getEquipmentsByPlayerId(playerId);
		
		return equipItems;
	}
	
	@RequestMapping("/usr/player/equipOff")
	@ResponseBody
	public List<Equipment> equipOff(int playerId, int itemId, int equipId) {
		
		ItemVO item = itemVOService.getItemByCode(itemId);
		
		List<Equipment> equipItems = equipmentService.getEquipmentsByPlayerId(playerId);
		
		int quan = -1;
		int itemDP = -1;
		
		if (item.getUseHand() == 2) {
			equipId = 0;
		}
		playerService.doChangeStatus(rq.getLoginedMemberId(), "maxHp", item.getIncreseHP(), 1); 
		playerService.doChangeStatus(rq.getLoginedMemberId(), "maxHp", item.getDecreseHP(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "maxSp", item.getIncreseSP(), 1); 
		playerService.doChangeStatus(rq.getLoginedMemberId(), "maxSp", item.getDecreseSP(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseAttackPoint", item.getIncreseAttackPoint(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseAttackPoint", item.getDecreseAttackPoint(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseDefencePoint", item.getIncreseDefencePoint(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseDefencePoint", item.getDecreseDefencePoint(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseHitRate", item.getIncreseHitRate(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseHitRate", item.getDecreseHitRate(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseMissRate", item.getIncreseMissRate(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "increseMissRate", item.getDecreseMissRate(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "findEnemyRate", item.getIncreseFindEnemyRate(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "findEnemyRate", item.getDecreseFindEnemyRate(), 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "findItemRate", item.getIncreseFindItemRate(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "findItemRate", item.getDecreseFindItemRate(), 0);
		
		int chkExistItem = inventoryService.checkExistItem(playerId, itemId);
		
		if (item.getCategoryNum() >= 2 && item.getCategoryNum() <= 7) {
			inventoryService.reviveEquip(playerId, itemId, inventoryService.getInventoryIdByPlayerIdAndItemIdAndDel(playerId, itemId, 0));
		} else {
			if (chkExistItem == 1) {
				inventoryService.getItem(playerId, itemId, quan);
			}else {
				inventoryService.addItem(playerId, itemId, quan, itemDP);
			}
		}
		
		equipmentService.equipOff(playerId, item.getCategoryNum(), equipId);
		
		equipItems = equipmentService.getEquipmentsByPlayerId(playerId);
		
		return equipItems;
	}
	
	@RequestMapping("/usr/player/getPlayerByMemberId")
	@ResponseBody
	public Player getPlayerByMemberId(int memberId) {
		
		return playerService.getPlayerByMemberId(memberId);
	}
	
	@RequestMapping("/usr/player/getInventoryByPlayerId")
	@ResponseBody
	public List<Inventory> getInventoryByPlayerId(int playerId) {
		
		return inventoryService.getInventoryByPlayerId(playerId);
	}
	
	@RequestMapping("/usr/player/getRecipeByPlayerId")
	@ResponseBody
	public List<ItemVO> getRecipeByPlayerId(int playerId) {
		
		List<Inventory> inventory = inventoryService.getInventoryUsefulItemCodeByPlayerId(playerId);
		List<ItemVO> itemRecipes = itemVOService.getItemListRecipe();
		List<ItemVO> possibleMixItems = new ArrayList<ItemVO>();
		
		for (int i = 0; i < itemRecipes.size(); i++ ) {
			for (int j = 0; j < inventory.size(); j++) {
				if (itemRecipes.get(i).getRecipeItem1() == inventory.get(j).getItemId()) {
					for (int k = 0; k < inventory.size(); k++) {
						if (itemRecipes.get(i).getRecipeItem2() == inventory.get(k).getItemId()) {
							if (itemRecipes.get(i).getRecipeItem3() == 0) {
								possibleMixItems.add(itemRecipes.get(i));
							} else {
								for (int l = 0; l < inventory.size(); l++) {
									if (itemRecipes.get(i).getRecipeItem3() == inventory.get(k).getItemId()) {
										possibleMixItems.add(itemRecipes.get(i));
									}
								}
							}
						}
					}
				}
			}
		}
		
		return possibleMixItems;
	}
	
	@RequestMapping("/usr/player/learnSkill")
	@ResponseBody
	public ResultData<Skill> learnSkill(int playerId, int skillId) {
		
		Player player = playerService.getPlayerById(playerId);
		Skill skill = skillService.getOneSkillById(skillId);
		
		
		if (player.getSkillPoint() >= skill.getNeedSkillPoint()) {
			playerService.doChangeStatus(rq.getLoginedMemberId(), "skillPoint", skill.getNeedSkillPoint(), 1);
			skillService.learnSkill(playerId, skillId);
			return ResultData.from("S-1", "스킬을 배우는데 성공하였습니다.", "skillList", skillService.getOneSkillById(skillId));
		} else {
			return ResultData.from("F-1", "스킬포인트가 부족합니다.", "skillList", skillService.getOneSkillById(skillId));
		}
	}
	
	@RequestMapping("/usr/player/getSkillByPlayerId")
	@ResponseBody
	public List<Skill> getSkillByPlayerId(int playerId) {
		
		return skillService.getSkillByPlayerId(playerId);
	}
	
	@RequestMapping("/usr/player/getSkillListByLv")
	@ResponseBody
	public List<Skill> getSkillListByLv(int memberId, int playerId, int level) {
		
		return skillService.getSkillListByPIdAndLv(playerId, level);
	}
	
	@RequestMapping("/usr/player/heal")
	@ResponseBody
	public Player healHP(int memberId, int healSec, int type) {
		
		Player player = playerService.getPlayerByMemberId(memberId);
		
		if (type == 0) {
			if (player.getHp() + healSec >= player.getMaxHp()) {
				playerService.doChangeStatus(rq.getLoginedMemberId(), "hp", player.getHp(), 1);
				playerService.doChangeStatus(rq.getLoginedMemberId(), "hp", player.getMaxHp(), 0);
			} else {
				playerService.doChangeStatus(rq.getLoginedMemberId(), "hp", healSec, 0); 
			}
		} else if (type == 1 ) {
			if (player.getSp() + healSec >= player.getMaxSp()) {
				playerService.doChangeStatus(rq.getLoginedMemberId(), "sp", player.getSp(), 1);
				playerService.doChangeStatus(rq.getLoginedMemberId(), "sp", player.getMaxSp(), 0);
			} else {
				playerService.doChangeStatus(rq.getLoginedMemberId(), "sp", healSec, 0); 
			}
		}
		
		return player;
	}
	
	public int getExp(int playerId, int exp) {
		Player player = playerService.getPlayerById(playerId);
		
		if (player.getExp() + exp >= player.getMaxExp()) {
			doLevelUp(playerId, exp);
			return 1;
		}else {
			playerService.doChangeStatus(rq.getLoginedMemberId(), "exp", exp, 0);
			return 0;
		}
	}
	
	public void doLevelUp(int playerId, int exp) {
		Player player = playerService.getPlayerById(playerId);
		
		int expCalc = player.getExp() + exp - player.getMaxExp();
		
		playerService.doChangeStatus(rq.getLoginedMemberId(), "exp", player.getExp(), 1);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "exp", expCalc, 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "maxExp", 10, 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "level", 1, 0);
		playerService.doChangeStatus(rq.getLoginedMemberId(), "skillPoint", 1, 0);
	}
}
