package com.project.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String relTypeCode;
	private int relId;
	private String writerName;
	
	private String body;
	private boolean actorCanChangeData;
	
	public String getForPrintBody() {
		return this.body.replaceAll("\n", "<br />");
	}

}