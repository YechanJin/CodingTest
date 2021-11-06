package Programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LongestDistNode {

    public static void main(String[] args) {

    }

    public static final int INF = 2123456789;
    List<Integer>[] e;
    int n;
    int max = 0;

    public int solution(int n, int[][] edge) {
        e = new ArrayList[n+1];
        this.n = n;

        for(int i = 0 ; i<= n; i++)
            e[i] = new ArrayList<>();

        parseEdgeArray(edge);
        int [] distance = getDist(1);

        return getFarNode(distance);
    }

    public void parseEdgeArray(int [][] edge){
        for(int i = 0 ; i < edge.length; i++){
            int v1 = edge[i][0], v2 = edge[i][1];
            e[v1].add(v2);
            e[v2].add(v1);
        }
    }

    public int [] getDist(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> x.dist > y.dist ? 1 : -1);
        int [] distance = new int[n+1];
        for(int i = 0 ; i <=n; i++)
            distance[i] = INF;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(distance[now.num] < now.dist)
                continue;
            distance[now.num] = now.dist;
            if(distance[now.num] > max)
                max = distance[now.num];
            for(Integer next : e[now.num]){
                if(distance[next] < now.dist+1) continue;
                pq.add(new Node(next, now.dist+1));
            }
        }
        return distance;
    }

    public int getFarNode(int [] dist){
        int cnt = 0;
        for(int i = 1; i < dist.length; i++){
            if(dist[i] == max)
                cnt++;
        }
        return cnt;
    }
    static class Node{
        int num, dist;
        Node(int num, int dist){
            this.num = num;
            this.dist = dist;
        }
    }
}
