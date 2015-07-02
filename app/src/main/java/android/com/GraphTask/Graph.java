package android.com.GraphTask;

import java.util.Stack;


public class Graph {
    protected int quantityNodes;  // кол-во вершин
    protected int quantityEdges;      // кол-во ребер
    protected MyList<Edge>[] adj;     // списки смежных вершин


    /**
     * Создание графа с getQuantityNodes вершинами, вначале все списки создаются пустыми
     * @throws java.lang.IllegalArgumentException если getQuantityNodes< 0
     */

    public Graph(int quantityNodes) {
        if (quantityNodes < 0) throw new IllegalArgumentException("Кол-во вершин отрицательно!");
        this.quantityNodes = quantityNodes;
        this.quantityEdges = 0;
        adj = (MyList<Edge>[]) new MyList[quantityNodes];
        for (int i = 0; i<quantityNodes; i++) {
            adj[i] = new MyList<>();
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

            Stack<Edge> reverse = new Stack<>();
            for (Edge e : G.adj[v]) {
                reverse.push(e);
            }
            for (Edge e : reverse) {
                adj[v].add(e);
            }
        }

    }

    public int getQuantityNodes() {
        return quantityNodes;
    }


    public int getQuantityEdges() {
        return quantityEdges;
    }

    // throw an IndexOutOfBoundsException unless 0 <= v < getQuantityNodes
    protected void validateNode(int v) {
        if (v < 0 || v >= getQuantityNodes())
            throw new IndexOutOfBoundsException("Ошибка! Вершины " + v + " не существует. ");
    }

    /**
     * Adds the undirected edge v-w to the graph.
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < getQuantityNodes and 0 <= w < getQuantityNodes
     */
    public void addEdge(int v, int w) {
        validateNode(v);
        validateNode(w);
        quantityEdges++;
        //addEdge(v, w);
        Edge edge = new Edge(v,w);
        adj[w].add(edge);

    }


    /**
     * Возвращение вершин смежных с вершиной v.
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < getQuantityNodes
     */

    public Iterable<Edge> adj(int v) {
        validateNode(v);
        return adj[v];
    }

    /**
     * Возвращение степени вершины v.
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < getQuantityNodes
     */
    public int degree(int v) {
        validateNode(v);
        return adj[v].size();
    }

    public boolean isConnected(int v, int w){
        validateNode(v);
        validateNode(w);
        for (Edge node: this.adj[v]) {
            if (node.other(v) == w){
                return true;
            }
        }
        return false;
    }



}

