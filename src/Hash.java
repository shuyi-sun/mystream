// awirth for COMP90056
// Aug 2017

import java.io.FileInputStream;
import java.util.Scanner;

public class Hash{
	private int p = 1073741789; //smaller than 2^30
	private int a,b;		// only use for hash tables < 24593 in size

    //default hash domain
    public static final int DOMAIN = 0x7fffffff;
	
	public Hash(){
		a= StdRandom.uniform(p-1)+1;
		b= StdRandom.uniform(p+1);
		//System.out.format("a %16d b %12d p %12d %n", a,b,p);
	}
	public int h2u(int x,int range){
		long prod = (long)a*(long)x;
		prod += (long)b;
		long y = prod % (long) p;
		int r = (int) y % range;
		//System.out.format("x %12d y %12d r %12d %n", x,y,r);
		return r;
	}
	
	public static int h_basic(Object key,int domain){
		// domain should be something like 0x0fffffff
        return (key.hashCode() & domain);
	}

	/*
	* This main function is to test if the h2u method can
	* really uniformly hashed the input value
	* */
	public static void main(String args[]){
		Hash h = new Hash();
		int M = 1024;
        int[] counts = new int[M];
		try{
            Scanner sc = new Scanner(new FileInputStream(args[0]));
            while (sc.hasNextLine()){
                String x = sc.nextLine();
                int k = h.h2u(h_basic(x, DOMAIN), M);
                counts[k] ++;
            }
        } catch (Exception e){

        }
        for (int i = 0 ;i < M ; i ++){
		    System.out.println(counts[i]);
        }
	}
}