import java.util.ArrayList;
import java.util.Arrays;

class Solution
{
    
    
    ArrayList<ArrayList<Integer> > graph = new ArrayList<>();
    ArrayList<ConnectPair> connectPairs = new ArrayList<>();
    boolean nodeVisit[];

    
    public int solution(int n, int[][] wires) 
    {  
        int answer = 987654321;
        nodeVisit = new boolean[n+1];
        for(int i=0;i<=n;i++)
        {
            graph.add(new ArrayList<Integer>());
        }

        connectGraph(wires);

        //시작 인덱스를 항상 0으로 두자
        //절단할 pair의 인덱스만 전달해주자
        for(ConnectPair pair : connectPairs)
        {
            boolean visit[][] = new boolean[n+1][n+1];
            Arrays.fill(nodeVisit, false);
            for(boolean[] iter : visit)
            {
                Arrays.fill(iter, false);
            }

            int depth = 0;
            int result = 0;
            for(int j=1;j<=graph.size()-1;j++)
            {

                if(!nodeVisit[j])
                {
                    depth = dfs(j,pair,visit,1);
                    if(result != 0)
                    {
                        result = Math.abs(depth - result);
                        answer = Math.min(result,answer);
                        break;
                    }
                    else
                    {
                        result = depth;
                    }
                    
                }
            }

        }

        return Math.abs(answer);
    }
                                
    int dfs(int curIdx,ConnectPair removeConnection,boolean[][] visit,int count)
    {
        nodeVisit[curIdx] = true;
        int sub = removeConnection.sub;
        int obj = removeConnection.obj;
        for(Integer next : graph.get(curIdx))
        {

            if(!visit[curIdx][next])
            {

                visit[curIdx][next] = true;
                visit[next][curIdx] = true;

                if( !(Math.max(curIdx,next) == obj && sub == Math.min(curIdx,next)) )
                {


                    count = Math.max(count,dfs(next, removeConnection, visit, count + 1));
                }
            }
        }

        return count;

    }


    private void connectGraph(int[][] wires) 
    {
        int curVal = 0;
        int nextVal = 0;
        ArrayList<Integer> curNode = new ArrayList<>();
        
        for(int i=0;i<wires.length;i++)
        {
            curVal = wires[i][0];
            nextVal = wires[i][1];
            ConnectPair curPair = new ConnectPair(curVal, nextVal);
            curNode = graph.get(curVal);
            graph.get(nextVal).add(curVal);
            curNode.add(nextVal);
            connectPairs.add(curPair);
        }
    }

    class ConnectPair
    {
        int sub = 0;
        int obj = 0;

        ConnectPair(int sub,int obj)
        {
            this.sub = sub;
            this.obj = obj;
        }
    }

}