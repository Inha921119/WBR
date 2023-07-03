package com.project.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.demo.vo.GameLog;

@Mapper
public interface GameLogRepository {

	@Select("""
			SELECT *
				FROM gameLog
				WHERE roundNum = #{nowRoundId}
				ORDER BY id DESC;
			""")
	public List<GameLog> getGameLogs(int nowRoundId);
	
	@Insert("""
			INSERT INTO gameLog
				SET regDate = NOW()
					, roundNum = #{nowRoundId}
					, logBody = #{gameLog};
			""")
	public void insertGameLog(int nowRoundId, String gameLog);
}
