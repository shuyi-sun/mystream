import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to test the performance of different counters
 */
public class CardinalityEstimator {
    //file path of the input data file
    private String filePath;

    //all the counters to be tested
    private ArrayList<Distinct> counters = new ArrayList<>();

    public static void main(String[] args){
        CardinalityEstimator estimator = new CardinalityEstimator(args[0]);

        //instantiate all the counters
        Distinct pc = new ProbabilityCounter();
        Distinct mpc = new MeanProbabilityCounter(1024);
        Distinct bjkst3 = new BJKST3(Hash.DOMAIN,1);
        Distinct dc = new DumbCounter();
        Distinct hll = new HyperLoglogCounter(1024);

        //add the counters to the estimator
        estimator.addCounter(dc);
        estimator.addCounter(pc);
        estimator.addCounter(mpc);
        estimator.addCounter(bjkst3);
        estimator.addCounter(hll);

        //start testing
        estimator.test();
    }

    public CardinalityEstimator(String filePath){
        this.filePath = filePath;
    }

    public void addCounter(Distinct counter){
        counters.add(counter);
    }

    public void test(){
        if (counters.size() == 0){
            System.out.println("Please add counters first");
            System.exit(-2);
        }
        Scanner sc = null;
        try{

            sc = new Scanner(new FileInputStream(filePath));

            while (sc.hasNextLine()){
                String s = sc.nextLine();

                //add the next object to every counter
                for(Distinct c : counters){
                    c.add(s);
                }
            }

            //print the final result of all the counters
            printResults();
        } catch (IOException e){
            System.out.println("IO error happens: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Some error happens: " + e.getMessage());
        } finally {
            try{
                sc.close();
            } catch (Exception e){

            }
        }
    }

    // Format and print the counting results including distinct items,
    // time consumption and relative accuracy
    private void printResults(){
        double accurate = 0;
        System.out.println("     Counter type             |      Distinct items     |     Time (ms)  |   Space(Bytes)  | Accuracy");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        for(Distinct c : counters){
            if(c.getClass() == DumbCounter.class) accurate = c.distinct();
            double num = c.distinct();
            double acc = Math.abs((accurate - num) / accurate);
            System.out.printf("%28s  | %20d    |   %8d     |   %10d    |  %2.2f \n" ,
                    c.toString(), (long)num, c.getTime(),c.getSpace(), acc);
        }
    }
}
