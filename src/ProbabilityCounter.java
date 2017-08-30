/**
 * Created by bryan on 30/08/17.
 */
public class ProbabilityCounter {
    private long sketch = 0;

    public long R(long x){
        return ~x & (x+1);
    }

    public void count(int num){
        sketch = sketch | R(num);
    }

    public long getResult(){
        return (long)(R(sketch)/.77351);
    }
}
