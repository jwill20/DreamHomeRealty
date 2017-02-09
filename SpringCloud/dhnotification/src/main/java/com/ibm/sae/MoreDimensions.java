package com.ibm.sae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MoreDimensions
{
	public static void main(String[] args)
	{
		
	   List<String>	 myList = new ArrayList<String>();
	   myList.add("Boo");
	   myList.set(0, "booboo");
	   System.out.println(myList);
	   myList.remove(0);
	   myList.contains("myItem");
	   myList.indexOf("myItem");  // -1 if it's not there
		
	   /*
	   int[] my1DArray = new int[5];		 // Creating 1d array of length 5
	   my1DArray[0] = 15;				      // Setting value in the first slot 
	   System.out.println(my1DArray);        	// This is the address in memory
	   System.out.println(my1DArray.length); 	// This is the length of the array
	   System.out.println(my1DArray[0]);     	// This is the value in the first position
	   
	   int[][] myFirst2DArray = new int[6][7];        // Creating 2d array of 6 rows and 7 columns
	   myFirst2DArray[0][0] = 809;			  // Setting a value in one of the slots
	   System.out.println(myFirst2DArray[0]);         // This is an address
	   System.out.println(myFirst2DArray.length);	  // This is the length from top to bottom - 6 - the number of rows
	   System.out.println(myFirst2DArray[0].length);  // This is the length of the first row - 7
	   System.out.println(myFirst2DArray[0][0]);	  // This is the value of the first slot of the first row
		
	   //Jagged arrays
	   //int[][] illegal1 = new int[][];		  // Illegal - have to provide at least the number of rows
	   //int[][] illegal2 = new int[][3];		  // Illegal - have to provide the number of rows first 
	   int[][] my2DArray = new int[6][];	      // Creating the top to bottom part of the array - i.e., the number of rows
	   my2DArray[0] = new int[4];			  // Creating the first row - length of 4
	   my2DArray[1] = new int[5];			  // Creating the second row - length of 5
	   my2DArray[0][0] = 12;			  // Putting a value in the first slot of the first row
	   System.out.println(my2DArray.length);	  // Checking the length from top to bottom - 6 - the number of rows
	   System.out.println(my2DArray[0].length);	  // Checking the length of the first row - 4
	   System.out.println(my2DArray[1].length);	  // Checking the length of the second row - 5
	   System.out.println(my2DArray[0][0]);		  // Checking the value of the first slot of the first row
	   */
	}

}
