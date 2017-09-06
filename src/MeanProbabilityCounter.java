/**
 * This counter also implements the algorithm proposed by Flajolet and Martin
 * It uses stochastic averaging to improve the performance of the counter
 */
public class MeanProbabilityCounter extends ProbabilityCounter {

    //number of sketches
    private int M;
    private long[] sketches;
    private Hash hash;

    public MeanProbabilityCounter(int M){
        this.M = M;
        this.sketches = new long[M];
        hash = new Hash();
        this.space = M*8+4+4;
    }

    @Override
    public void count(Object o){
        //get the hashcode of object o within specific domain
        int hc = Hash.h_basic(o, Hash.DOMAIN);
        int k = hash.h2u(hc, M);
        sketches[k] = sketches[k] | R(hc);
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
