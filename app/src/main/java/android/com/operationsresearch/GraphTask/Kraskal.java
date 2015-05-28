package android.com.operationsresearch.GraphTask;

import java.util.Arrays;

/**
 * Created by Алексей on 26.05.2015.
 */
public class Kraskal {


    private int[] rnk;
    private int[] ftr;
    private Edge mEdge[];
    private MyQueue<Edge> UT;

    Kraskal(EdgeWeightedGraph weightedGraph){

        ftr = new int[weightedGraph.V()];
        rnk = new int[weightedGraph.V()];

        UT = new MyQueue<>();
        mEdge = new Edge[weightedGraph.E()];

        for (int i = 0; i <weightedGraph.V() ; i++) {
            ftr[i] = i;
            rnk[i] = 0;
        }

        int t = 0;
        for (Edge edge: weightedGraph.edges()){
            mEdge[t++] = edge;
        }

        Arrays.sort(mEdge);

        for (int k=0; k<weightedGraph.E(); k++) {
            int i = mEdge[k].either();
            int j = mEdge[k].other(i);
            //int FIND_i = Find(i);
            //int FIND_j = Find(j);
            if (Find(i) != Find(j)){
                UT.enqueue(mEdge[k]);
                Union(Find(i), Find(j));
            }
        }


    }

    int Find(int i){
        if (i != ftr[i]){
            ftr[i] = Find(ftr[i]);
        }
        return ftr[i];
    }

    void Union(int r, int s){
        if (rnk[r] >= rnk[s]) {
            ftr[s] = r;
            if (rnk[r] == rnk[s]) {
                rnk[r] = rnk[r] + 1;
            }
        }
        else{
            ftr[r] = s;
        }
    }


    public int[] getRnk() {
        return rnk;
    }

    public int[] getFtr() {
        return ftr;
    }

    public MyQueue<Edge> getUT() {
        return UT;
    }
}
