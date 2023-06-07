package com.project.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
			SELECT *
				FROM equipment
				WHERE playerId = #{playerId};
			""")
	public Equipment getEquipmentById(int playerId);
}
