package com.project.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
			SELECT *
				FROM player
				WHERE memberId = #{memberId}
			""")
	public Player getPlayerByMemberId(int memberId);
}