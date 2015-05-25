package android.com.operationsresearch.GraphTask;


public class BreadthFirstSearch {
    int num[];
    int ftr[];

    MyQueue<Integer> queue;

    BreadthFirstSearch(Graph graph, int startNode){
        num = new int[graph.getQuantityNodes()];
        ftr = new int[graph.getQuantityNodes()];

        queue = new MyQueue<Integer>();

        for (int i = 0; i <graph.getQuantityNodes() ; i++) {
            num[i] = -1;
        }

        // начинаем с той вершине, которую передали в конструкторе
        BFS(graph, startNode);
        for (int r = 0; r <graph.getQuantityNodes() ; r++) {
            if (num[r] == -1){
                BFS(graph, r);
            }
        }
    }

    private void BFS(Graph graph, int r){
        num[r] = 0;  ftr[r] = 0;
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
