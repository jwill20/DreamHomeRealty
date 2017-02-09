package com.ibm.sae;

import java.util.List;
import java.util.ArrayList;

public final class MyClass implements IMyInterface{

	List<String> myList = new ArrayList<String>();
	private int value = 1;
	
	public static void main(String[] args) 
	{
		MyClass mc = new MyClass();
		mc.Test1();
		mc.Results();
	}
	
	@Override
	public void doThings() {
		System.out.println("bump");
	}


	@Override
	public boolean equals(Object o)
	{
		if (o == null) return false;
		if (this == o) return true;
		if (o instanceof MyClass)
		{
			MyClass mc = (MyClass)o;
			if (this.value == mc.value)
			{
				return true;
			}
		}
		return true;
	}
	
	public final void Test1()
	{
		myList.add("aaa");
		myList.add("bbb");
		System.out.println(myList.size());
	}
	public void Results()
	{
		//for (String ms: myList)
		for (int a = 0; a < myList.size(); a++)	
		{	
			System.out.println(myList.get(a));
			
			switch(value){
			case 1:
				System.out.println("aaa");
				break;
			case 2:
				System.out.println("bbb");
				break;
			default:
				System.out.println("defaults");
				break;
			}
		}	
	}
}
