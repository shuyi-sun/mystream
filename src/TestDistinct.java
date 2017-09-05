// TestDistinct.java
// "main class" for distinct elements counter
// Shuyi Sun for COMP90056
// Sep 2017

public class TestDistinct{
	
	public static void main(String args[]){
        if(args.length != 1){
            System.out.println("Please only input the file path");
            System.exit(-1);
        }
        CardinalityEstimator estimator = new CardinalityEstimator(args[0]);

        //add all the counters
        Distinct pc = new ProbabilityCounter();
        Distinct mpc = new MeanProbabilityCounter(1024);
        Distinct dc = new DumbCounter();
        Distinct bjkst3 = new BJKST3(0x0fffffff,1);

        estimator.addCounter(pc);
        estimator.addCounter(mpc);
        estimator.addCounter(bjkst3);
        estimator.addCounter(dc);
        estimator.test();

	}
}