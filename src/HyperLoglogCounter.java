/**
 * This counter is hyperloglog counter, implementing
 * Flajolet-Fusy-Gandouet-Meunier algorithm.
 * It uses M log log N bits space and can reach
 * relative accuracy of 1.02 / sqrt(M).
 */
public class HyperLoglogCounter extends ProbabilityCounter {
    private byte[] bytes;
    private Hash hash;
    private int M;

    public HyperLoglogCounter(int M){
        this.M = M;
        this.bytes = new byte[M];
        this.hash = new Hash();
        this.space = M + 4 + 4;
    }

    @Override
    public void count(Object o){
        //hash the input object to the specific domain
        int hc = Hash.h_basic(o, Hash.DOMAIN);
        int k = hash.h2u(hc, M);
        if(bytes[k] < r(hc)){
            bytes[k] = (byte) r(hc);
        }
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
        return "Hyper log log";
    }
}
