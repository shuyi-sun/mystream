/**
 * Created by bryan on 5/09/17.
 */
public class HyperLoglogCounter extends ProbabilityCounter {
    private int[] bytes;
    private Hash hash;
    private int M;

    public HyperLoglogCounter(int M){
        this.M = M;
        this.bytes = new int[M];
        this.hash = new Hash();
    }

    @Override
    public void add(Object o){
        startTimer();
        int hc = o.hashCode();
        int k = hash.h2u(hc, M);
        if(bytes[k] < r(hc)){
            bytes[k] = r(hc);
        }
        stopTimer();
    }

    @Override
    public double distinct(){
        double sum = 0.0;
        for(int b : bytes){
            sum += Math.pow(2, -1.0 - b);
        }
        return phi * M * M / sum;
    }

    @Override
    public String toString(){
        return "HyperLoglog";
    }
}
