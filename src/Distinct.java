// Interface for distinct elements counter
// awirth for COMP90056
// Aug 2017

public abstract class Distinct{
    private long accumulatedTime = 0;
    private long currentTime;

	public static int zeros(int v){
			return Integer.numberOfTrailingZeros(v);
	}
	
	abstract void add(Object o);

	abstract double distinct();

    void startTimer(){
        currentTime = System.nanoTime();
    }
    void stopTimer(){
        accumulatedTime += System.nanoTime() - currentTime;
    }

    //return time in millisecond
    long getTime(){
        return accumulatedTime/1000000;
    }
}