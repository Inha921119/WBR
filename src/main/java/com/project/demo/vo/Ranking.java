package com.project.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {
	private int id;
	private String regDate;
	private String updateDate;
	private int winRound;
	private String name;
	private int memberId;
	private int playerId;
	private String image;
	private int gender;
	private int level;
	private int skillPoint;
	private int exp;
	private int maxExp;
	private int hp;
	private int maxHp;
	private int sp;
	private int maxSp;
	private int attackPoint;
	private int increseAttackPoint;
	private int defencePoint;
	private int increseDefencePoint;
	private int hitRate;
	private int increseHitRate;
	private int missRate;
	private int increseMissRate;
	private int findEnemyRate;
	private int findItemRate;
	private int inventoryPoint;
	private int killPoint;
	private int deathStatus;
	private String deathDate;
	private int actionType;
	private int nowLocation;
	private String lname;
}
