import java.util.HashSet;

/**
 * This counter is a dumb counter, which uses a hash set to store
 * all the distinct items. It is used as a baseline for all other
 * counters.
 */
public class DumbCounter extends Distinct {
    private HashSet<Object> hashSet = new HashSet<>();

    @Override
    public void count(Object o){
        hashSet.add(o);
    }

    @Override
    public double distinct(){
        this.space = hashSet.size() *4;
        return hashSet.size();
    }

    @Override
    public String toString(){
        return "HashSet counter";
    }
}
