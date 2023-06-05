package com.project.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CharacterRepository {
	
	@Insert("""
			INSERT INTO `character`
				SET regDate = NOW(),
					updateDate = NOW(),
					memberId = #{memberId},
					`name` = #{name},
					gender = #{gender}
			""")
	public void doParticipationApp(int memberId, String name, int gender);
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT *
				FROM `character`
				WHERE `name` = #{charName}
			""")
	public Character getCharacterByName(String name);
}