class Solution {
    int[][] map;
    public int[] solution(int[][] map) {
        int[] answer = {};
        this.map = map;
        
        Result result = doQuadThing(0,0,map.length);
        answer = new int[]{result.zero,result.one};
        
        return answer;
    }
    
    class Result{
        int zero;
        int one;
        public Result(int zero,int one){
            this.zero=zero;
            this.one=one;
        }
    }
    public Result doQuadThing(int row,int col,int size){
        if(size == 1){
            if(map[row][col] == 1){
                return new Result(0,1);
            }else{
                return new Result(1,0);
            }
        }
        
        Result left = doQuadThing(row,col,size/2);
        Result right = doQuadThing(row,col+size/2,size/2);
        
        Result downLeft = doQuadThing(row+size/2,col,size/2);
        Result downRight = doQuadThing(row+size/2,col+size/2,size/2);
        
        int zeroCount = left.zero + right.zero + downLeft.zero + downRight.zero;
        int oneCount = left.one + right.one + downLeft.one + downRight.one;
        if(zeroCount == 0) return new Result(0,1);
        else if(oneCount == 0) return new Result(1,0);
        return new Result(zeroCount,oneCount);
    }
}