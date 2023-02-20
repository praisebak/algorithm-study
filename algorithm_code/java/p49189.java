import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Pair
{
    int node;
    int depth;
    Pair(int node,int depth)
    {
        this.node = node;
        this.depth = depth;
    }
}


class Solution 
{
    int result = 0;
    boolean[] visit;
    int[][] graph;
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();

    public int solution(int n, int[][] edge) 
    {
        for(int i=0;i<=n;i++)
        {
            list.add(new ArrayList<>());
        }

        visit = new boolean[n+1];

        initGraph(edge);
        BFS(edge);
        int answer = result;
        return answer;
    }

    private void initGraph(int[][] edge) 
    {
        for(int i=0;i<edge.length;i++)
        {
            list.get(edge[i][0]).add(edge[i][1]);
            list.get(edge[i][1]).add(edge[i][0]);
        }
    }

    private void BFS(int[][] edge)
    {
        Queue<Pair> que = new LinkedList<>();

        que.add(new Pair(1,0));
        int tmpMax = 0;
        while(que.size()!= 0)
        {
            Pair curPair = que.poll();
            int curNode = curPair.node;
            int count = 0;
            visit[curNode] = true;

            for(int i=0;i<list.get(curNode).size();i++)
            {
                int nextNode = list.get(curNode).get(i);
                int nextDepth = curPair.depth+1;
                if(!visit[nextNode])
                {
                    que.add(new Pair(nextNode,nextDepth));
                    visit[nextNode] = true;
                    if(tmpMax < nextDepth)
                    {
                        result = 1;
                    }
                    if(nextDepth == tmpMax)
                    {
                        result++;
                    }
                    tmpMax = Math.max(tmpMax,nextDepth);

                }
            }
            
        }
    }
}