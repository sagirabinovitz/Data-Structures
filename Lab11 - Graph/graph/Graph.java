package il.ac.telhai.ds.graph;


import java.util.*;

public class Graph<V extends Comparable<V>, E> implements IGraph<V,E>{

        private class Node {
            V val;
            double dist;
            Node next;
            Node prev;

            Node(V value,double distance){
                val =value;
                dist=distance;
            }

        }

    private TreeMap<V, LinkedList<Edge>> graph;
    private PriorityQueue<Node> pq;

    public Graph() {
        graph = new TreeMap<>();
        pq = new PriorityQueue<Node>();
    }
    public void sortPQ(){
        for(Node n : pq){

        }
    }
    private void checkContains(V u, V v) {
        if (!containsVertex(u) || !containsVertex(v)) throw new RuntimeException("checkContains failed");
    }

    @Override
    public void add(V v) {
        if (!graph.containsKey(v)) {
            graph.put(v, new LinkedList<>());
        }
    }

    @Override
    public E getEdge(V u, V v) {
        checkContains(u, v);
        for (Edge edge : graph.get(u)) {
            if (edge.vertices.contains(v)){
                return edge.label;
            }
        }
        return null;
    }

    public Edge getEdgeArc(V u, V v) {
        if (!containsVertex(u) || !containsVertex(v)) return null;
        for (Edge edge : graph.get(u)) {
            if (edge.vertices.contains(v)){
                return edge;
            }
        }
        return null;
    }

    private boolean hasNoFreeEdges(V v, E edgeLabel) {
        for (Edge edge : graph.get(v)) {
            if (edge.vertices.size() == 1) {
                edge.vertices.add(v);
                edge.label = edgeLabel;
                return false;
            }
        }
        return true;
    }

    @Override
    public E putEdge(V u, V v, E edgeLabel) {
        if (!containsVertex(u)) add(u);
        if (!containsVertex(v)) add(v);
        if (getEdge(u, v) == null) {
            Edge edge = new Edge(u, v, edgeLabel);
            if (hasNoFreeEdges(u, edgeLabel)) graph.get(u).add(edge);
            if (hasNoFreeEdges(v, edgeLabel)) graph.get(v).add(edge);
        }
        else getEdgeArc(u,v).label = edgeLabel;
        return edgeLabel;

    }

    @Override
    public boolean containsVertex(V v) {
        return graph.containsKey(v);
    }

    @Override
    public void removeVertex(V v) {
        if (!containsVertex(v)) return;
        for (Edge edge : graph.get(v)) {
            edge.vertices.remove(v);
        }
        graph.remove(v);
    }

    @Override
    public E removeEdge(V u, V v) {
        checkContains(u, v);
        E label = getEdge(u, v);
        graph.get(u).removeIf(edgeU -> edgeU.vertices.contains(v));
        graph.get(v).removeIf(edgeV -> edgeV.vertices.contains(u));
        return label;
    }

    @Override
    public double getWeight(V u, V v) {
        checkContains(u, v);
        if (getEdge(u, v) == null) return 0.0;
        try {
            return  (Double) getEdge(u, v);
        } catch (Exception ignored){}
        try {
            return ((Weighted) getEdge(u, v)).getWeight();
        } catch (Exception ignored){throw new RuntimeException();}
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V v : graph.keySet()) {
            if (!sb.isEmpty()) sb.append(",");
            sb.append(v.toString());
        }
        return sb.toString();
    }

    @Override
    public String toStringExtended() {
        double weight = 0;
        StringBuilder s = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (V v : graph.keySet()) {
            if (!s.isEmpty()) s.append("\n");
            s.append(v.toString()).append(":");
            for (Edge edge : graph.get(v)) {
                if (edge.vertices.size()==2) {
                    if (!sb.isEmpty()) sb.append(",");
                    sb.append("{").append(edge.vertices.get(0).toString())
                            .append(",").append(edge.vertices.get(1)).append("}");
                    try { weight = getWeight(edge.vertices.get(0), edge.vertices.get(1)); }
                    catch (Exception ignore){}
                    if (weight != 0.0) sb.append("(").append(weight).append(")");
                }
            }
            s.append(sb);
            sb = new StringBuilder();
        }
        return s.toString();
    }

    @Override
    public boolean areAdjacent(V u, V v) {
        if (!graph.containsKey(u) || !graph.containsKey(v)) return false;
        for (Edge edge : graph.get(u)) {
            if (edge.vertices.contains(v)) return true;
        }
        return false;
    }

    public TreeMap<V,Double> distancesFrom(V a) {
        for( Node r : pq){
            r.compareTo(pq.peek().dist);
        }
        TreeMap<V,Double> tree = new TreeMap<V,Double>();
        pq.add(new Node(a,0));
        if(graph.isEmpty()){
            return tree;
        }
        for(V v : graph.keySet()){
            Node temp = new Node(v,Double.MAX_VALUE);
            pq.add(temp);
        }
        for(Node n : pq){
            Node temp = pq.peek();
            pq.remove(pq.peek());
            tree.put(temp.val, temp.dist);
            for(Node p : pq) {
                if (getWeight(a, p.val) < p.dist) {
                    p.dist = temp.dist + getWeight(a, p.val);
                }
                pq.remove(p);
                pq.add(p);
            }
        }
        return tree;
    }


    private class Edge {

        private ArrayList<V> vertices;
        private E label;

        public Edge(V u, V v, E label) {
            this.label = label;
            vertices = new ArrayList<>();
            vertices.add(u);
            vertices.add(v);
        }
    }
}

