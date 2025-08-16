import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {

        //pq쓸거고 pq써서 첫번째값이 k라면 끝
        //그거아니면 계속 꺼내서 섞으면 된다
        PriorityQueue<Long> que = new PriorityQueue<>(); 
        for(int i=0;i<scoville.length;i++){
            que.add((long)scoville[i]);
        }

        int answer = 0;
        
        while(que.peek() < K){
            if(que.size() < 2 ) return -1;
            long min = que.poll();
            long min2 = que.poll();
            
            que.add(min + min2 * 2);
            answer++;
        }
        
        return answer;
    }
}