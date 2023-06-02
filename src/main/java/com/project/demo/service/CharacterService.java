package com.project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.CharacterRepository;
import com.project.demo.vo.Member;
import com.project.demo.vo.ResultData;

@Service
public class CharacterService {
private CharacterRepository characterRepository;
	
	@Autowired
	public CharacterService(CharacterRepository characterRepository) {
		this.characterRepository = characterRepository;
	}

	public ResultData<Integer> doParticipationApp(String name, int gender) {
		// TODO Auto-generated method stub
		return null;
	}

	public Character getCharacterByCharName(String charName) {
		// TODO Auto-generated method stub
		return null;
	}
}
