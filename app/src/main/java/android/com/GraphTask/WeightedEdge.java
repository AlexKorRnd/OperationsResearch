package android.com.GraphTask;


/*
    Класс для хранения в памяти ребра взвешенного графа
 */
public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {

    private final double weight;

    public WeightedEdge(int v, int w, int weight){
        super(v, w);
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.weight = weight;
    }

    public int compareTo(WeightedEdge that) {
        if (that == null) throw  new IllegalArgumentException("Ребро не определено");
        if (this.weight < that.weight)
            return -1;
        else if (this.weight > that.weight)
            return +1;
        else return 0;
    }

    public double getWeight() {
        return weight;
    }
}
