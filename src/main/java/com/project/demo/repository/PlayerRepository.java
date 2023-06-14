package com.project.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.demo.vo.Player;
import com.project.demo.vo.ResultData;

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
				, p.exp
				, p.maxExp
				, p.hp
				, p.maxHp
				, p.sp
				, p.maxSp
				, p.attackPoint
				, p.defencePoint
				, p.hitRate
				, p.missRate
				, p.findEnemyRate
				, p.findItemRate
				, p.inventoryPoint
				, p.killPoint
				, p.deathStatus
				, p.deathDate
				, p.actionType
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
}