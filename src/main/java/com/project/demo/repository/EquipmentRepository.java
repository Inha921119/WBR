package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.demo.vo.Equipment;

@Mapper
public interface EquipmentRepository {
	
	@Delete("""
			TRUNCATE TABLE equipment;
			""")
	public void resetEquipmentData();
	
	@Insert("""
			INSERT INTO equipment
				SET regDate = NOW(),
					updateDate = NOW(),
					playerId = #{id},
					usedItemCode = #{itemCode};
			""")
	public void createEquipment(int id, int itemCode);

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
				FROM equipment AS e
				INNER JOIN item AS i
				ON e.usedItemCode = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.categoryNum = ic.id
				WHERE e.playerId = #{playerId};
			""")
	public List<Equipment> getEquipmentsByPlayerId(int playerId);
	
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
				FROM equipment AS e
				INNER JOIN item AS i
				ON e.usedItemCode = i.itemCode
				INNER JOIN itemCategory AS ic
				ON i.categoryNum = ic.id
				WHERE e.id = #{equipId};
			""")
	public Equipment getEquipmentById(int equipId);


	@Update("""
			<script>
				UPDATE equipment AS e
				INNER JOIN item AS i
				ON e.usedItemCode = i.itemCode
					<set>
						e.usedItemCode = #{itemId}
						, e.usedItemDP = (SELECT durabilityPoint FROM item WHERE itemCode = #{itemId})
					</set>
						WHERE e.playerId = #{playerId}
						AND i.categoryNum = #{categoryNum}
						<if test="equipId != 0">
							AND e.id = #{equipId};
						</if>
			</script>
			""")
	public void equipItem(int playerId, int itemId, int categoryNum, int equipId);
	
	@Update("""
			<script>
				UPDATE equipment AS e
				INNER JOIN item AS i
				ON e.usedItemCode = i.itemCode
					<set>
						e.usedItemCode = (SELECT itemCode FROM item WHERE categoryNum = #{categoryNum} AND itemCode > 990)
						, e.usedItemDP = (SELECT durabilityPoint FROM item WHERE categoryNum = #{categoryNum} AND itemCode > 990)
					</set>
						WHERE e.playerId = #{playerId}
						AND i.categoryNum = #{categoryNum}
						<if test="equipId != 0">
								AND e.id = #{equipId};
						</if>
			</script>
			""")
	public void equipOff(int playerId, int categoryNum, int equipId);
	
	@Select("""
			SELECT MAX(e.id)
				FROM equipment AS e
				INNER JOIN item AS i
				ON e.usedItemCode = i.itemCode
				WHERE e.playerId = #{playerId}
				AND i.categoryNum = #{categoryNum};
			""")
	public int getMaxEquipIdByPlayerId(int playerId, int categoryNum);
	
	@Select("""
			SELECT MIN(e.id)
				FROM equipment AS e
				INNER JOIN item AS i
				ON e.usedItemCode = i.itemCode
				WHERE e.playerId = #{playerId}
				AND i.categoryNum = #{categoryNum};
			""")
	public int getMinEquipIdByPlayerId(int playerId, int categoryNum);

	@Select("""
			SELECT *
				FROM equipment
				WHERE playerId = #{playerId}
				AND usedItemCode = #{itemId};
			""")
	public Equipment getEquipByPIdAndItemId(int playerId, int itemId);
}
