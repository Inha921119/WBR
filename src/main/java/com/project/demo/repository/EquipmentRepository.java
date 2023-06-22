package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.demo.vo.Equipment;

@Mapper
public interface EquipmentRepository {
	
	
	@Insert("""
			INSERT INTO equipment
				SET regDate = NOW(),
					updateDate = NOW(),
					playerId = #{id};
			""")
	public void createEquipment(int id);

	@Select("""
			SELECT e.*
				, ic.category
				, i.categoryNum
				, i.name
				, i.rarity
				, i.useType
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
				FROM equipment AS e
				INNER JOIN item AS i
				ON e.usedItemCode = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.categoryNum = ic.id
				WHERE e.playerId = #{playerId};
			""")
	public List<Equipment> getEquipmentById(int playerId);


	@Update("""
			UPDATE equipment AS e
			INNER JOIN item AS i
			ON e.usedItemCode = i.itemCode
				SET e.usedItemCode = #{itemId}
					, e.usedItemDP = i.durabilityPoint
					WHERE e.playerId = #{playerId}
					AND i.categoryNum = #{categoryNum};
			""")
	public void equipItem(int playerId, int itemId, int categoryNum);
	
	@Update("""
			UPDATE equipment AS e
			INNER JOIN item AS i
			ON e.usedItemCode = i.itemCode
				SET e.usedItemCode = (SELECT itemCode FROM item WHERE categoryNum = #{categoryNum} AND itemCode > 990)
					, e.usedItemDP = (SELECT durabilityPoint FROM item WHERE categoryNum = #{categoryNum} AND itemCode > 990)
					WHERE e.playerId = #{playerId}
					AND i.categoryNum = #{categoryNum};
			""")
	public void equipOff(int playerId, int categoryNum);
}
