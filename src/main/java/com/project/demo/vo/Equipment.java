package com.project.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
	private int id;
	private String regDate;
	private String updateDate;
	private int playerId;
	private int head;
	private int headDP;
	private int top;
	private int topDP;
	private int pants;
	private int pantsDP;
	private int arm;
	private int armDP;
	private int foot;
	private int footDP;
	private int leftHands;
	private int leftHandsDP;
	private int rightHands;
	private int rightHandsDP;
}
