package android.com.GraphTask;

import java.util.Arrays;

/**
 * Created by Алексей on 26.05.2015.
 */
public class Kruskal {


    private int[] rnk;
    private int[] ftr;
    private WeightedEdge mEdge[];
    private MyQueue<WeightedEdge> UT;

    Kruskal(EdgeWeightedGraph weightedGraph){

        ftr = new int[weightedGraph.getQuantityNodes()];
        rnk = new int[weightedGraph.getQuantityNodes()];

        UT = new MyQueue<>();
        mEdge = new WeightedEdge[weightedGraph.getQuantityEdges()];

        for (int i = 0; i <weightedGraph.getQuantityNodes() ; i++) {
            ftr[i] = i;
            rnk[i] = 0;
        }

        int t = 0;
        for (WeightedEdge edge: weightedGraph.edges()){
            mEdge[t++] = edge;
        }

        Arrays.sort(mEdge);

        for (int k=0; k<weightedGraph.getQuantityEdges(); k++) {
            int i = mEdge[k].either();
            int j = mEdge[k].other(i);
            if (Find(i) != Find(j)){
                UT.push(mEdge[k]);
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

    public MyQueue<WeightedEdge> getUT() {
        return UT;
    }
}
