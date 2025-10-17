import java.util.*;

class Solution {
    Set<String> set = new HashSet<>();
    int answer = Integer.MAX_VALUE;
    String[] words;
    public int solution(String begin, String target, String[] words) {
        this.words=words;
        for(String s : words){
            set.add(s);
        }

        bfs(begin,target);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    class Node{
        String s;
        int time;
        public Node(String s,int time){
            this.s=s;
            this.time=time;
        }
    }
    
    public void bfs(String start,String target){
        PriorityQueue<Node> que = new PriorityQueue<>((o1,o2) -> o1.time - o2.time);
        que.add(new Node(start,0));

        Set<String> cur = new HashSet<>();
        while(!que.isEmpty()){
            Node node = que.poll();
            String s = node.s;
            if(s.equals(target)){
                answer = Math.min(answer,node.time);
                continue;
            }
            
            for(int i=0;i<words.length;i++){
                int count =0;
                if(cur.contains(words[i])) continue;
                
                for(int j=0;j<words[i].length();j++){
                    //지금 문자와 얼마나 다른지를 확인하면된다
                    if(words[i].charAt(j) != s.charAt(j)) count++;
                }
                if(count != 1) continue;
                
                que.add(new Node(words[i],node.time + 1));
                cur.add(words[i]);                    
            }
        }

    }
}