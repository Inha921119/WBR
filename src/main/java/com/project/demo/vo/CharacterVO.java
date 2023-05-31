package com.project.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterVO {
	private int id;
	private String regDate;
	private String updateDate;
	private String memberId;
	private String name;
	private String sex;
	private int level;
	private int hp;
	private int sp;
	private int attackPoint;
	private int defencePoint;
	private int hitRate;
	private int avoidanceRate;
	private int findEnemyRate;
	private int findItemRate;
	private int inventoryPoint;
	private int killPoint;
	private int deathStatus;
	private String deathDate;
	private int actionType;
}
