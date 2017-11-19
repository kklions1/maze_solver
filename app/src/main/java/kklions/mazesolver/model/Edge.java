package kklions.mazesolver.model;

/**
 * Edge in the minimum spanning tree
 *
 * Created by kliok002 on 11/18/17.
 */

public class Edge implements Comparable {
    public Integer v1;
    public Integer v2;
    public int weight;

    public Edge(Integer v1, Integer v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Object o) {
        Edge otherEdge = (Edge) o;
        if (this.weight < otherEdge.weight) {
            return -1;
        }
        return 1;
    }
}
