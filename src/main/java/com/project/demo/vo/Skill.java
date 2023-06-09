package com.project.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
	private int id;
	private String regDate;
	private String updateDate;
	private int playerId;
	private int activeSkillId;
	private int passiveSkillId;
}
