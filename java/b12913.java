
class Solution {
    int max = Integer.MIN_VALUE;


    int solution(int[][] land) {
        int answer = 0;
        int tmp[] = new int[4];
        int tmpTmp[] = new int[4];
        for(int i=0;i<4;i++)
        {
            tmp[i] = land[0][i];
        }

        if(land.length != 1)
        {
            for(int i=1;i<land.length;i++)
            {
                for(int cur=0;cur<4;cur++)
                {
                    //이전 prev가 0일때 
                    int tmpVal = 0;
                    int maxVal = 0;
                    for(int prev=0;prev<4;prev++)
                    {
                        if(prev != cur)
                        {
                            tmpVal =  land[i][cur] + tmp[prev];
                            maxVal = Math.max(maxVal, tmpVal);
                        }
                    }

                    tmpTmp[cur] = maxVal;
                }

                for(int k=0;k<4;k++)
                {
                    tmp[k] = tmpTmp[k];
                }
            }
        }
        
        for(int i=0;i<4;i++)
        {
            answer = Math.max(tmp[i],answer);
        }
        return answer;
    }
}

