// Interface for distinct elements counter
// awirth for COMP90056
// Aug 2017

public abstract class Distinct{
    private long accumulatedTime = 0;
    private long currentTime;
    int space;

	public static int zeros(int v){
			return Integer.numberOfTrailingZeros(v);
	}

	/**
     * This method will count the input object o
     * @param o input object item
     * */
	abstract void count(Object o);

	/**
     * This method take the input object o and add the
     * time spent on adding this object
     * @param o input object item
     * */
	public void add(Object o){
	    startTimer();
	    count(o);
	    stopTimer();
    }

    /**
     * Get the calculated distinct items from counter
     * @return get the distinct items calculated by this counter
     * */
	abstract double distinct();

    private void startTimer(){
        currentTime = System.nanoTime();
    }

    private void stopTimer(){
        accumulatedTime += System.nanoTime() - currentTime;
    }

    /**
     * @return get the total time consumed by this counter while counting items
     * */
    long getTime(){
        return accumulatedTime/1000000;
    }

    /**
     * @return get the space used by the counter.
     * */
    public int getSpace(){
        return space;
    }
}