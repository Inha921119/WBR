package com.project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.PlayerRepository;
import com.project.demo.util.Util;
import com.project.demo.vo.Player;
import com.project.demo.vo.ResultData;

@Service
public class PlayerService {
private PlayerRepository playerRepository;
	
	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public ResultData<Integer> doParticipationApp(String name, int memberId, String image, int gender) {
		Player existsplayer = getPlayerByName(name);
		
		if (existsplayer != null) {
			return ResultData.from("F-7", Util.f("이미 사용중인 이름(%s) 입니다", name));
		}
				
		playerRepository.doParticipationApp(name, memberId, image, gender);
		
		return ResultData.from("S-1", Util.f("%s님이 전장에 참가하였습니다.", name), "id", playerRepository.getLastInsertId());
	}

	public Player getPlayerByName(String name) {
		return playerRepository.getPlayerByName(name);
	}
	
	public Player getPlayerByMemberId(int memberId) {
		return playerRepository.getPlayerByMemberId(memberId);
	}

	public void moveLocation(int loginedMemberId, int location) {
		playerRepository.moveLocation(loginedMemberId, location);
	}

	public String getLocationNameById(int location) {
		return playerRepository.getLocationNameById(location);
	}
}
