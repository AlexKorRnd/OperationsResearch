package android.com.operationsresearch.GraphTask;




/**
 * Created by ������� on 20.05.2015.
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
        num[r] = 0;  ftr[r] = 0;  // ���������� ����� �����
        queue.enqueue(r);  // �������� ������ � �������
        while (queue != null){
            int i = queue.peek();  // ��������� �� ������� �������
            for (int j: graph.adj(i)){  // ��� ������ ������� �� ������ ���������
                if (num[j] == -1){      // ���� ������� �� ��������
                    queue.enqueue(j);   // �������� �� � �������
                    ftr[j] = i;         // � �������� �������
                    num[j] = num[i] + 1;
                }
            }
        }
    }
}
