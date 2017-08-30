/**
 * Created by bryan on 28/08/17.
 */
public class CardinalityEstimator {
    private int m;
    private int n;

    public static void main(String[] args){
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        System.out.println("Total number of items:          " + m);
        System.out.println("Total number of distinct items: " + n);

        CardinalityEstimator estimator = new CardinalityEstimator(m, n);
        for(int j = 0 ; j < 1 ; j ++) {
            estimator.test2();
        }


    }

    public CardinalityEstimator(int m, int n){
        this.m = m;
        this.n = n;
    }

    private void test1(){
        ProbabilityCounter pc = new ProbabilityCounter();
        for (int i = 0; i < m; i++) {
            int num = StdRandom.uniform(n);
            pc.count(num);
        }
        System.out.println("Norm probability estimator" + pc.getResult());
    }

    private void test2(){
        MeanProbabilityCounter mpc = new MeanProbabilityCounter(1024);
        ProbabilityCounter pc = new ProbabilityCounter();
        for (int i = 0; i < m; i++) {
            int num = StdRandom.uniform(n);
            //System.out.println(num);
            pc.count(num);
            mpc.count(num);
        }
        System.out.println("***********Result****************");
        System.out.println("Norm probability estimator: " + pc.getResult());
        System.out.println("Mean probability estimator: " + mpc.getResult());
    }
}
