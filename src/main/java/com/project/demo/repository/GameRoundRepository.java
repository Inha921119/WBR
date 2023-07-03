package com.project.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
