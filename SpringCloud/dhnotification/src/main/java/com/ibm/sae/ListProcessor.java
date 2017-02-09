package com.ibm.sae;

import java.util.List;
import java.util.ArrayList;

public class ListProcessor {
	
	ArrayList<Integer> li = new ArrayList<Integer>();
	
	public static void main(String[] args) 
	{
		
		ListProcessor lp = new ListProcessor();
		lp.initializeList();
		lp.printList(lp.getList());
		lp.processList(lp.getList());
	}

	public ArrayList<Integer> getList() {
		return this.li;
	}

	public void setLi(ArrayList<Integer> li) {
		this.li = li;
	}

	public void initializeList()
	{
		li.add(1); li.add(2); li.add(3); li.add(4); li.add(5); li.add(6); li.add(8); li.add(9); li.add(10);
	}
	
	public int processList(ArrayList<Integer> currentList)
	{
		int size = currentList.size();
		int midPoint = currentList.size()/2;
		System.out.println("Size = " + size);
		System.out.println("Mid point = " + midPoint);
		System.out.println("Index at mid point  = " + currentList.indexOf(currentList.get(midPoint)));
		System.out.println("Value at mid point  = " + currentList.get(midPoint));
		
		
		if ((currentList.get(midPoint) -1) == currentList.indexOf(currentList.get(midPoint)))
		{
			// Process the list to the right
			System.out.println("go right");
			getNewList(currentList, currentList.indexOf(currentList.get(midPoint)));
		}
		else
		{
			// Process the list to the left
			getNewList(currentList, 0);
			System.out.println("go left");
		}
		return 3;
	}
		
	public List<Integer> getNewList(ArrayList<Integer> previousList, int position)
	{
		ArrayList<Integer> newList = new ArrayList<>();
		//for 
		
		return previousList;
	}
	public void printList(ArrayList<Integer> pList)
	{
		for (Integer theList: pList)
		{
			System.out.println(theList);
		}
	}
	
}


















