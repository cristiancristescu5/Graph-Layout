package com.example.demo1.Graph;

import org.graph4j.Edge;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cristescu Cristian
 * This class is used to model the graph that needs to be represented.
 */
public class GraphRepresentation {
    private final Graph<Node, Edge<Node>> graph;

    private final List<Node> nodes = new ArrayList<>();

    private final boolean isDirected;

    public GraphRepresentation(List<Node> nodes, boolean isDirected) {

        if(nodes.size()==0 || nodes == null){
            throw new IllegalStateException("The list of nodes is empty!");
        }
        for(Node n : nodes){
            this.nodes.add(n);
        }
        if (isDirected) {
            graph = GraphBuilder.empty()
                    .estimatedNumVertices(nodes.size())
                    .estimatedAvgDegree(nodes.size())
                    .buildDigraph();
            this.isDirected = true;
            for (Node node : nodes) {
                if (!isPresent(node)) {
                    graph.addVertex(node);
                }
            }
        } else {
            graph = GraphBuilder.empty()
                    .estimatedNumVertices(nodes.size())
                    .estimatedAvgDegree(nodes.size())
                    .buildGraph();
            this.isDirected=false;
            for(Node node : nodes){
                if(!isPresent(node)){
                    graph.addVertex(node);
                }
            }
        }
    }

    public void addEdge(Node node1, Node node2){
        boolean found1=false;
        boolean found2=false;
        if(node1.equals(node2)){
            throw new IllegalStateException("The two nodes must not be equal!");
        }
        for(Node node : nodes){
            if(node.equals(node1)){
                found1 = true;
            }
            if(node.equals(node2)){
                found2=true;
            }
        }
        if(!found1 && !found2){
            return;
        }
        if(existsEdge(node1,node2)){
            return;
        }
        graph.addEdge(node1, node2);
    }
    public Graph<Node, Edge<Node>> getGraph() {
        return graph;
    }
    public List<Node> getNodes() {
        return nodes;
    }

    private boolean isPresent(Node node) {
        int[] nodes = graph.vertices();
        for (int i = 0; i < nodes.length; i++) {
            if (graph.getVertexLabel(graph.vertices()[i]).equals(node)) {
                return true;
            }
        }
        return false;
    }
    private boolean existsEdge(Node node1, Node node2){
        Edge<Node>[] edges = graph.edgesOf(graph.findVertex(node1));
        if(!isDirected) {
            try {
                for (Edge<Node> edge : edges) {
                    if (node2.equals(graph.getVertexLabel(edge.target()))) {
                        return true;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else{
            try{
                boolean found = false;
                for (Edge<Node> edge : edges) {
                    if (node2.equals(graph.getVertexLabel(edge.target()))) {
                        return true;
                    }
                }
                Edge<Node>[] edges1 = graph.edgesOf(graph.findVertex(node2));
                for (Edge<Node> edge : edges) {
                    if (node1.equals(graph.getVertexLabel(edge.target()))) {
                        return true;
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The vertices of the graph:").append("\n");
        for(Node n : nodes){
            stringBuilder.append(n.toString()).append(" ");
        }
        stringBuilder.append("\n");
        stringBuilder.append("The edges of the graph:");
        stringBuilder.append("\n");
        for(Edge e : graph.edges()){
            stringBuilder.append("[").append(graph.getVertexLabel(e.source()).toString())
                    .append("-").append(graph.getVertexLabel(e.target()).toString()).append("]")
                    .append(":");
        }
        return stringBuilder.toString();
    }

    public Node getNodeByIndex(int index){
        return graph.getVertexLabel(graph.vertices()[index]);
    }
}
