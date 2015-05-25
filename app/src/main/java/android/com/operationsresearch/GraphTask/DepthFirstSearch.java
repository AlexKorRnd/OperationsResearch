package android.com.operationsresearch.GraphTask;

/**
 * Поиск в глубину
 */
public class DepthFirstSearch {
    private int num[];
    private int ftr[];
    private int tn[];
    private int tk[];

    private int time;
    private int k;

    DepthFirstSearch(Graph graph, int startNode){
        num = new int[graph.getQuantityNodes()];
        ftr = new int[graph.getQuantityNodes()];
        tn = new int[graph.getQuantityNodes()];
        tk = new int[graph.getQuantityNodes()];
        for (int i = 0; i <graph.getQuantityNodes() ; i++) {
            num[i] = -1;
            ftr[i] = -1;
        }

        time = 0;
        k = 1;

        // начинаем с той вершине, которую передали в конструкторе
        DFS(graph, startNode);

        for (int r = 0; r < graph.getQuantityNodes(); r++) {
            if (num[r] == -1){
                DFS(graph, r);
            }
        }
    }

    private void DFS(Graph graph, int i){
        time++;
        tn[i] = time;
        num[i] = k;
        k++;
        for (int j: graph.adj(i)){
            if (num[j] == -1){
                ftr[j] = i;
                DFS(graph, j);
            }
        }
        time++; tk[i] = time;
    }

    public int[] getNum() {
        return num;
    }

    public int[] getFtr() {
        return ftr;
    }

    public int[] getTn() {
        return tn;
    }

    public int[] getTk() {
        return tk;
    }
}
