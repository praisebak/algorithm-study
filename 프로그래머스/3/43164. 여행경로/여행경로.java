import java.util.*;
class Solution {

    class Node implements Comparable<Node>{
        String left;
        String right;
        int idx;
        public Node(String left,String right,int idx){
            this.left=left;
            this.right=right;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Node other){
            if(left.equals(right)){
                return right.compareTo(other.right);
            }
            return left.compareTo(other.left);
        }
    }
    
    boolean[] tIdx;
    Map<String,List<Node>> map;
    
    int tN;
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        tIdx = new boolean[tickets.length+1];
        tN = tickets.length;
        
        //그냥 티켓을 다 돌면 느리다
        //그럼 어떡하지?
        //map에 가능한 티켓을 넣어서 하고
        //visit을 체크하자
        List<Node> list = new ArrayList<>();
    
        map = new HashMap<>();
         
        for(int i=0;i<tickets.length;i++){
            String left = tickets[i][0];
            String right = tickets[i][1];
            list.add(new Node(left,right,i));
        }
        
        Collections.sort(list);
        
        for(Node node : list){
            List<Node> nodeList = map.getOrDefault(node.left,new ArrayList<>());
            
            nodeList.add(new Node(node.left,node.right,node.idx));
            map.put(node.left,nodeList);
        }
        
        dfs("ICN",0,"ICN");
        
        String[] rr = result.split(" ");
        return rr;
    } 
    
    String result = "Z";
    
    Set<String> visit = new HashSet<>();
    
    public void dfs(String s, int count,String result){
        if(tN == count){
            if(this.result.compareTo(result) > 0){
                this.result = result;
            }
            return;
        }
        
        for(Node next: map.getOrDefault(s,new ArrayList<>())){
            if(tIdx[next.idx]){
                continue;
            }
            
            tIdx[next.idx] = true;
            dfs(next.right,count+1,result + " " + next.right);
            tIdx[next.idx] = false;
        }
    }
}