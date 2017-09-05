/**
 * Created by bryan on 30/08/17.
 */
public class ProbabilityCounter extends Distinct {
    public final double phi = 0.77351;

    private long sketch = 0;

    public long R(long x){
        return ~x & (x+1);
    }

    public int r(long x) {
        return Long.bitCount(R(x)-1);
    }

    @Override
    public void add(Object o){
        startTimer();
        sketch = sketch | R(o.hashCode());
        stopTimer();
    }

    @Override
    public double distinct(){
        return (R(sketch)/phi);
    }

    @Override
    public String toString(){
        return "Probability Counter";
    }
}
