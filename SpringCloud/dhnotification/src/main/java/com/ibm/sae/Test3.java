package com.ibm.sae;

import java.util.ArrayList;
import java.util.List;

public class Test3 
{

	private List<String> myList = new ArrayList<String>();
	   
	private String boo;
	private String boo2;
	private String boo3;
	
	public static void main(String[] args) 
	{
		MyClass mc = new MyClass();
		System.out.println("Tester:main ==> Begin");
		Test3 t1 = new Test3("a", "b", "c");
		Test3 t2 = new Test3("a", "b", "c");
		if (t1.equals(t2))
		{
			System.out.println("equal");
			
		}
		else
		{
			System.out.println("Not equal");
			
		}
		System.out.println("Tester:main ==> End");
		t1.Test4();
	}
	public Test3() 
	{
		super();
	}

	public Test3(String boo, String boo2, String boo3) 
	{
		super();
		this.boo = boo;
		this.boo2 = boo2;
		this.boo3 = boo3;
	}

	public void Test4()
	{
	   myList.add("abc");
	   myList.add("def");
	
		for (String item: myList)
		{
			System.out.println(item);
		}
	}

	public String getBoo() {
		return boo;
	}
	public void setBoo(String boo) {
		this.boo = boo;
	}
	public String getBoo2() {
		return boo2;
	}
	public void setBoo2(String boo2) {
		this.boo2 = boo2;
	}
	public String getBoo3() {
		return boo3;
	}
	public void setBoo3(String boo3) {
		this.boo3 = boo3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boo == null) ? 0 : boo.hashCode());
		result = prime * result + ((boo2 == null) ? 0 : boo2.hashCode());
		result = prime * result + ((boo3 == null) ? 0 : boo3.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
	
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Test3 other = (Test3) obj;
		
		if (boo == null) {
			if (other.boo != null)
				return false;
		} else if (!boo.equals(other.boo))
			return false;
		if (boo2 == null) {
			if (other.boo2 != null)
				return false;
		} else if (!boo2.equals(other.boo2))
			return false;
		if (boo3 == null) {
			if (other.boo3 != null)
				return false;
		} else if (!boo3.equals(other.boo3))
			return false;
		return true;
	}
	
	
}
