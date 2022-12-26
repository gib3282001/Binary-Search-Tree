// CS 1501 Summer 2022
// Test driver program for Assignment 1
// Use this program to (mostly) verify that your classes are all working
// properly.  Your output should match the output shown in A1Test-out.txt

import java.util.*;
public class A1Test
{
	private ArrayList<A1Interface<Integer, Integer>> trees;
	
	public A1Test()
	{
		// Initialize tree array with an instance of each tree type
		trees = new ArrayList<A1Interface<Integer, Integer>>();
		trees.add(new BST<Integer, Integer>());
		trees.add(new IterativeBST<Integer, Integer>());
		trees.add(new RedBlackBST<Integer, Integer>());
		
		// Create balanced trees by adding data to maintain balance
		int [] addValues = new int[]{100, 50, 150, 20, 70, 120, 170, 10, 30, 60, 80, 110, 130, 160, 180};
		buildBalancedData(addValues);
		System.out.println("Trees created in intentionally balanced way...");
		showInfo();
		System.out.println();
		
		// Test the delete method
		int [] delValues = new int[]{100, 150, 160, 170, 20, 10};
		testDelete(delValues);
		System.out.println();
		System.out.println("After deletes...");
		showInfo();

		// Reinitialize tree array with new instances
		trees = new ArrayList<A1Interface<Integer, Integer>>();
		trees.add(new BST<Integer, Integer>());
		trees.add(new IterativeBST<Integer, Integer>());
		trees.add(new RedBlackBST<Integer, Integer>());		
	
		// Insert ordered data -- this should make the regular BSTs unbalanced
		System.out.println();
		buildOrderedData();
		System.out.println("Trees created using ordered data...");
		showInfo();
		System.out.println();
		
		delValues = new int[15];
		for (int i = 0; i < delValues.length; i++)
			delValues[i] = 10 * (i+1);
		testDelete(delValues);
		System.out.println();
		System.out.println("After deletes...");
		showInfo();
	}
	
	public void buildBalancedData(int [] values)
	{
		for (int i = 0; i < trees.size(); i++)
		{
			for (int j = 0; j < values.length; j++)
			{
				Integer x = Integer.valueOf(values[j]);
				trees.get(i).put(x, x);
			}
		}
	}
	
	public void buildOrderedData()
	{
		for (int i = 0; i < trees.size(); i++)
			for (int j = 10; j <= 150; j += 10)
				trees.get(i).put(Integer.valueOf(j), Integer.valueOf(j));
	}

	public void showInfo()
	{
		for (int i = 0; i < trees.size(); i++)
		{
			System.out.println("Tree Type: " + trees.get(i).treeType());
			System.out.println("\tSize: " + trees.get(i).size());
			System.out.println("\tHeight: " + trees.get(i).height());
			System.out.println("\tAve Path Length: " + trees.get(i).avePathLength());
			System.out.print("\tKeys: ");
			for (Integer key: trees.get(i).keys())
				System.out.print(key + " ");
			System.out.println();
			System.out.print("\tKeys by select: ");
			for (int j = 0; j < trees.get(i).size(); j++)
				System.out.print(trees.get(i).select(j) + " ");
			System.out.println("\n");
		}
	}
	
	public void testDelete(int [] values)
	{
		for (int i = 0; i < trees.size(); i++)
		{
			System.out.println("Tree Type: " + trees.get(i).treeType());
			for (int j = 0; j < values.length; j++)
			{
				Integer k = values[j];
				System.out.println("\tDeleting: " + k);
				trees.get(i).delete(k);
				System.out.print("\tKeys: ");
				for (Integer key: trees.get(i).keys())
					System.out.print(key + " ");
				System.out.println();
			}
			System.out.println();				
		}
	}		
	
	public static void main(String [] args)
	{
		new A1Test();
	}
}