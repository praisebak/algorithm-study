import java.util.*;


class Solution {
    int[] parent;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n+1];
        for(int i=0;i<=n;i++)
        {
            parent[i] = i;
        }
        //이거 참고할만한 코드
        //implement comparable은 귀찮으니까
        Arrays.sort(costs,(int[] c1,int[] c2) -> c1[2] - c2[2]);

        
        for(int i=0;i<costs.length;i++)
        {
            int from = costs[i][0];
            int to = costs[i][1];
            int fromParent = returnParent(from);
            int toParent = returnParent(to);

            if(fromParent != toParent)
            {
                parent[toParent] = fromParent;
                answer += costs[i][2];
            }
        }
        return answer;
    }

    int returnParent(int node)
    {
        if(node == parent[node])
        {
            return node;
        }else{
            return returnParent(parent[node]);
        }
    }

}
