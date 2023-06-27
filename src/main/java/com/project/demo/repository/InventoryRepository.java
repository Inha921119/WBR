package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
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
				FROM inventory AS iv
				INNER JOIN item AS i
				ON iv.itemId = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.categoryNum = ic.id
				WHERE iv.playerId = #{playerId};
			""")
	List<Inventory> getInventoryByPlayerId(int playerId);

	@Select("""
			SELECT iv.*
				, ic.category
				, i.categoryNum
				, i.name
				, i.rarity
				, i.useType
				, i.usehand
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
				FROM inventory AS iv
				INNER JOIN item AS i
				ON iv.itemId = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.categoryNum = ic.id
				WHERE iv.playerId = #{playerId}
				AND iv.itemId = #{itemId};
			""")
	Inventory getInventoryItemByItemId(int playerId, int itemId);
	
	@Select("""
			SELECT iv.*
				, ic.category
				, i.categoryNum
				, i.name
				, i.rarity
				, i.useType
				, i.usehand
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
				FROM inventory AS iv
				INNER JOIN item AS i
				ON iv.itemId = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.categoryNum = ic.id
				WHERE iv.id = #{invenId};
			""")
	Inventory getInventoryItemById(int invenId);

	@Update("""
			UPDATE inventory
				SET quantity = quantity - 1
				WHERE playerId = #{playerId}
				AND itemId = #{itemId};
			""")
	public void useItem(int playerId, int itemId);
	
	@Delete("""
			<script>
				DELETE FROM inventory
					WHERE playerId = #{playerId}
					AND itemId = #{itemId}
					<if test="invenId != 0">
						AND id = #{invenId}
					</if>
					LIMIT 1;
			</script>
			""")
	public void useEquip(int playerId, int itemId, int invenId);
	
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
				FROM inventory AS iv
				INNER JOIN item AS i
				ON iv.itemId = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.categoryNum = ic.id
				WHERE iv.playerId = #{playerId}
				AND iv.quantity != 0;
			""")
	List<Inventory> getInventoryUsefulItemCodeByPlayerId(int playerId);
	
}
