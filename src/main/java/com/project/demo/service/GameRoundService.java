package com.project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.GameRoundRepository;
import com.project.demo.vo.GameRound;

@Service
public class GameRoundService {
	
	private GameRoundRepository gameRoundRepository;
	
	@Autowired
	public GameRoundService(GameRoundRepository gameRoundRepository) {
		this.gameRoundRepository = gameRoundRepository;
	}

	public GameRound getNowGameRound() {
		return gameRoundRepository.getNowGameRound();
	}

	public void addNewGame() {
		gameRoundRepository.addNewGame();
	}
	
	public void updateEndGame(int nowGameRoundId, int winMemberId, String winnerName) {
		gameRoundRepository.updateEndGame(nowGameRoundId, winMemberId, winnerName);
	}
}
