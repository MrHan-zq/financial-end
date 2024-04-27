package com.qst.financial.util.json;

import com.google.gson.GsonBuilder;

public class ResultObj {
	private int resultCode;
	private String msg;
	private Object data;
	 
	
	public ResultObj(){
		this.resultCode=100;
		this.msg = "defualt info";
	}
	
	public ResultObj(int resultCode,String msg){
		this.resultCode=resultCode;
		this.msg = msg;
	}
	
	public ResultObj(int resultCode,String msg,Object data){
		this.resultCode=resultCode;
		this.msg = msg;
		this.data = data;
	}
	
	public String toString(){
		ResultObj robj = new ResultObj(this.resultCode, this.msg, this.data);
		return new GsonBuilder().disableHtmlEscaping().serializeNulls().create().toJson(robj);
	}
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}


