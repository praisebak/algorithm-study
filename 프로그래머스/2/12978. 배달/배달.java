import java.util.*;
import java.io.*;

class Solution {
    class Node{
        int idx;
        int weight;
        public Node(int idx,int weight){
            this.idx=idx;
            this.weight=weight;
        }
    }
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        //양방향 // K 시간 이하만 배달 주문가능
        //음식주문 받을 수 있는 마을의 개수

        //1번시작
            
        //int[]로 현재 i번 마을의 가중치 계산 Integer.MAX_VALUE;
        //int 값이 작으면 가중치 업데이트
        //마지막에 for 돌면서 asnwer 계산
            
        Queue<Node> que = new LinkedList<>();
        
        int[] weight = new int[N+1];
        Arrays.fill(weight,Integer.MAX_VALUE);
        
        List<List<Node>> nodeList = new ArrayList<>();

        for(int i=0;i<=N;i++){
            nodeList.add(new ArrayList<>());
        }
        
        for(int i=0;i<road.length;i++){
            for(int j=0;j<3;j++){
                int start = road[i][0];
                int end = road[i][1];
                int w = road[i][2];
                nodeList.get(start).add(new Node(end,w));
                nodeList.get(end).add(new Node(start,w));

            }
        }
        
        que.add(new Node(1,0));

        
        while(!que.isEmpty()){
            Node num = que.poll();


            for(Node next : nodeList.get(num.idx)) {
            
                if(num.weight + next.weight > K) continue;

                if(weight[next.idx] > num.weight + next.weight){
                    weight[next.idx] = num.weight + next.weight;
                    que.add(new Node(next.idx,weight[next.idx]));
                }
            }   
        }
        
        for(int i=1;i<=N;i++){
            if(i == 1){
                answer++;
                continue;
            } 
            if(weight[i] <= K) answer++;
        }
        

        return answer;
    }
}