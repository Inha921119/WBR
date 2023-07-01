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
			<script>
			UPDATE inventory
				<set>
					<if test="quan == 0">
						quantity = 0
					</if>
					<if test="quan == 1">
						quantity = quantity - #{quan}
					</if>
				</set>
				WHERE playerId = #{playerId}
				AND itemId = #{itemId};
			</script>
			""")
	public void useItem(int playerId, int itemId, int quan); // all : quan = 0, 1개 : quan = 1
	
	@Update("""
			<script>
				UPDATE inventory
					<set>
						<if test="del == 0">
							delStatus = 0
							, quantity = 0
						</if>
						<if test="del == 1"> 
							delStatus = 1
							, quantity = 0
						</if>
					</set>
					WHERE playerId = #{playerId}
					AND itemId = #{itemId}
					<if test="invenId != 0">
						AND id = #{invenId}
					</if>
					LIMIT 1;
			</script>
			""")
	public void useEquip(int playerId, int itemId, int invenId, int del); // del 1 : 장착, del 0 : 버림
	
	@Update("""
			<script>
				UPDATE inventory
					<set>
						delStatus = 1
						, quantity = 1
					</set>
					WHERE playerId = #{playerId}
					AND itemId = #{oldEquipItemCode}
					AND itemDP = #{oldEquipDP}
					AND quantity = 0
					LIMIT 1;
			</script>
			""")
	public void changeEquipItem(int playerId, int oldEquipItemCode, int oldEquipDP);
	
	@Update("""
			<script>
				UPDATE inventory
					<set>
						quantity = 1
					</set>
						WHERE playerId = #{playerId}
						AND itemId = #{itemId}
						<if test="invenId != 0">
							AND id = #{invenId}
						</if>
						LIMIT 1;
			</script>
			""")
	public void reviveEquip(int playerId, int itemId, int invenId);
	
	@Select("""
			SELECT EXISTS(
					SELECT * 
						FROM inventory 
						WHERE playerId = #{playerId} 
						AND itemId = #{itemId}
					);
			""")
	int checkExistItem(int playerId, int itemId); // 없 : 0, 있 : 1

	@Insert("""
			<script>
			INSERT INTO inventory
				<set>
					regDate = NOW()
					, updateDate = NOW()
					, playerId = #{playerId}
					, itemId = #{itemId}
					<if test="quantity == -1">
						, quantity = 1
					</if>
					<if test="quantity != -1">
						, quantity = #{quantity}
					</if>
					<if test="itemDP == -1">
						, itemDP = 999;
					</if>
					<if test="itemDP != -1">
						, itemDP = #{itemDP};
					</if>
				</set>
			</script>
			""")
	public void addItem(int playerId, int itemId, int quantity, int itemDP);

	@Update("""
			<script>
			UPDATE inventory
				<set>
					<if test="addQuan == -1">
						quantity = quantity + 1
					</if>
					<if test="addQuan != -1">
						quantity = quantity + #{addQuan}
					</if>
				</set>
					WHERE playerId = #{playerId}
					AND itemId = #{itemId};
			</script>
			""")
	public void getItem(int playerId, int itemId, int addQuan);
	
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
	
	@Select("""
			<script>
				SELECT id
					FROM inventory
					WHERE itemId = #{itemId}
					AND playerId = #{playerId}
					<if test="delStatus == 0">
						AND quantity = 0
						AND delStatus = 0
					</if>
					<if test="delStatus == 1">
						AND quantity = 0
						AND delStatus = 1
					</if>
					<if test="delStatus == 2">
						AND quantity = 1
						AND delStatus = 1
					</if>
					LIMIT 1;
			</script>
			""")
	public int getInventoryIdByPlayerIdAndItemIdAndDel(int playerId, int itemId, int delStatus);  // 0 버린거,  1 장착중인거, 2 인벤토리 존재하는거
}
