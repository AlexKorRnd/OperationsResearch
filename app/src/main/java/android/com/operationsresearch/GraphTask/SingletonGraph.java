package android.com.operationsresearch.GraphTask;

/**
 * Created by Алексей on 18.05.2015.
 */
public class SingletonGraph {
    private static SingletonGraph ourInstance = new SingletonGraph();

    public static SingletonGraph getInstance() {
        return ourInstance;
    }

    private SingletonGraph() {

    }
}
