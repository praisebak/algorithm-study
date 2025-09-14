import java.util.*;

class Solution {
    class HashNode implements Comparable<HashNode>{
        int firstVal;
        int val;
        int idx;
        public HashNode(int firstVal,int val,int idx){
            this.firstVal=firstVal;
            this.val=val;
            this.idx=idx;
        }
        
        @Override
        public int compareTo(HashNode other){
            if(val == other.val){
                return other.firstVal - firstVal;
            }
            return this.val - other.val;
        }
    }
    
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        List<HashNode> list = new ArrayList<>();
        col--;
        row_begin--;
        row_end--;
        
        for(int i=0;i<data.length;i++){
            int colV = data[i][col];
            int firstV = data[i][0];
            list.add(new HashNode(firstV,colV,i));
        }
        
        Collections.sort(list);
        
        int sum = 0;
        for(int i=row_begin;i<=row_end;i++){
            HashNode node = list.get(i);
            int tmp = 0;

            for(int j=0;j<data[0].length;j++){
                tmp += data[node.idx][j] % (i+1);
            }
            sum ^= tmp;
        }

        
        return sum;
    }
}