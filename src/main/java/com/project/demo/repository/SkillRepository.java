package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.demo.vo.Skill;

@Mapper
public interface SkillRepository {
	
	
	@Select("""
			
			""")
	List<Skill> getSkillListByLv(int level);
	
}
