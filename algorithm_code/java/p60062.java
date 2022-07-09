import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    int minFriend = Integer.MAX_VALUE;
    int N = 0;

    public int solution(int n, int[] weak, int[] dist2) {
        //permutation
        //가장 많은 거리를 이동할 수 있는 친구부터
        Integer[] dist = Arrays.stream(dist2).boxed().toArray(Integer[] :: new);
        Arrays.sort(dist,Collections.reverseOrder());
        N = n;
        for(int i=0;i<weak.length;i++)
        {
            dfs(i,0,0,dist,weak);
        }

        if(minFriend == Integer.MAX_VALUE)
            return -1;

        return minFriend;
    }

    private void dfs(int start, int depth,int visit, Integer[] dist,int[] weak) 
    {
        if(depth == dist.length)
            return;

        if(depth+1 >= minFriend)
            return;

        for(int i=0;i<weak.length;++i)
        {
            int next = 0;
            int distance = 0;
            next = (start + i) % weak.length;
            distance = weak[next] - weak[start];

            if(next < start)
                distance += N;

            if(distance <= dist[depth])
                visit |= (1 << next);
            else
                break;
        }

        if(visit == (1 << weak.length) -1)
        {
            minFriend = Math.min(minFriend,depth+1);
            return;
        }
                
        for(int i=0;i<weak.length;++i)
        {
            if((visit & (1 << i)) != 0)
                continue;
            dfs(i, depth+1, visit, dist, weak);
        }
    }
}