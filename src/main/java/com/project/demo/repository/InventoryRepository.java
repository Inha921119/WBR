package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.demo.vo.Inventory;

@Mapper
public interface InventoryRepository {
	
	@Select("""
			SELECT iv.*
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
				FROM inventory AS iv
				INNER JOIN item AS i
				ON iv.itemId = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.categoryNum = ic.id
				WHERE iv.playerId = #{playerId};
			""")
	List<Inventory> getInventoryById(int playerId);

	@Select("""
			SELECT iv.*
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
				FROM inventory AS iv
				INNER JOIN item AS i
				ON iv.itemId = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.categoryNum = ic.id
				WHERE iv.playerId = #{playerId}
				AND iv.itemId = #{itemId};
			""")
	Inventory getInventoryItemByItemId(int playerId, int itemId);

	@Update("""
			UPDATE inventory
				SET quantity = quantity - 1
				WHERE playerId = #{playerId}
				AND itemId = #{itemId};
			""")
	public void useItem(int playerId, int itemId);
	
	@Select("""
			SELECT EXISTS(
					SELECT * 
						FROM inventory 
						WHERE playerId = #{playerId} 
						AND itemId = #{itemId}
					);
			""")
	int checkExistItem(int playerId, int itemId);

	@Insert("""
			INSERT INTO inventory
				SET regDate = NOW()
					, updateDate = NOW()
					, playerId = #{playerId}
					, itemId = #{itemId}
					, quantity = 1;
			""")
	public void addItem(int playerId, int itemId);

	@Update("""
			UPDATE inventory
				SET quantity = quantity + 1
				WHERE playerId = #{playerId}
				AND itemId = #{itemId};
			""")
	public void getItem(int playerId, int itemId);
	
}
