/**
 * This is base class for all the probability counters such as stochastic averaging
 * or hyperloglog counter.
 * It is a counter itself, which implement the probability counter but not so accurate
 * Algorithm proposed by Flajolet and Martin
 */
public class ProbabilityCounter extends Distinct {
    //bias correction factor
    public final double phi = 0.77351;

    private long sketch = 0;

    public ProbabilityCounter(){
        this.space = 8;
    }

    /**
     * This function will calculate the 2^r(x)
     * @param x the input hashcode value
     * @return 2^r(x)
     * */
    public long R(long x){
        return ~x & (x+1);
    }

    /**
     * This method is to calculate the number of trailing 1s
     * @param x input hashcode value
     * @return  the number of trailing 1s in binary representation
     * */
    public int r(long x) {
        return Long.bitCount(R(x)-1);
    }

    @Override
    public void count(Object o){
        //update the sketch
        sketch = sketch | R(Hash.h_basic(o, Hash.DOMAIN));
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
