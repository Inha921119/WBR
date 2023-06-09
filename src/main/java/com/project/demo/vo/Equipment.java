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
	private int usedItemCode;
	private int usedItemDP;
	private String name;
	private String category;
	private int increseHP;
	private int decreseHP;
}
