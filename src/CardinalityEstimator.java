import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by bryan on 28/08/17.
 */
public class CardinalityEstimator {
    private String filePath;
    private ArrayList<Distinct> counters = new ArrayList<>();

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
            while (sc.hasNext()){
                int n = sc.nextInt();
                for(Distinct c : counters){
                    c.add(n);
                }
            }
            System.out.println("     Counter type             |      Distinct items     |     Time (ms)  ");
            for(Distinct c : counters){
                System.out.printf("%28s  | %20d    | %8d \n" , c.toString(), (long)c.distinct(), c.getTime());
                //System.out.println(c.toString() + "     " + (long)c.distinct() + "       " + c.getTime() + " ms");
            }
        } catch (Exception e) {
            System.out.println("Some error happens");
        } finally {
            sc.close();
        }
    }
}
