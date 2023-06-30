package com.project.demo.vo;

import lombok.Data;

@Data
public class ResultData<DT> {
	private String resultCode;
	private String msg;
	private String data1Name;
	private DT data1;
	private int numData1;
	private String data2Name;
	private DT data2;
	private int numData2;
	private String data3Name;
	private DT data3;
	private int numData3;
	
	public static <DT> ResultData<DT> from(String resultCode, String msg) {
		return from(resultCode, msg, null, null);
	}
	
	public static <DT> ResultData<DT> from(String resultCode, String msg, String data1Name, DT data1) {
		ResultData<DT> rd = new ResultData<>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		
		return rd;
	}
	public static <DT> ResultData<DT> from(String resultCode, String msg, String data1Name, DT data1, int numData1) {
		ResultData<DT> rd = new ResultData<>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		rd.numData1 = numData1;
		
		return rd;
	}
	public static <DT> ResultData<DT> from(String resultCode, String msg, String data1Name, DT data1, int numData1, String data2Name, DT data2, int numData2) {
		ResultData<DT> rd = new ResultData<>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		rd.numData1 = numData1;
		rd.data2Name = data2Name;
		rd.data2 = data2;
		rd.numData2 = numData2;
		
		return rd;
	}
	
	public static <DT> ResultData<DT> from(String resultCode, String msg, String data1Name, DT data1, int numData1, String data2Name, DT data2, int numData2, String data3Name, DT data3, int numData3) {
		ResultData<DT> rd = new ResultData<>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		rd.numData1 = numData1;
		rd.data2Name = data2Name;
		rd.data2 = data2;
		rd.numData2 = numData2;
		rd.data3Name = data3Name;
		rd.data3 = data3;
		rd.numData3 = numData3;
		
		return rd;
	}
	
	public boolean isSuccess() {
		return this.resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}
}