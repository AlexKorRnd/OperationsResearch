package android.com.operationsresearch.GraphTask;




/**
 * Created by Алексей on 20.05.2015.
 */
public class BreadthFirstSearch {
    int num[];
    int ftr[];

    MyQueue<Integer> queue;

    BreadthFirstSearch(Graph graph){
        num = new int[graph.getQuantityNodes()];
        ftr = new int[graph.getQuantityNodes()];

        queue = new MyQueue<Integer>();

        for (int i = 0; i <graph.getQuantityNodes() ; i++) {
            num[i] = -1;
        }

        for (int r = 0; r <graph.getQuantityNodes() ; r++) {
            if (num[r] == -1){
                BFS(graph, r);
            }
        }
    }

    private void BFS(Graph graph, int r){
        num[r] = 0;  ftr[r] = 0;  // присвоение меток корню
        queue.enqueue(r);  // помещаем корень в очередь
        while (queue != null){
            int i = queue.peek();  // считываем из очереди вершину
            for (int j: graph.adj(i)){  // для каждой вершины из списка смежности
                if (num[j] == -1){      // если вершина не помечена
                    queue.enqueue(j);   // помещаем ее в очередь
                    ftr[j] = i;         // и помечаем вершину
                    num[j] = num[i] + 1;
                }
            }
        }
    }
}
