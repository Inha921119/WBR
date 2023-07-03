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
				ORDER BY regDate DESC;
			""")
	public List<GameLog> getGameLogs();
	
	@Insert("""
			INSERT INTO gameLog
				SET regDate = NOW;
					, logBody = #{gameLog};
			""")
	public void insertGameLog(String gameLog);
}
