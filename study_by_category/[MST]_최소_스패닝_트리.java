//https://www.acmicpc.net/problem/1197

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }

    private static class Solve {

        private int N;
        private int M;
        private List<Node> nodeList;

        public void solve() throws IOException{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String[] sArr = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(sArr[0]);
            M = Integer.parseInt(sArr[1]);
            nodeList = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                sArr = bufferedReader.readLine().split(" ");
                int a = Integer.parseInt(sArr[0]);
                int b = Integer.parseInt(sArr[1]);
                int w = Integer.parseInt(sArr[2]);
                nodeList.add(new Node(a,b,w));
                nodeList.add(new Node(b,a,w));
            }
            parent = new int[N+1];
            Collections.sort(nodeList);
            mst();

        }

        int[] parent;

        private void mst() {
            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            int sum = 0;
            for(Node node : nodeList){
                int a = node.start;
                int b = node.next;
                if(find(a) != find(b)){
                    connect(node.start,node.next);
                    sum += node.weight;
                }
            }
            System.out.println(sum);
        }

        private int find(int b) {
            if(parent[b] == b){
                return b;
            }
            return find(parent[b]);
        }

        private void connect(int start, int next) {
            int a = find(start);
            int b = find(next);
            if(a == b){
                return;
            }

            if(a < b){
                parent[a] = b;
            }else{
                parent[b] = a;
            }
        }

        private class Node implements Comparable<Node>{
            int start;
            int next;
            int weight;
            public Node(int start, int next, int weight) {
                this.start = start;
                this.next = next;
                this.weight = weight;
            }

            @Override
            public int compareTo(Node o) {
                return this.weight - o.weight;
            }
        }
    }
}

/**
 *
 * 우선 플로이드 (for3중)은 mst 구하기에 적합하지 않다.
 * 그리고 시간복잡도를 보면 for 3중으로는 풀수없는 문제임을 알수있다.
 * 그렇다면 내가 알고있는 알고리즘중 프림이 정답임을 알 수 있다.
 */

class Solve2 {

    private long[][] graph;
    private long[][] weight = new long[10001][10001];

    private int N;
    private int M;
    private long MAX = (Integer.MAX_VALUE) + 1l;


    class Node{
        int next;
        int weight;

        public Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        graph = new long[N +1][N +1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(weight[i], MAX);
        }

        for (int i = 0; i < M; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(sArr[0]);
            int b = Integer.parseInt(sArr[1]);
            int w = Integer.parseInt(sArr[2]);
            weight[a][b] = Math.min(weight[a][b],w);
            weight[b][a] = Math.min(weight[b][a],w);
        }

        mst();
        System.out.println(weight[1][N]);
    }

    private void mst() {
        for (int i = 1; i <= N; i++) { // 경유 노드
            for (int j = 1; j <= N; j++) { // 시작 노드
                for (int k = 1; k <= N; k++) { // 도착 노드
                    if (weight[j][k] > weight[j][i] + weight[i][k]) {
                        weight[j][k] = weight[j][i] + weight[i][k];
                    }
                }
            }
        }
    }
}
