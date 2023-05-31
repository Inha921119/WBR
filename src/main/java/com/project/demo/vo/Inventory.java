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
	private int characterId;
	private int itemId;
	private int quantity;
}
