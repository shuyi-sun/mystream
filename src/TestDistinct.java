// TestDistinct.java
// "main class" for distinct elements counter
// Shuyi Sun for COMP90056
// Sep 2017

import java.io.FileInputStream;
import java.util.Scanner;

public class TestDistinct{
	
	public static void main(String args[]){
        if(args.length != 1){
            System.out.println("Please input only the file path");
            System.exit(-1);
        }

        // hyperloglog counter, which can get best performance, with sketch number to 1024
        HyperLoglogCounter hll = new HyperLoglogCounter(1024);

        Scanner sc;
        try{
            sc = new Scanner(new FileInputStream(args[0]));
            while (sc.hasNextLine()){
                //add every line to the counter
                hll.add(sc.nextLine());
            }

            //print out the counting result, rounding to long
            System.out.println((long)hll.distinct());

            sc.close();
        } catch (Exception e){

        }
	}
}