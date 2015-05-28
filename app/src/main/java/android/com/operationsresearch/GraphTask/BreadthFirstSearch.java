package android.com.operationsresearch.GraphTask;


import android.content.Context;
import android.widget.TableLayout;

public class BreadthFirstSearch {
    private int num[];
    private int ftr[];
    private TableLayout[] mTableLayouts;

    MyQueue<Integer> queue;

    BreadthFirstSearch(Context context, Graph graph, int startNode){
        num = new int[graph.getQuantityNodes()];
        ftr = new int[graph.getQuantityNodes()];

        queue = new MyQueue<Integer>();

        for (int i = 0; i <graph.getQuantityNodes() ; i++) {
            num[i] = -1;
            ftr[i] = -1;
        }

        // начинаем с той вершине, которую передали в конструкторе
        int i = 0;
        BFS(graph, startNode);
        for (int r = 0; r <graph.getQuantityNodes(); r++) {
            if (num[r] == -1){
                BFS(graph, r);
            }
        }
    }

    private void BFS(Graph graph, int r){
        num[r] = 0;  ftr[r] = -1;
        queue.enqueue(r);
        while (!queue.isEmpty()){
            int i = queue.dequeue();
            for (int j: graph.adj(i)){
                if (num[j] == -1){
                    queue.enqueue(j);
                    ftr[j] = i;
                    num[j] = num[i] + 1;
                }
            }
        }
    }




    public int[] getNum() {
        return num;
    }

    public int[] getFtr() {
        return ftr;
    }
}
