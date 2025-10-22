import java.util.*;

class Solution {
    class Node{
        int left;
        int right;
        int val;
        
        public Node(int start,int next,int val){
            this.left=start;
            this.right=next;
            this.val=val;
        }
    }
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        List<Node> list = new ArrayList<>();
        
        for(int i=0;i<costs.length;i++){
            int left = costs[i][0];
            int right = costs[i][1];
            int val = costs[i][2];
            list.add(new Node(left,right,val));
        }
        
        Collections.sort(list,((o1,o2) -> o1.val - o2.val));
        parent = new int[n+1];

        for(int i=0;i<=n;i++){
            parent[i] = i;
        }
        
        for(Node node : list){
            int left = find(node.left);
            int right = find(node.right);
            
            if(left != right){
                union(left,right);
                answer+= node.val;
            }
        }
        
        return answer;
    }
    
    int[] parent;
    public int find(int idx){
        if(parent[idx] == idx){
            return idx;
        }
        
        return find(parent[idx]);
    }
    
    
    public void union(int left,int right){
        int l = find(left);
        int r = find(right);
        if(l < r){
            parent[l] = r;
        }else{
            parent[r] = l;
        }
        
    }
}