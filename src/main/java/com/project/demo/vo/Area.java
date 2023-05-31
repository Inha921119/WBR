package com.project.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Area {
	private int id;
	private String regDate;
	private String updateDate;
	private String name;
	private int redZoneStatus;
}
