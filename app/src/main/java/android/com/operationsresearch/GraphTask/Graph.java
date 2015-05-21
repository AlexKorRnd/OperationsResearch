package android.com.operationsresearch.GraphTask;

import java.util.Stack;


public class Graph {
    private int quantityNodes;  // кол-во вершин
    private int quantityEdges;      // кол-во ребер
    private myList<Integer>[] adj;     // списки смежных вершин

    /**
     * Создание графа с V вершинами, вначале все списки создаются пустыми
     * @throws java.lang.IllegalArgumentException если V< 0
     */

    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Кол-во вершин отрицательно!");
        quantityNodes = V;
        this.quantityEdges = 0;
        adj = (myList<Integer>[]) new myList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new myList<Integer>();
        }
    }


    /**
     * Создание нового графа как копии графа G.
     * @param G как копия графа
     */
    public Graph(Graph G) {
        this(G.getQuantityNodes());
        this.quantityEdges = G.getQuantityEdges();
        for (int v = 0; v < G.getQuantityNodes(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }

    }

    public int getQuantityNodes() {
        return quantityNodes;
    }


    public int getQuantityEdges() {
        return quantityEdges;
    }

    // throw an IndexOutOfBoundsException unless 0 <= v < V
    private void validateNode(int v) {
        if (v < 0 || v >= quantityNodes)
            throw new IndexOutOfBoundsException("Ошибка! Вершины " + v + " не существует. ");
    }

    /**
     * Adds the undirected edge v-w to the graph.
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
     */
    public void addEdge(int v, int w) {
        validateNode(v);
        validateNode(w);
        quantityEdges++;
        adj[v].add(w);
        //adj[w].add(v);
    }


    /**
     * Возвращение вершин смежных с вершиной v.
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
     */

    public Iterable<Integer> adj(int v) {
        validateNode(v);
        return adj[v];
    }

    /**
     * Возвращение степени вершины v.
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
     */
    public int degree(int v) {
        validateNode(v);
        return adj[v].size();
    }

    public boolean isConnected(int v, int w){
        validateNode(v);
        validateNode(w);
        for (int node: this.adj[v]) {
            if (node == w){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *    followed by the <em>V</em> adjacency lists
     */
    /*public String toString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }*/

}

