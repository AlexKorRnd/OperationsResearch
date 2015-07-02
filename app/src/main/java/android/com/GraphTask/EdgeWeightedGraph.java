package android.com.GraphTask;


import java.util.Stack;


public class EdgeWeightedGraph extends Graph {
        protected MyList<WeightedEdge>[] adj;

    public EdgeWeightedGraph(int quantityNode) {
        super(quantityNode);

        adj = (MyList<WeightedEdge>[]) new MyList[quantityNode];

        for (int v = 0; v < quantityNode; v++) {
            adj[v] = new MyList<WeightedEdge>();
        }
    }


    public EdgeWeightedGraph(EdgeWeightedGraph G) {
        this(G.getQuantityNodes());
        super.quantityEdges = G.getQuantityEdges();
        for (int v = 0; v < G.getQuantityNodes(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<WeightedEdge> reverse = new Stack<WeightedEdge>();
            for (WeightedEdge e : G.adj[v]) {
                reverse.push(e);
            }
            for (WeightedEdge e : reverse) {
                adj[v].add(e);
            }
        }
    }

    public void addEdge(WeightedEdge e) {
        int v = e.either();
        int w = e.other(v);
        super.validateNode(v);
        super.validateNode(w);
        adj[v].add(e);
        //adj[w].add(e);
        quantityEdges++;
    }

    public void addEdge(int v, int w, int weight){
        validateNode(v);
        validateNode(w);
        WeightedEdge e = new WeightedEdge(v, w, weight);
        adj[v].add(e);
    }

    public Iterable<WeightedEdge> adjWeighted(int v) {
        validateNode(v);
        return this.adj[v];
    }


    public int degree(int v) {
        validateNode(v);
        return adj[v].size();
    }

    /**
     * Returns all edges in the edge-weighted graph.
     * To iterate over the edges in the edge-weighted graph, use foreach notation:
     * <tt>for (Edge e : G.edges())</tt>.
     * @return all edges in the edge-weighted graph as an Iterable.
     */
    public Iterable<WeightedEdge> edges() {
        MyList<WeightedEdge> list = new MyList<>();
        for (int v = 0; v < quantityNodes; v++) {
            for (WeightedEdge e : adjWeighted(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
            }
        }
        return list;
    }

}
