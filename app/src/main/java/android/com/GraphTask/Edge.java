package android.com.GraphTask;


public class Edge {

    protected final int v;
    protected final int w;


    public Edge(int v, int w) {
        if (v < 0) throw new IndexOutOfBoundsException("Вершина должна быть положительным числом");
        if (w < 0) throw new IndexOutOfBoundsException("Вершина должна быть положительным числом");
        this.v = v;
        this.w = w;
    }


    public int either() {
        return v;
    }


    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Такой вершины не существует");
    }



}

