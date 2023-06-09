package com.project.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassiveSkill {
	private int id;
	private String regDate;
	private String updateDate;
	private String name;
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
	private int useHP;
	private int increseHP;
	private int decreseHP;
	private int useSP;
	private int increseSP;
	private int decreseSP;
}
