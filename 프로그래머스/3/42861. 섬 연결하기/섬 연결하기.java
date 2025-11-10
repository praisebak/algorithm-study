import java.util.*;

class Solution {
    class Node{
        int left;
        int right;
        int val;
        public Node(int left,int right,int val){
            this.left=left;
            this.right=right;
            this.val=val;
        }
    }
    
    List<Node> list = new ArrayList<>();
    int[] parent;
    public int solution(int n, int[][] costs) {
        this.parent = new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i] = i;
        }
        
        for(int i=0;i<costs.length;i++){
            list.add(new Node(costs[i][0],costs[i][1],costs[i][2]));
        }
        Collections.sort(list,(o1,o2) ->o1.val - o2.val);
        
        int result = 0;
        
        for(Node node : list){
            int a = find(node.left);
            int b = find(node.right);
            if(a != b){
                union(a,b);
                result += node.val;
            }
            
            
        }
        
        
        return result;
    }
    
    public void union(int a,int b){
        int ax = find(a);
        int bx = find(b);
        if(ax < bx){
            parent[ax] = bx;
        }else{
            parent[ax] = bx;
        }
    }
    
    public int find(int x){
        if(x == parent[x]) return x;
        return find(parent[x]); 
    }
}