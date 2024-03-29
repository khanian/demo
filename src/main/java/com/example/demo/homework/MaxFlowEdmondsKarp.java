package com.example.demo.homework;

import java.util.ArrayList;
import java.util.List;

public class MaxFlowEdmondsKarp {

    static class Edge {
        int s, t, rev, cap, flow;

        public Edge(int s, int t, int rev, int cap) {
            this.s = s;
            this.t = t;
            this.rev = rev;
            this.cap = cap;
        }
    }

    public static List<Edge>[] createGraph(int edges) {
        List<Edge>[] graph = new List[edges];
        for (int i = 0; i < edges; i++)
            graph[i] = new ArrayList<>();
        return graph;
    }

    public static void addEdge(List<Edge>[] graph, int s, int t, int cap) {
        graph[s].add(new Edge(s, t, graph[t].size(), cap));
        graph[t].add(new Edge(t, s, graph[s].size() - 1, 0));
    }

    public static int maxFlow(List<Edge>[] graph, int s, int t) {
        int maxFlow = 0;
        int[] q = new int[graph.length];
        while (true) {
            int qt = 0;
            q[qt++] = s;
            Edge[] pred = new Edge[graph.length];
            for (int qh = 0; qh < qt && pred[t] == null; qh++) {
                int cur = q[qh];
                for (Edge e : graph[cur]) {
                    if (pred[e.t] == null && e.cap > e.flow) {
                        pred[e.t] = e;
                        q[qt++] = e.t;
                    }
                }
            }
            if (pred[t] == null)
                break;
            int df = Integer.MAX_VALUE;
            for (int u = t; u != s; u = pred[u].s)
                df = Math.min(df, pred[u].cap - pred[u].flow);
            for (int u = t; u != s; u = pred[u].s) {
                pred[u].flow += df;
                graph[pred[u].t].get(pred[u].rev).flow -= df;
            }
            maxFlow += df;
        }
        return maxFlow;
    }

    // Usage example
    public static void main(String[] args) {
        List<Edge>[] graph = createGraph(4);
        addEdge(graph, 0, 1, 3);
        addEdge(graph, 0, 2, 2);
        addEdge(graph, 1, 2, 2);
        System.out.println("The maximum possible flow is " + maxFlow(graph, 0, 2));

        List<Edge>[] graph1 = createGraph(5);
        addEdge(graph1, 0, 1, 3);
        addEdge(graph1, 0, 2, 2);
        addEdge(graph1, 2, 1, 5);
        addEdge(graph1, 1, 3, 2);
        addEdge(graph1, 2, 3, 2);
        System.out.println("The maximum possible flow is " + maxFlow(graph1, 0, 3));
    }
}