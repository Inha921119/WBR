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
	private String name;
	private int attackPoint;
	private int defencePoint;
	private int hitRate;
	private int avoidanceRate;
	private int dropRate;
	private int durabilityPoint;
	private int inventoryPoint;
}
