package com.project.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.demo.vo.Player;

@Mapper
public interface PlayerRepository {
	
	@Insert("""
			INSERT INTO player
				SET regDate = NOW(),
					updateDate = NOW(),
					`name` = #{name},
					memberId = #{memberId},
					image = #{image},
					gender = #{gender}
			""")
	public void doParticipationApp(String name, int memberId, String image, int gender);
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT *
				FROM player
				WHERE `name` = #{name}
			""")
	public Player getPlayerByName(String name);
	
	@Select("""
			SELECT p.id
				, p.regDate
				, p.updateDate
				, p.`name`
				, p.memberId
				, p.image
				, p.gender
				, p.level
				, p.skillPoint
				, p.exp
				, p.maxExp
				, p.hp
				, p.maxHp
				, p.sp
				, p.maxSp
				, p.attackPoint
				, p.increseAttackPoint
				, p.defencePoint
				, p.increseDefencePoint
				, p.hitRate
				, p.increseHitRate
				, p.missRate
				, p.increseMissRate
				, p.findEnemyRate
				, p.findItemRate
				, p.inventoryPoint
				, p.killPoint
				, p.deathStatus
				, p.deathDate
				, p.actionType
				, p.nowLocation
				, l.lname
				FROM player AS p
				INNER JOIN location AS l
				ON p.nowlocation = l.id
				WHERE memberId = #{memberId};
			""")
	public Player getPlayerByMemberId(int memberId);

	
	@Update("""
			UPDATE player
				SET nowLocation = #{location}
				WHERE memberId = #{loginedMemberId};
			""")
	public void moveLocation(int loginedMemberId, int location);

	@Select("""
			SELECT lname
				FROM location
				WHERE id = #{location};
			""")
	public String getLocationNameById(int location);
	
	@Update("""
			UPDATE player
				SET actionType = #{type}
				WHERE memberId = #{loginedMemberId};
			""")
	public void changeActionType(int loginedMemberId, int type);
	
	@Select("""
			SELECT TypeName
				FROM actionType
				WHERE id = #{type};
			""")
	public String getActionTypeNameById(int type);
	
	@Select("""
			SELECT actionType
				FROM player
				WHERE memberId = #{loginedMemberId};
			""")
	public int getNowActionType(int loginedMemberId);

	@Update("""
			<script>
			UPDATE player
				<set>
					<if test="oe == 0">
						<choose>
							<when test="statName == 'level'">
								level = level + #{num}
							</when>
							<when test="statName == 'skillPoint'">
								skillPoint = skillPoint + #{num}
							</when>
							<when test="statName == 'exp'">
								exp = exp + #{num}
							</when>
							<when test="statName == 'maxExp'">
								maxExp = maxExp + #{num}
							</when>
							<when test="statName == 'hp'">
								hp = hp + #{num}
							</when>
							<when test="statName == 'maxHp'">
								maxHp = maxHp + #{num}
							</when>
							<when test="statName == 'sp'">
								sp = sp + #{num}
							</when>
							<when test="statName == 'maxSp'">
								maxSp = maxSp + #{num}
							</when>
							<when test="statName == 'attackPoint'">
								attackPoint = attackPoint + #{num}
							</when>
							<when test="statName == 'increseAttackPoint'">
								increseAttackPoint = increseAttackPoint + #{num}
							</when>
							<when test="statName == 'defencePoint'">
								defencePoint = defencePoint + #{num}
							</when>
							<when test="statName == 'increseDefencePoint'">
								increseDefencePoint = increseDefencePoint + #{num}
							</when>
							<when test="statName == 'hitRate'">
								hitRate = hitRate + #{num}
							</when>
							<when test="statName == 'increseHitRate'">
								increseHitRate = increseHitRate + #{num}
							</when>
							<when test="statName == 'missRate'">
								missRate = missRate + #{num}
							</when>
							<when test="statName == 'increseMissRate'">
								increseMissRate = increseMissRate + #{num}
							</when>
							<when test="statName == 'findEnemyRate'">
								findEnemyRate = findEnemyRate + #{num}
							</when>
							<when test="statName == 'findItemRate'">
								findItemRate = findItemRate + #{num}
							</when>
							<when test="statName == 'inventoryPoint'">
								inventoryPoint = inventoryPoint + #{num}
							</when>
							<when test="statName == 'killPoint'">
								killPoint = killPoint + #{num}
							</when>
						</choose>
					</if>
					<if test="oe == 1">
						<choose>
							<when test="statName == 'level'">
								level = level - #{num}
							</when>
							<when test="statName == 'skillPoint'">
								skillPoint = skillPoint - #{num}
							</when>
							<when test="statName == 'exp'">
								exp = exp - #{num}
							</when>
							<when test="statName == 'maxExp'">
								maxExp = maxExp - #{num}
							</when>
							<when test="statName == 'hp'">
								hp = hp - #{num}
							</when>
							<when test="statName == 'maxHp'">
								maxHp = maxHp - #{num}
							</when>
							<when test="statName == 'sp'">
								sp = sp - #{num}
							</when>
							<when test="statName == 'maxSp'">
								maxSp = maxSp - #{num}
							</when>
							<when test="statName == 'attackPoint'">
								attackPoint = attackPoint - #{num}
							</when>
							<when test="statName == 'increseAttackPoint'">
								increseAttackPoint = increseAttackPoint - #{num}
							</when>
							<when test="statName == 'defencePoint'">
								defencePoint = defencePoint - #{num}
							</when>
							<when test="statName == 'increseDefencePoint'">
								increseDefencePoint = increseDefencePoint - #{num}
							</when>
							<when test="statName == 'hitRate'">
								hitRate = hitRate - #{num}
							</when>
							<when test="statName == 'increseHitRate'">
								increseHitRate = increseHitRate - #{num}
							</when>
							<when test="statName == 'missRate'">
								missRate = missRate - #{num}
							</when>
							<when test="statName == 'increseMissRate'">
								increseMissRate = increseMissRate - #{num}
							</when>
							<when test="statName == 'findEnemyRate'">
								findEnemyRate = findEnemyRate - #{num}
							</when>
							<when test="statName == 'findItemRate'">
								findItemRate = findItemRate - #{num}
							</when>
							<when test="statName == 'inventoryPoint'">
								inventoryPoint = inventoryPoint - #{num}
							</when>
							<when test="statName == 'killPoint'">
								killPoint = killPoint - #{num}
							</when>
						</choose>
					</if>
				</set>
				WHERE memberId = #{memberId};
			</script>
			""")
	public void doChangeStatus(int memberId, String statName, int num, int oe);
	
	@Select("""
			SELECT *
				FROM player
				WHERE id = #{playerId};
			""")
	public Player getPlayerById(int playerId);
	
	@Select("""
			SELECT p.id
				, p.regDate
				, p.updateDate
				, p.`name`
				, p.memberId
				, p.image
				, p.gender
				, p.level
				, p.skillPoint
				, p.exp
				, p.maxExp
				, p.hp
				, p.maxHp
				, p.sp
				, p.maxSp
				, p.attackPoint
				, p.increseAttackPoint
				, p.defencePoint
				, p.increseDefencePoint
				, p.hitRate
				, p.increseHitRate
				, p.missRate
				, p.increseMissRate
				, p.findEnemyRate
				, p.findItemRate
				, p.inventoryPoint
				, p.killPoint
				, p.deathStatus
				, p.deathDate
				, p.actionType
				, p.nowLocation
				, l.lname
				FROM player AS p
				INNER JOIN location AS l
				ON p.nowlocation = l.id
				WHERE p.nowLocation = #{nowLocation}
				AND p.id != #{id}
				ORDER BY RAND()
				LIMIT 1;
			""")
	public Player getPlayerByLocation(int nowLocation, int id);
}