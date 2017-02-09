package com.ibm.sae;

import java.util.*;

public class TwoDimensions
{

	public static void main(String[] args)
	{
		// Create a random array
		// int [] array2 = fillRandom(10, 5);
		// printArray(array2);

		// int [] tally = tallyData(array2, 5);
		// printArray(tally);

		// int idx = indexOf(array2, 3);
		// System.out.println("Index of 3 in random array: " + idx);

		// int idx2 = nextIndexOf(array2, 3, idx+1);
		// System.out.println("Next index of 3 in random array: " + idx2);

		// replaceAll(array2, 3, 7);
		// printArray(array2);

		// reverse(array2);
		// printArray(array2);

		// int [] array3 = fillRandom(10, 5);
		// printArray(array3);
		// if (equals(array2, array3)) {
		// System.out.println("Arrays are equal");
		// } else {
		// System.out.println("Array are NOT equal");
		// }

		int[][] array4 = fill2DRandom(5, 5, 5);
		int[][] array5 = fill2DRandom(5, 5, 5);
		print2DArray(array4);
		System.out.println();
		print2DArray(array5);

		if (equals2D(array4, array5))
		{
			System.out.println("2D Arrays are equal.");
		}
		else
		{
			System.out.println("2D Arrays are NOT equal.");
		}

	}

	public static int[][] fill2DRandom(int numberRows, int numberColumns,
			int multiplier)
	{
		Random r = new Random();
		int[][] a = new int[numberRows][numberColumns];

		for (int i = 0; i < numberRows; i++) // for each row
		{
			for (int j = 0; j < numberColumns; j++) // for each column
			{
				a[i][j] = r.nextInt(multiplier);
			}
		}

		return a;
	}

	public static void print2DArray(int[][] data)
	{
		for (int i = 0; i < data.length; i++) // for each row
		{
			for (int j = 0; j < data[i].length; j++)
			{
				System.out.print(data[i][j]);
			}
			System.out.println();
		}

	}

	public static boolean equals2D(int[][] array1, int[][] array2)
	{
		if (array1.length != array2.length)
		{
			return false;
		}
		if (array1[0].length != array2[0].length)
		{
			return false;
		}
		for (int i = 1; i < array1[0].length; i++)
		{
			for (int j = 1; j < array1[0].length; j++)
			{
				if (array1[i][j] != array2[i][j])
				{
					return false;
				}
			}

		}
		return true;
	}

	public static void reverse(int[] array)
	{
		for (int i = 0; i < array.length / 2; i++)
		{
			swap(array, i, array.length - 1 - i);
		}
	}

	public static void swap(int[] array, int idx1, int idx2)
	{
		int temp = array[idx1];
		array[idx1] = array[idx2];
		array[idx2] = temp;
	}

	public static boolean equals(int[] array1, int[] array2)
	{
		if (array1.length != array2.length)
		{
			return false;
		}
		for (int i = 0; i < array1.length; i++)
		{
			if (array1[i] != array2[i])
			{
				return false;
			}
		}

		return true;
	}

	public static void replaceAll(int[] array, int val1, int val2)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == val1)
			{
				array[i] = val2;
			}
		}
	}

	public static int nextIndexOf(int[] array, int value, int startIdx)
	{
		for (int i = startIdx; i < array.length; i++)
		{
			if (array[i] == value)
			{
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(int[] array, int value)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == value)
			{
				return i;
			}
		}
		return -1;
	}

	public static int[] tallyData(int[] array, int multiplier)
	{
		int[] tally = new int[multiplier];

		for (int i = 0; i < array.length; i++)
		{
			tally[array[i]]++;
		}

		return tally;
	}

	public static int[] fillRandom(int length, int multiplier)
	{
		Random r = new Random();
		int[] a = new int[length];

		for (int i = 0; i < a.length; i++)
		{
			a[i] = r.nextInt(multiplier);
		}

		return a;
	}

	public static void printArray(int[] data)
	{
		String out = "[" + data[0];
		for (int i = 1; i < data.length; i++)
		{
			out += ", " + data[i];
		}
		out += "]";
		System.out.println(out);
	}

	public static int[] getUserArray(int length)
	{
		int[] array = new int[length];
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < array.length; i++)
		{
			System.out.print("Int? ");
			while (!in.hasNextInt())
			{
				System.out.println("Not an int.");
				System.out.print("Int? ");
			}
			array[i] = in.nextInt();
		}
		return array;
	}

}
