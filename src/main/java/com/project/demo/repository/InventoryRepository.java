package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.demo.vo.Inventory;

@Mapper
public interface InventoryRepository {
	
	@Select("""
			SELECT iv.*
				, ic.category
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
				FROM inventory AS iv
				INNER JOIN item AS i
				ON iv.itemId = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.itemCode = ic.itemCode
				WHERE iv.playerId = #{playerId};
			""")
	List<Inventory> getInventoryById(int playerId);

}
