package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.service.EquipmentService;
import com.project.demo.service.InventoryService;
import com.project.demo.service.ItemVOService;
import com.project.demo.service.MemberService;
import com.project.demo.service.PlayerService;
import com.project.demo.util.Util;
import com.project.demo.vo.Equipment;
import com.project.demo.vo.Inventory;
import com.project.demo.vo.ItemVO;
import com.project.demo.vo.Player;
import com.project.demo.vo.ResultData;
import com.project.demo.vo.Rq;

@Controller
public class UsrPlayerController {
	
	private PlayerService playerService;
	private MemberService memberService;
	private ItemVOService itemVOService;
	private EquipmentService equipmentService;
	private InventoryService inventoryService;
	private Rq rq;
	
	@Autowired
	public UsrPlayerController(PlayerService playerService, MemberService memberService, ItemVOService itemVOService, EquipmentService equipmentService, InventoryService inventoryService, Rq rq) {
		this.playerService = playerService;
		this.memberService = memberService;
		this.itemVOService = itemVOService;
		this.equipmentService = equipmentService;
		this.inventoryService = inventoryService;
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
		List<Inventory> inventory = inventoryService.getInventoryById(player.getId());
		
		model.addAttribute("equipments", equipments);
		model.addAttribute("inventory", inventory);
		
		return "usr/player/battle";
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
			playerService.doChangeStatus(rq.getLoginedMemberId(), "hp", player.getSp(), 1);
			playerService.doChangeStatus(rq.getLoginedMemberId(), "hp", player.getMaxSp(), 0);
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
			inventoryService.useEquip(playerId, itemId, invenId); // 아이템 갯수 깎
		} else {
			inventoryService.useItem(playerId, itemId); // 아이템 갯수 깎
		}
		
		return inventoryService.getInventoryItemByItemId(playerId, itemId);
	}
	
	@RequestMapping("/usr/player/equipItem")
	@ResponseBody
	public List<Equipment> equipItem(int playerId, int itemId) {
		
		ItemVO item = itemVOService.getItemByCode(itemId);
		
		List<Equipment> equipItems = equipmentService.getEquipmentById(playerId);
		
		int equipId = 0;
		int invenId = 0;
		
		if (item.getCategoryNum() == 2) {
			
			if (equipItems.get(0).getUsedItemCode() == itemId ) {
				equipId = equipItems.get(1).getId();
			} else {
				equipId = equipItems.get(0).getId();
			}
			if (equipItems.get(1).getUsedItemCode() == itemId) {
				equipId = equipItems.get(0).getId();
			}
			
			if (item.getUseHand() == 2) {
				equipId = 0;
			}
		}
		
		// 아이템에 따른 능력치 변경
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
			inventoryService.useEquip(playerId, itemId, invenId); // 아이템 갯수 깎
		} else {
			inventoryService.useItem(playerId, itemId); // 아이템 갯수 깎
		}
		
		equipmentService.equipItem(playerId, itemId, item.getCategoryNum(), equipId); // DB가서 장비아이템 코드 변경(실제 장착)
		
		equipItems = equipmentService.getEquipmentById(playerId);
		
		return equipItems;
	}
	
	@RequestMapping("/usr/player/equipOff")
	@ResponseBody
	public List<Equipment> equipOff(int playerId, int itemId, int equipId) {
		
		ItemVO item = itemVOService.getItemByCode(itemId);
		
		List<Equipment> equipItems = equipmentService.getEquipmentById(playerId);
		
		if (item.getUseHand() == 2) {
			equipId = 0;
		}
		
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
			inventoryService.addItem(playerId, itemId);
		} else {
			if (chkExistItem == 1) {
				inventoryService.getItem(playerId, itemId);
			}else {
				inventoryService.addItem(playerId, itemId);
			}
		}
		
		equipmentService.equipOff(playerId, item.getCategoryNum(), equipId);
		
		equipItems = equipmentService.getEquipmentById(playerId);
		
		return equipItems;
	}
	
	@RequestMapping("/usr/player/getPlayerByMemberId")
	@ResponseBody
	public Player getPlayerByMemberId(int memberId) {
		
		return playerService.getPlayerByMemberId(memberId);
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
}
