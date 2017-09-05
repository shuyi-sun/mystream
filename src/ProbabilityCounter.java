/**
 * Created by bryan on 30/08/17.
 */
public class ProbabilityCounter extends Distinct {
    private long sketch = 0;

    public long R(long x){
        return ~x & (x+1);
    }

    @Override
    public void add(Object num){
        startTimer();
        sketch = sketch | R((Integer)num);
        stopTimer();
    }

    @Override
    public double distinct(){
        return (R(sketch)/.77351);
    }

    @Override
    public String toString(){
        return "Probability Counter";
    }
}
