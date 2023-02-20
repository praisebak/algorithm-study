import java.util.*;

class Solution {
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();

    boolean[] visit;
    void initInsert(int[][] computers)
    {
        for(int i=0;i<computers.length;i++)
        {
            
            ArrayList<Integer> curList = list.get(i);
            for(int j=0;j<computers[i].length;j++)
            {  
                if(i != j && computers[i][j] == 1)
                {
                    curList.add(j);
                    list.get(j).add(i);
                }
            }
        }

    }    
    public int solution(int n, int[][] computers) {
        for(int i=0;i<n;i++)
        {
            list.add(new ArrayList<>());
        }
        int answer = 0;
        initInsert(computers);
        visit = new boolean[n];
        for(int i=0;i<n;i++)
        {
            if(!visit[i])
            {
                dfs(i);
                answer++;
            }
        }

        return answer;
    }
    private void dfs(int startNode) 
    {
        visit[startNode] = true;
        for(int nextNode : list.get(startNode))
        {
            if(!visit[nextNode])
            {
                dfs(nextNode);
            }
        }

    }
}