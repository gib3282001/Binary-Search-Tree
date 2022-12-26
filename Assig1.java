import java.util.*;
import java.io.*;
public class Assig1{
    public static void main(String [] args) throws IOException{
        File file = new File("A1-out.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        ArrayList<A1Interface<Integer, Integer>> trees = new ArrayList<A1Interface<Integer, Integer>>();
        trees.add(new BST<Integer, Integer>());
		trees.add(new IterativeBST<Integer, Integer>());
		trees.add(new RedBlackBST<Integer, Integer>());
        pw.println("Testing put():");
        for(int i = 0; i < trees.size(); i++){
            pw.println(trees.get(i).treeType() + ": ");
            Random rand = new Random(5);
            double start = System.nanoTime();
            int attempts = 0;
            while(trees.get(i).size() != 2000000){
                int x = rand.nextInt(10000000) + 1;
                trees.get(i).put(x, x);
                attempts += 1;
            }
            double end = System.nanoTime();
            double total = end - start;
            pw.println("\tput() calls attempted: " + attempts);
            pw.println("\tTotal time for put() calls: " + total / 1000000000);
        }
        
        pw.println();
        for (int i = 0; i < trees.size(); i++)
		{
			pw.println("Tree Type: " + trees.get(i).treeType());
			pw.println("\tSize: " + trees.get(i).size());
			pw.println("\tHeight: " + trees.get(i).height());
			pw.println("\tAve Path Length: " + trees.get(i).avePathLength());
		}
        pw.println();
        pw.println("Testing get():");
        for(int i = 0; i < trees.size(); i++){
            pw.println(trees.get(i).treeType() + ": ");
            Random rand = new Random(4);
            int success = 0;
            int fail = 0;
            double getstart = System.nanoTime();
            for(int j = 0; j < 20000000; j++){
                int x = rand.nextInt(10000000) + 1;
                if(trees.get(i).get(x) == null){
                    fail += 1;
                }else{
                    success += 1;
                }
            }
            double getend = System.nanoTime();
            double gettotal = getend - getstart;
            pw.println("\tTotal time for get() calls: " + gettotal / 1000000000);
            pw.println("\tSuccessful searches: " + success);
            pw.println("\tUnsuccessful searches: " + fail);
        }
        pw.println();
        pw.println("Testing delete():");
        for(int i = 0; i < trees.size(); i++){
            pw.println(trees.get(i).treeType() + ": ");
            Random rand = new Random(3);
            int attempts = 0;
            int OGsize = trees.get(i).size();
            double deletestart = System.nanoTime();
            while(trees.get(i).size() != (OGsize - 500000)){
                int x = rand.nextInt(10000000) + 1;
                trees.get(i).delete(x);
                attempts += 1;
            }
            double deleteend = System.nanoTime();
            double deletetotal = deleteend - deletestart;
            pw.println("\tTotal time for delete() calls: " + deletetotal / 1000000000);
            pw.println("\tAttempts: " + attempts);
        }
        pw.println();
        for (int i = 0; i < trees.size(); i++)
		{
			pw.println("Tree Type: " + trees.get(i).treeType());
			pw.println("\tSize: " + trees.get(i).size());
			pw.println("\tHeight: " + trees.get(i).height());
			pw.println("\tAve Path Length: " + trees.get(i).avePathLength());
		}
        pw.println();
        pw.println("Adding 500000 new items to each tree: ");
        for(int i = 0; i < trees.size(); i++){
            pw.println(trees.get(i).treeType() + ": ");
            Random rand = new Random(2);
            double start2 = System.nanoTime();
            int attempts = 0;
            while(trees.get(i).size() != 2000000){
                int x = rand.nextInt(10000000) + 1;
                trees.get(i).put(x, x);
                attempts += 1;
            }
            double end2 = System.nanoTime();
            double total2 = end2 - start2;
            pw.println("\tput() calls attempted: " + attempts);
            pw.println("\tTotal time for put() calls: " + total2 / 1000000000);
        }
        pw.println();
        for (int i = 0; i < trees.size(); i++)
		{
			pw.println("Tree Type: " + trees.get(i).treeType());
			pw.println("\tSize: " + trees.get(i).size());
			pw.println("\tHeight: " + trees.get(i).height());
			pw.println("\tAve Path Length: " + trees.get(i).avePathLength());
		}
        // System.out.println();
        // System.out.println("Deleting smallest 500000:");
        // for(int i = 0; i < trees.size(); i++){
        //     System.out.println(trees.get(i).treeType() + ": ");
        //     double deletestart2 = System.nanoTime();
        //     for(int j = 0; j < 500000; j++){
        //         int select = trees.get(i).select(j);
        //         trees.get(i).delete(select);
        //     }
        //     double deleteend2 = System.nanoTime();
        //     double deletetotal2 = deleteend2 - deletestart2;
        //     System.out.println("\tTotal time for delete() calls: " + deletetotal2 / 1000000000);
        // }
        pw.println();
        pw.println("Adding numbers 1 to 1000 from smallest to largest: ");
        for(int i = 0; i < trees.size(); i++){
            pw.println(trees.get(i).treeType() + ": ");
            double start = System.nanoTime();
            for(int j = 1; j < 1001; j++){
                trees.get(i).put(j, j);
            }
            double end = System.nanoTime();
            double total = end - start;
            pw.println("\tTotal time for put() calls: " + total / 1000000000);
        }
        pw.println();
        for (int i = 0; i < trees.size(); i++)
		{
			pw.println("Tree Type: " + trees.get(i).treeType());
			pw.println("\tSize: " + trees.get(i).size());
			pw.println("\tHeight: " + trees.get(i).height());
			pw.println("\tAve Path Length: " + trees.get(i).avePathLength());
		}
        pw.close();
    }
}