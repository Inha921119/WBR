package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
				, i.name
				, i.rarity
				, i.useType
				, i.useHP
				, i.increseHP
				, i.decreseHP
				, i.useSP
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
				INNER JOIN itemCategory AS ic
				ON e.usedItemCode = ic.itemCode
				INNER JOIN item AS i
				ON ic.itemCode = i.itemCode
				WHERE e.playerId = #{playerId};
			""")
	public List<Equipment> getEquipmentById(int playerId);
}
