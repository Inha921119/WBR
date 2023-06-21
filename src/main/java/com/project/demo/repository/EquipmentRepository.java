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
				INNER JOIN itemCategory AS ic
				ON e.usedItemCode = ic.itemCode
				INNER JOIN item AS i
				ON ic.itemCode = i.itemCode
				WHERE e.playerId = #{playerId};
			""")
	public List<Equipment> getEquipmentById(int playerId);


	@Update("""
			<script>
				UPDATE equipment AS e
				INNER JOIN item AS i
				ON e.usedItemCode = i.itemCode
				<set>
					e.usedItemCode = #{itemId}
					, e.usedItemDP = i.durabilityPoint
				</set>
					WHERE playerId = #{playerId}
					<choose>
							<when test="itemId gte 101 and itemId lte 150">
								AND usedItemCode BETWEEN 101 AND 150
								LIMIT 1;
							</when>
							<when test="itemId gte 151 and itemId lte 200">
								AND usedItemCode BETWEEN 151 AND 200;
							</when>
							<when test="itemId gte 201 and itemId lte 250">
								AND usedItemCode BETWEEN 201 AND 250;
							</when>
							<when test="itemId gte 251 and itemId lte 300">
								AND usedItemCode BETWEEN 251 AND 300;
							</when>
							<when test="itemId gte 301 and itemId lte 350">
								AND usedItemCode BETWEEN 301 AND 350;
							</when>
							<when test="itemId gte 351 and itemId lte 400">
								AND usedItemCode BETWEEN 351 AND 400;
							</when>
							<when test="itemId gte 401 and itemId lte 450">
								AND usedItemCode BETWEEN 401 AND 450;
							</when>
							<when test="itemId gte 451 and itemId lte 500">
								AND usedItemCode BETWEEN 451 AND 500;
							</when>
					</choose>
			</script>
			""")
	public void equipItem(int playerId, int itemId);
}
