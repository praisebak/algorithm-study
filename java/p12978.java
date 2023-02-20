import java.util.*;


class Node
{
    int weight;
    int nodeIdx;
    Node(int weight,int idx)
    {
        this.weight = weight;
        this.nodeIdx = idx;
    }
}

class Solution
{
    ArrayList<ArrayList<Node> > graph = new ArrayList<>();
    boolean[] isVisit;
    int[] weights;

    private void connectGraph(int n, int[][] road) 
    {
        for(int i=0;i<=n;i++)
        {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<road.length;i++)
        {
            int curNode = road[i][0];
            int nextNode = road[i][1];
            int weight = road[i][2];
            //System.out.println(curNode + " -> " + nextNode + " 연결");
            graph.get(curNode).add(new Node(weight,nextNode));
            graph.get(nextNode).add(new Node(weight,curNode));

        }
        Arrays.fill(weights, Integer.MAX_VALUE);


    }


    private void dijkstra(int n) 
    {  
        weights[1] = 0;
        for(int i=0; i <n;i++)
        {
            Node node = new Node(Integer.MAX_VALUE,i);
            
            for(int j=1;j<=n;j++)
            {
                if(!isVisit[j] && weights[j] < node.weight )
                {
                    node.nodeIdx = j;
                    node.weight = weights[j];
                }
            }
            //System.out.println("현재 노드 " + node.nodeIdx + " " + node.weight);

            isVisit[node.nodeIdx] = true;
            for(int j=0;j< graph.get(node.nodeIdx).size();j++)
            {
                Node nextNode = graph.get(node.nodeIdx).get(j);
                if(weights[nextNode.nodeIdx] > weights[node.nodeIdx] + nextNode.weight)
                {
                    
                    weights[nextNode.nodeIdx] = weights[node.nodeIdx] + nextNode.weight;
                    //System.out.println("갱신된 노드 : " + nextNode.nodeIdx + " 값 : " + weights[nextNode.nodeIdx]  );

                }
            }
        }



    }   


    public int solution(int N,int arr[][],int K)
    {
        int answer = 0;
        isVisit = new boolean[N+1];
        weights = new int[N+1];
        connectGraph(N, arr);
        dijkstra(N);
        
        for(int i=0;i<=N;i++)
        {
            //System.out.println(weights[i]);
            if(weights[i] <= K)
            {
                answer++;
            }
        }
        
        //System.out.println(answer);
        return answer;
    }
    public static void main(String[] args) 
    {        
        Solution solution = new Solution();
        int[][] tmp = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        solution.solution(5,tmp , 3);

    }
}

/*
class Solution
{
    ArrayList<ArrayList<Integer> > graph = new ArrayList<>();

    int[][] weights;
    int[][] resultWeights;
    boolean[][] visit;
    int[] resultWeight;

    public int solution(int N, int[][] road, int K) 
    {
        
        weights = new int[N+1][N+1];
        resultWeights = new int[N+1][N+1];
        resultWeight = new int[N+1];
        visit = new boolean[N+1][N+1];

        for(int[] weight : resultWeights)
        {
            Arrays.fill(weight, Integer.MAX_VALUE);
        }
        for(int i=1;i<=N;i++)
        {
            resultWeight[i] = Integer.MAX_VALUE;
        }
        for(boolean[] iter : visit)
        {
            Arrays.fill(iter, false);
        }
        connectGraph(N,road);
        bfs(N,road,K);
        int answer = 0;
        answer = getCountVisit(K);
        return answer;
    }

    private int getCountVisit(int K) 
    {
        int count = 0;
        

        //System.out.println();
        for(int i=1;i<resultWeight.length;i++)
        {
            //System.out.print(resultWeight[i] + " ");
            if(resultWeight[i] <= K)
            {
                count++;
            }
        }
        return count;
    }

    private void connectGraph(int n, int[][] road) 
    {
        for(int i=0;i<=n;i++)
        {
            graph.add(new ArrayList<Integer>());
        }

        for(int i=0;i<road.length;i++)
        {
            int curNode = road[i][0];
            int nextNode = road[i][1];
            int weight = road[i][2];
            graph.get(curNode).add(nextNode);
            graph.get(nextNode).add(curNode);
            weights[curNode][nextNode] = weight;
            weights[nextNode][curNode] = Math.min(weights[curNode][nextNode],weight);
        }
    }

    private void bfs(int n, int[][] road, int k) 
    {
        Queue<Integer> nodeQue = new LinkedList<>();
        Queue<Integer> weightQue = new LinkedList<>();
        int curNode = 0;
        int curWeight = 0;
        nodeQue.add(1);
        weightQue.add(0);
        resultWeight[1] = 0;

        while(nodeQue.size() != 0)
        {
            curNode = nodeQue.poll();
            curWeight = weightQue.poll();
            for(Integer next : graph.get(curNode))
            {
                if(!visit[curNode][next] && curWeight <= k)
                {
                    visit[curNode][next] = true;
                    visit[next][curNode] = true;
                    //System.out.println(curNode + "->" + next + " W : " + curWeight + "-> +" + weights[curNode][next]);
                    resultWeights[curNode][next] = Math.min(resultWeights[curNode][next],
                                    curWeight + weights[curNode][next]);
                    resultWeights[next][curNode] = resultWeights[curNode][next];
                    nodeQue.add(next);
                    weightQue.add(curWeight + weights[curNode][next]);
                    resultWeight[next] = Math.min(curWeight + weights[curNode][next],resultWeight[next]);
                }
            }
        }

        
    }

    public static void main(String[] args) 
    {        
        Solution solution = new Solution();
        int[][] tmp = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        solution.solution(5,tmp , 3);

    }
}  
*/