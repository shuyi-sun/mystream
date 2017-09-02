import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by bryan on 28/08/17.
 */
public class CardinalityEstimator {
    private String filePath;
    private ArrayList<Distinct> counters = new ArrayList<>();

    public static void main(String[] args){
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
        estimator.addCounter(dc);
        estimator.addCounter(bjkst3);
        estimator.test();

    }

    public CardinalityEstimator(String filePath){
        this.filePath = filePath;
    }

    public void addCounter(Distinct counter){
        counters.add(counter);
    }

    private void test(){
        if (counters.size() == 0){
            System.out.println("Please add counters first");
            System.exit(-2);
        }
        Scanner sc = null;
        try{
            sc = new Scanner(new FileInputStream(filePath));
            while (sc.hasNext()){
                int n = sc.nextInt();
                for(Distinct c : counters){
                    c.add(n);
                }
            }
            for(Distinct c : counters){
                System.out.println(c.toString() + " get result " + (long)c.distinct());
            }
        } catch (Exception e) {
            System.out.println("Some error happens");
        } finally {
            sc.close();
        }
    }
}
