import java.util.HashSet;

/**
 * Created by bryan on 2/09/17.
 */
public class DumbCounter implements Distinct {
    private HashSet<Integer> hashSet = new HashSet<>();

    @Override
    public void add(Object o){
        hashSet.add((Integer)o);
    }

    @Override
    public double distinct(){
        return hashSet.size();
    }

    @Override
    public String toString(){
        return "HashSet counter";
    }
}
