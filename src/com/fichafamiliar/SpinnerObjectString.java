package com.fichafamiliar;

public class SpinnerObjectString {

	private String databaseCodigo;
	private String databaseValue;
	

	public SpinnerObjectString(String databaseCodigo, String databaseValue) {
		this.databaseCodigo = databaseCodigo;
		this.databaseValue = databaseValue;
	}
	public String getCodigo() {
		return databaseCodigo;
	}

	public String getValue() {
		return databaseValue;
	}

	@Override
	public String toString() {
		return databaseValue;
	}
}
