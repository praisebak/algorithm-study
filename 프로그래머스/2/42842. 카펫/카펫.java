class Solution {
    int brown;
    int yellow;
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        this.brown = brown;
        this.yellow = yellow;
        //row
        for(int i=1;i<= brown/2;i++){
            //col
            for(int j=1;j<= brown;j++){
                if(i > j) continue;
                if(dfs(i,j)){
                    return new int[]{j,i};
                }
            }
        }
        return answer;
    }
    
    public boolean dfs(int row,int col){
        int brownSum = row * 2;
        
        int brownCol = col * 2 -4; 
        
        int area = row * col;
        if(area == brownSum + brownCol + yellow && brown == brownSum + brownCol){
            return true;
        }
        return false;
    }
}