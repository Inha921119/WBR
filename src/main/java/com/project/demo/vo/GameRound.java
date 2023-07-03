package com.project.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameRound {
	private int id;
	private String regDate;
	private String updateDate;
	private int winMemberId;
	private String winnerName;
	private int endStatus;
	private String endDate;
}
