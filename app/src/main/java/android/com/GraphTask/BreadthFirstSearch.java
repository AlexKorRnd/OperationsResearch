package android.com.GraphTask;


public class BreadthFirstSearch  {
    private int num[];
    private int ftr[];
    private int startNode;

    //protected MyQueue<String> text;
    //protected MyQueue<Integer[]> mArray;

    MyQueue<Integer> queue;

    BreadthFirstSearch(Graph graph, int startNode){
        num = new int[graph.getQuantityNodes()];
        ftr = new int[graph.getQuantityNodes()];
        this.startNode = startNode;

        queue = new MyQueue<>();

        for (int i = 0; i <graph.getQuantityNodes() ; i++) {
            num[i] = -1;
            ftr[i] = -1;
        }

        // начинаем с той вершине, которую передали в конструкторе
        BFS(graph, startNode);
        for (int r = 0; r <graph.getQuantityNodes(); r++) {
            if (num[r] == -1){
                BFS(graph, r);
            }
        }
    }

    private void BFS(Graph graph, int r){
        num[r] = 0;  ftr[r] = -1;
        queue.push(r);
        while (!queue.isEmpty()){
            int i = queue.pop();
            for (Edge edge: graph.adj(i)){
                int j = edge.either();
                if (num[j] == -1){
                    queue.push(j);
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
