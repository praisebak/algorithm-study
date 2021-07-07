import java.util.Arrays;

class p12913 {
    
    boolean visit[];
    int max = Integer.MIN_VALUE;
    
    void backTracking(int[][] land,int row,int sum)
    {
        if(land.length <= row)
        {
            max = Math.max(sum, max);
            return;
        }

        int tmpMax = 0;
        int tmpIdx = 0;
        for(int i=0;i<land[0].length;i++)
        {
            if(visit[i]!=false)
            {
                if(tmpMax < land[row][i])
                {
                    tmpIdx = i;
                    tmpMax = land[row][i];
                }
            }
        }
        visit[tmpIdx] = true;
        backTracking(land,row+1,sum+tmpMax);

        
    }
    
    
    
    int solution(int[][] land) {
        int answer = 0;
        visit =  new boolean[land[0].length];
        //첫번째에 어떤 cal을 선택했느냐
        
        if(land.length == 1)
        {
            for(int i=0;i<land[0].length;i++)
            {
                max = Math.max(max,land[0][i]);
            }
        }
        else
        {
            for(int i=0;i<land[0].length;i++)
            {  
                Arrays.fill(visit,false);
                visit[i] = true;
                backTracking(land,i+1,land[0][i]);
            }
        }

        answer = max; 

        return answer;
    }
}