package com.project.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
	private int id;
	private String regDate;
	private String updateDate;
	private int playerId;
	private int itemId;
	private int quantity;
	private int itemDP;
	private String category;
	private int categoryNum;
	private String name;
	private int rarity;
	private int increseAttackPoint;
	private int increseDefencePoint;
	private int increseHP;
	private int recoveryHP;
	private int increseSP;
	private int recoverySP;
	private int increseHitRate;
	private int increseMissRate;
	private int durabilityPoint;
	private int delStatus;
}
