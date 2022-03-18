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

/*
import java.util.ArrayList;
import java.util.List;

class Solution 
{
    int n;
    int answer = Integer.MAX_VALUE;

    ArrayList<Integer>[] dp = new ArrayList[10];
    public int solution(int N, int number) 
    {
        dp[0] = new ArrayList<>();
        dp[0].add(0);
        n = N;
        for(int i=1;i<=8;i++)
        {
            dp(i);
            if(dp[i].contains(number))
            {
                answer = i;
                break;
            }
        }
        if(answer == Integer.MAX_VALUE) 
            answer = -1;
        return answer;
    }

    List<Integer> dp(int depth)
    {
        if(dp[depth] != null)
        {
            return dp[depth];
        }
        dp[depth]= new ArrayList<>();
        dp[depth].add(dp[depth-1].get(0) * 10 + n);
        for(int i=1;i<depth;i++)
        {
            int j = depth-i;
            List<Integer> dpOne = dp(i);
            List<Integer> dpTwo = dp(j); 
            for(int n1 : dpOne)
            {
                for(int n2 :dpTwo)
                {
                    dp[depth].add(n1 + n2);
                    dp[depth].add(n1 - n2);
                    dp[depth].add(n1 * n2);
                    if(n2 !=0)
                    {
                        dp[depth].add(n1 / n2);
                    }

                }
            }
        }
        return dp[depth];
    }

}
*/