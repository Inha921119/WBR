package com.project.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.SkillRepository;
import com.project.demo.vo.Skill;

@Service
public class SkillService {
	private SkillRepository skillRepository;
	
	@Autowired
	public SkillService(SkillRepository skillRepository) {
		this.skillRepository = skillRepository;
	}

	public List<Skill> getSkillListByLv(int level) {
		return skillRepository.getSkillListByLv(level);
	}
	
}
