package com.example.demo.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
    public static int V, E, K;
    public static int distance[];
    public static boolean visited[];
    public static ArrayList<Edge> list[];
    public static PriorityQueue<Edge> q = new PriorityQueue<Edge>();

    static class Edge implements Comparable<Edge> {
        int vertex, value;
        Edge(int vertex, int value) {
            this.vertex = vertex;
            this.value = value;
        }
        @Override
        public int compareTo(Edge e) {
            if (this.value > e.value) return 1;
            else return -1;
        }
    }


    /*
    *
10 16
1
1 2 7
1 3 2
1 4 3
2 5 5
2 8 3
3 2 4
4 6 1
4 7 6
5 6 3
6 7 1
6 8 4
6 9 6
8 9 5
7 10 9
8 10 8
9 10 7
    *
    * */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        distance = new int[V + 1];
        visited = new boolean[V + 1];
        list = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++)
            list[i] = new ArrayList<Edge>();
        for (int i = 0; i <= V; i++)
            distance[i] = Integer.MAX_VALUE;
        for (int i = 0; i < E; i++) {   // 가중치가 있는 인접 리스트 초기화
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Edge(v, w));
        }
        q.add(new Edge(K, 0));      // K를 시작점으로 설정하기
        distance[K] = 0;

        while (!q.isEmpty()) {
            Edge current = q.poll();
            int c_v = current.vertex;
            if (visited[c_v]) continue;     // 이미 방문한 적이 있는 노드는 다시 큐에 넣지 않음.
            visited[c_v] = true;
            System.out.println("c_v = " + c_v);
            for (int i = 0; i < list[c_v].size(); i++) {
                Edge tmp = list[c_v].get(i);
                int next = tmp.vertex;
                int value = tmp.value;
                if (distance[next] > distance[c_v] + value) {   // 최소 거리로 업데이트
                    distance[next] = value + distance[c_v];
                    q.add(new Edge(next, distance[next]));
                    System.out.println("add queue next[" + next + "] = " + distance[next]);
                }
                System.out.println("distance[" + c_v + "] = " + distance[c_v]);
                System.out.println("distance[" + c_v + " + value] = " + (int)(value + distance[c_v]));
                System.out.println("next distance[" + next + "] = " + distance[next]);
            }
        }

        for (int i = 1; i <= V; i++) {
            if (visited[i])
                System.out.println(distance[i]);
            else
                System.out.println("INF");
        }

    }

}

