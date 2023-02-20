import java.util.*;
class Solution
{
    int[][] graph;
    int INF;

    public void floyd(int n)
    {
        for(int k=1;k<=n;k++)
        {
            for (int i = 1; i <= n ; i++)
            {
                for (int j = 1; j <= n ; j++)
                {
                    graph[i][j] = Math.min(graph[i][k] + graph[k][j],graph[i][j]);
                }
            }
        }
    }

    public int solution(int n, int[][] results)
    {
        INF = n * n + 1;
        graph = new int[n+1][n+1];
        initGraph(results,n);
        floyd(n);
        int answer = check(n);
        return answer;
    }

    private int check(int n)
    {
        int result = 0;
        for(int i=1;i<=n;i++)
        {
            boolean flag = true;
            for(int j=1;j<=n;j++)
            {
                if(i != j && graph[i][j] == INF && graph[j][i] == INF)
                {
                    flag = false;
                    break;
                }
            }
            if(flag)
            {
                result++;
            }
        }
        return result;
    }

    public void initGraph(int[][] results,int n)
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                graph[i][j] = INF;
            }
        }

        for(int i=0;i<results.length; i++)
        {
            graph[results[i][0]][results[i][1]] = 1;
        }
    }

}