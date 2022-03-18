class Solution 
{
    int answer = Integer.MAX_VALUE;
    public int solution(int N, int number) 
    {
        dfs(N,number,0,0);
        if(answer == Integer.MAX_VALUE)
        {
            answer =  -1;
        }
        return answer;
    }

    private void dfs(int n, int objNum, int depth, int curNum) 
    {
        if(depth > 8)
        {
            return;
        }
        if(curNum == objNum)
        {
            this.answer = Math.min(answer,depth);
            return;
        }
        int tmpNum = 0;
        for(int i=0;i<8;i++)
        {
            if(depth + i + 1 <= 8)
            {
                tmpNum = tmpNum * 10 + n;
                dfs(n, objNum, depth + i + 1, curNum + tmpNum);
                dfs(n, objNum, depth + i + 1, curNum - tmpNum);
                dfs(n, objNum, depth + i + 1, curNum / tmpNum);
                dfs(n, objNum, depth + i + 1, curNum * tmpNum);
            }

        }
    }
}