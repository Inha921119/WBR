package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.demo.vo.Equipment;
import com.project.demo.vo.Inventory;
import com.project.demo.vo.Player;
import com.project.demo.vo.Skill;

@Mapper
public interface RankingRepository {
	
	@Insert("""
			INSERT INTO winPlayer
				(
				id
				, regDate
				, updateDate
				, winRound
				, `name`
				, memberId
				, image
				, gender
				, `level`
				, skillPoint
				, `exp`
				, maxExp
				, hp
				, maxHp
				, sp
				, maxSp
				, attackPoint
				, increseAttackPoint
				, defencePoint
				, increseDefencePoint
				, hitRate
				, increseHitRate
				, missRate
				, increseMissRate
				, findEnemyRate
				, findItemRate
				, inventoryPoint
				, killPoint
				, deathStatus
				, deathDate
				, actionType
				, nowLocation
				)
			SELECT id
				, regDate
				, updateDate
				, #{winRound}
				, `name`
				, memberId
				, image
				, gender
				, `level`
				, skillPoint
				, `exp`
				, maxExp
				, hp
				, maxHp
				, sp
				, maxSp
				, attackPoint
				, increseAttackPoint
				, defencePoint
				, increseDefencePoint
				, hitRate
				, increseHitRate
				, missRate
				, increseMissRate
				, findEnemyRate
				, findItemRate
				, inventoryPoint
				, killPoint
				, deathStatus
				, deathDate
				, actionType
				, nowLocation
				FROM player
				WHERE id = #{playerId}
			""")
	public void registWinPlayer(int playerId, int winRound);
	
	@Insert("""
			INSERT INTO winnerEquipment
				(
				id
				, regDate
				, updateDate
				, winRound
				, playerId
				, usedItemCode
				, usedItemDP
				)
			SELECT id
				, regDate
				, updateDate
				, #{winRound}
				, playerId
				, usedItemCode
				, usedItemDP
				FROM equipment
				WHERE playerId = #{playerId}
			""")
	public void registWinnerEquipment(int playerId, int winRound);
	
	@Insert("""
			INSERT INTO winnerInventory
				(
				id
				, regDate
				, updateDate
				, winRound
				, playerId
				, itemId
				, quantity
				, itemDP
				, delStatus
				)
			SELECT id
				, regDate
				, updateDate
				, #{winRound}
				, playerId
				, itemId
				, quantity
				, itemDP
				, delStatus
				FROM inventory
				WHERE playerId = #{playerId}
			""")
	public void registWinnerInventory(int playerId, int winRound);
	
	@Insert("""
			INSERT INTO winnerSkill
				(
				id
				, regDate
				, updateDate
				, winRound
				, playerId
				, skillId
				)
			SELECT id
				, regDate
				, updateDate
				, #{winRound}
				, playerId
				, skillId
				FROM skill
				WHERE playerId = #{playerId}
			""")
	public void registWinnerSkill(int playerId, int winRound);
	
	@Select("""
			SELECT *
				FROM winPlayer
				ORDER BY Nid DESC;
			""")
	public List<Player> getWinPlayers();
	
	@Select("""
			SELECT e.*
				, ic.category
				, i.categoryNum
				, i.name
				, i.rarity
				, i.useType
				, i.useHand
				, i.useHP
				, i.recoveryHP
				, i.increseHP
				, i.decreseHP
				, i.useSP
				, i.recoverySP
				, i.increseSP
				, i.decreseSP
				, i.increseAttackPoint
				, i.decreseAttackPoint
				, i.increseDefencePoint
				, i.decreseDefencePoint
				, i.increseHitRate
				, i.decreseHitRate
				, i.increseMissRate
				, i.decreseMissRate
				, i.increseFindItemRate
				, i.decreseFindItemRate
				, i.increseFindEnemyRate
				, i.decreseFindEnemyRate
				, i.durabilityPoint
				, i.inventoryPoint
				, i.dropRate
				, i.recipeItem1
				, i.recipeItem2
				, i.recipeItem3
				FROM winnerEquipment AS e
				INNER JOIN item AS i
				ON e.usedItemCode = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.categoryNum = ic.id
			""")
	public List<Equipment> getWinnerEquipments();
	
	@Select("""
			SELECT iv.*
				, ic.category
				, i.categoryNum
				, i.name
				, i.rarity
				, i.useType
				, i.useHand
				, i.useHP
				, i.recoveryHP
				, i.increseHP
				, i.decreseHP
				, i.useSP
				, i.recoverySP
				, i.increseSP
				, i.decreseSP
				, i.increseAttackPoint
				, i.decreseAttackPoint
				, i.increseDefencePoint
				, i.decreseDefencePoint
				, i.increseHitRate
				, i.decreseHitRate
				, i.increseMissRate
				, i.decreseMissRate
				, i.increseFindItemRate
				, i.decreseFindItemRate
				, i.increseFindEnemyRate
				, i.decreseFindEnemyRate
				, i.durabilityPoint
				, i.inventoryPoint
				, i.dropRate
				, i.recipeItem1
				, i.recipeItem2
				, i.recipeItem3
				FROM winnerInventory AS iv
				INNER JOIN item AS i
				ON iv.itemId = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.categoryNum = ic.id
				WHERE iv.quantity != 0;
			""")
	public List<Inventory> getWinnerInventorys();
	
	@Select("""
			SELECT s.*
				, sl.name
				, sl.skillType
				, sl.needSkillPoint
				, sl.needLearnLevel
				, sl.weaponType
				, sl.attackPoint
				, sl.defencePoint
				, sl.useHP
				, sl.increseHP
				, sl.decreseHP
				, sl.useSP
				, sl.increseSP
				, sl.decreseSP
				, sl.increseAttackPoint
				, sl.decreseAttackPoint
				, sl.increseDefencePoint
				, sl.decreseDefencePoint
				, sl.increseHitRate
				, sl.decreseHitRate
				, sl.increseMissRate
				, sl.decreseMissRate
				, sl.increseFindItemRate
				, sl.decreseFindItemRate
				, sl.increseFindEnemyRate
				, sl.decreseFindEnemyRate
				FROM winnerSkill AS s
				INNER JOIN skillList AS sl
				ON s.skillId = sl.id;
			""")
	public List<Skill> getWinnerSkills();
}
