import java.util.*;
class Solution {
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static Boolean visit[][];
    static int maxNum = 0;

    void dfs(int[][] picture,int x,int y)
    {
        int curX = 0,curY = 0;

        if(visit[x][y])
        {
            return;
        }  
        visit[x][y] = true;
          
        maxNum++;

        for(int i=0;i<4;i++)
        {
            curX = dx[i] + y;
            curY = dy[i] + x;
            
            if(curX >= picture[x].length || curX < 0 || curY >= picture.length || curY < 0)
            {
                continue;
            }
            if(picture[x][y] != picture[curY][curX])
            {
                continue;
            }
            if(visit[curY][curX])
            {
                continue;
            }
            dfs(picture,curY,curX);

        }
        

 
    }
    
    public int[] solution(int m, int n, int[][] picture) 
    {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int[] answer = new int[2];
        visit = new Boolean[picture.length][picture[0].length];
        for(Boolean[] iter : visit)
        {
            Arrays.fill(iter,false);
        }


        
        for(int i=0;i<picture.length;i++)
        {
            for(int j=0;j<picture[i].length;j++)
            {

                if(!visit[i][j])
                {
                    if(picture[i][j] != 0)
                    {
                        maxNum = 0;
                        dfs(picture,i,j);
                        maxSizeOfOneArea = Math.max(maxSizeOfOneArea,maxNum);
                        numberOfArea++;

                    }
                        
                }

            }
        }
    
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        
        return answer;
    }
}