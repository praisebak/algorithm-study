class Solution {
    int[][] map;
    int N;
    int M;
    public int[] solution(int N, int M, int[][] queries) {
        int[] answer = new int[queries.length];
        this.map = new int[N][M];
        this.N=N;
        this.M=M;

        int idx = 1;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j] = idx++;
            }
        }
        

        for(int i=0;i<queries.length;i++){
            int[] s = queries[i];
            int startRow = s[0]-1;
            int startCol = s[1]-1;
            int endRow  = s[2];
            int endCol = s[3];
            
            answer[i] = move(startRow,startCol,endRow,endCol);
        }
        
        return answer;
    }
   
    public int move(int startRow,int startCol,int endRow,int endCol){
        int movedNum = map[startRow][startCol];        
        int min = movedNum;
        //우로 이동
        for(int i=startCol+1;i<endCol;i++){
            int tmp = map[startRow][i];
            map[startRow][i] = movedNum;
            min = Math.min(min,movedNum);
            min = Math.min(min,tmp);
            
            movedNum = tmp;
        }
        
        for(int i=startRow+1;i<endRow;i++){
            int tmp = map[i][endCol-1];
            map[i][endCol-1] = movedNum;
            min = Math.min(min,movedNum);
            min = Math.min(min,tmp);
            

            movedNum = tmp;
        }
        
        for(int i=endCol-2;i>=startCol;i--){
            int tmp = map[endRow-1][i];
            map[endRow-1][i] = movedNum;
            min = Math.min(min,movedNum);
            min = Math.min(min,tmp);

            movedNum = tmp;
        }
        
        for(int i=endRow-2;i>=startRow;i--){
            int tmp = map[i][startCol];
            map[i][startCol] = movedNum;
            min = Math.min(min,movedNum);
            min = Math.min(min,tmp);

            movedNum = tmp;
        }
        
        return min;

    }
}