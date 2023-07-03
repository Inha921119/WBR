package com.project.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.repository.GameLogRepository;
import com.project.demo.vo.GameLog;

@Service
public class GameLogService {
	
	private GameLogRepository gameLogRepository;
	
	@Autowired
	public GameLogService(GameLogRepository gameLogRepository) {
		this.gameLogRepository = gameLogRepository;
	}

	public List<GameLog> getGameLogs() {
		return gameLogRepository.getGameLogs();
	}

	public void insertGameLog(String gameLog) {
		gameLogRepository.insertGameLog(gameLog);
	}
}
