package com.project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.CharacterRepository;
import com.project.demo.util.Util;
import com.project.demo.vo.ResultData;

@Service
public class CharacterService {
private CharacterRepository characterRepository;
	
	@Autowired
	public CharacterService(CharacterRepository characterRepository) {
		this.characterRepository = characterRepository;
	}

	public ResultData<Integer> doParticipationApp(int memberId, String name, int gender) {
		Character existsCarecter = getCharacterByName(name);
		
		if (existsCarecter != null) {
			return ResultData.from("F-7", Util.f("이미 사용중인 이름(%s) 입니다", name));
		}
				
		characterRepository.doParticipationApp(memberId, name, gender);
		
		return ResultData.from("S-1", Util.f("%s님이 전장에 참가하였습니다.", name), "id", characterRepository.getLastInsertId());
	}

	public Character getCharacterByName(String name) {
		return characterRepository.getCharacterByName(name);
	}
}
