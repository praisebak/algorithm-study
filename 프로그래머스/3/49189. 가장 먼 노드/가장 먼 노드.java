import java.util.*;

class Solution {
    int answer = 0;
    
    class Node{
        int idx;
        int count;
        public Node(int idx,int count){
            this.idx=idx;
            this.count=count;
        }
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<=n;i++){
            list.add(new ArrayList<>());
        }
        
        for(int i=0;i<edge.length;i++){
            int left = edge[i][0];
            int right = edge[i][1];
            list.get(left).add(right);
            list.get(right).add(left);
        }
        
        PriorityQueue<Node> que = new PriorityQueue<>((o1,o2) -> o1.count - o2.count);
        que.add(new Node(1,0));

        //bfs로 한다고 하면 어떤일이 생기는가? bfs로 했을때는 노드가 20000개여서 문제가..아니 (n + e) 아닌가
        //그럼 괜찮을것같은데..
        
        //이게 그럼 visit을 고려해야하는건가? x 최단경로 고려니까 하지않아도된다
        boolean[] visit = new boolean[n+1];
        visit[1] = true;
        
        int answerCount = 0;
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(answer < cur.count){
                answer = cur.count;
                answerCount = 1;
            }else if(answer == (cur.count)){
                answerCount++;
            }
            
            for(int next : list.get(cur.idx)){
                if(visit[next]) continue;
                visit[next] = true;
                que.add(new Node(next,cur.count+1));
            }
        }
                
        
        return answerCount;
    }
}