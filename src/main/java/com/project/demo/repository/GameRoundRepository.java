package com.project.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.demo.vo.GameRound;

@Mapper
public interface GameRoundRepository {

	@Select("""
			SELECT *
				FROM gameRound
				ORDER BY id DESC
				LIMIT 1;
			""")
	public GameRound getNowGameRound();
	
	@Insert("""
			INSERT INTO gameRound
				SET regDate = NOW()
					, updateDate = NOW()
			""")
	public void addNewGame();
	
	@Update("""
			UPDATE gameRound
				SET winMemberId = #{winMemberId}
					, winnerName = #{winnerName}
					, endStatus = 1
					, endDate = NOW()
				WHERE id = #{nowGameRoundId}
			""")
	public void updateEndGame(int nowGameRoundId, int winMemberId, String winnerName);
}
