import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        //남은 일의 작업량을 기반으로 야근 지수를 최소화하기
        
        //n = 일할수 있는 시간
        
        //그냥 그리디로 Arrays.sort때린다음에 앞에서부터 뺴주면되는거아닌가
        //아 근데 무작정 다 빼는건 안되겠네
        //pq에 넣어서 다 넣은뒤에 큰것부터 1시간씩 빼는건 별론가
        
        PriorityQueue<Integer> que = new PriorityQueue<>((o1,o2) -> o2 - o1);
        for(int i=0;i<works.length;i++){
            que.add(works[i]);
        }
        
        for(int i=0;i<n;i++){
            int cur = que.poll(); 
            if(cur == 0) break;
            cur--;
            que.add(cur);
        }
        
        
        
        while(!que.isEmpty()){
            int cur = que.poll();
            answer += Math.pow(cur,2);
        }
        
        return answer;
    }
}