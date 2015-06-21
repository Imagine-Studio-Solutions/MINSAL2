package com.fichafamiliar;

public class SpinnerObject {

	private int databaseId;
	private String databaseValue;
	private String databaseValue2;
	private String databaseValue3;
	

	public SpinnerObject(int databaseId, String databaseValue ) {
		this.databaseId = databaseId;
		this.databaseValue = databaseValue;
	}
	public SpinnerObject(int databaseId, String databaseValue , String databaseValue2,String databaseValue3) {
		this.databaseId = databaseId;
		this.databaseValue = databaseValue;
		this.databaseValue2 = databaseValue2;
		this.databaseValue3 = databaseValue3;
	}
	public int getId() {
		return databaseId;
	}

	public String getValue() {
		return databaseValue;
	}
	
	public String getValue2() {
		return databaseValue2;
	}
	
	public String getValue3() {
		return databaseValue3;
	}

	@Override
	public String toString() {
		return databaseValue;
	}
}