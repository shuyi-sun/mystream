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
    public void add(Object num){
        startTimer();
        int k = hash.h2u((Integer) num, M);
        sketches[k] = sketches[k] | R((Integer)num);
        stopTimer();
    }

    @Override
    public double distinct(){
        int sum = 0;
        for(long l : sketches)
            sum += Long.bitCount(R(l)-1);
        double mean = 1.0 * sum / M;
        return (M * Math.pow(2, mean) / 0.77351);
    }

    @Override
    public String toString(){
        return "Mean probability Counter";
    }
}
