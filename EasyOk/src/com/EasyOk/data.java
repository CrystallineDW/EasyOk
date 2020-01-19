package com.EasyOk;

public class data {
	
	private String dataString;
	private Exception error;
	
	public void setdata(String input)
	{
		dataString = input;
	}
	public String getdata()
	{
		return dataString;
	}
	public void seterror(Exception inputException)
	{
		error = inputException;
	}
	public Exception geterror()
	{
		return error;
	}

}
