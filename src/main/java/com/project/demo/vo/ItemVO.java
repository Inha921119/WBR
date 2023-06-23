package com.project.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVO {
	private int id;
	private String regDate;
	private String updateDate;
	private int itemCode;
	private String name;
	private String category;
	private int categoryNum;
	private int rarity;
	private int useType;
	private int useHand;
	private int useHP;
	private int increseHP;
	private int decreseHP;
	private int useSP;
	private int increseSP;
	private int decreseSP;
	private int increseAttackPoint;
	private int decreseAttackPoint;
	private int increseDefencePoint;
	private int decreseDefencePoint;
	private int increseHitRate;
	private int decreseHitRate;
	private int increseMissRate;
	private int decreseMissRate;
	private int increseFindItemRate;
	private int decreseFindItemRate;
	private int increseFindEnemyRate;
	private int decreseFindEnemyRate;
	private int durabilityPoint;
	private int inventoryPoint;
	private int dropRate;
}
