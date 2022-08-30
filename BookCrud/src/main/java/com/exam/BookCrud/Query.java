package com.exam.BookCrud;

public class Query {
	String value;

	@Override
	public String toString() {
		return "Query [value=" + value + "]";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
