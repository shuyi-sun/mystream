/**
 * Created by bryan on 30/08/17.
 */
public class MeanProbabilityCounter extends ProbabilityCounter {
    private int M;
    private long[] sketches;
    private Hash hash;

    public MeanProbabilityCounter(int M){
        this.M = M;
        this.sketches = new long[M];
        hash = new Hash();
    }

    @Override
    public void add(Object o){
        startTimer();
        int hc = o.hashCode();
        int k = hash.h2u(hc, M);
        sketches[k] = sketches[k] | R(hc);
        stopTimer();
    }

    @Override
    public double distinct(){
        int sum = 0;
        for(long l : sketches)
            sum += r(l);
        double mean = 1.0 * sum / M;
        return (M * Math.pow(2, mean) / phi);
    }

    @Override
    public String toString(){
        return "Mean probability Counter";
    }
}
