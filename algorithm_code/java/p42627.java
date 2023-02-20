import java.util.*;

class Pair
{
    int idx;
    int val;
    Pair(int idx,int val)
    {
        this.idx = idx;
        this.val = val;
    }
}
//각각 요소들에 있어서 경우에따른 처리가 완벽한가 생각
class Solution {
    int[][] jobs;
    boolean[] visit;
    PriorityQueue<int[]> heap = new PriorityQueue<>((i1,i2) -> i1[1] - i2[1] );
    
    public static void main(String[] args) {
        int[][] jobs = {{0,3},{1,9},{2,6}};
        Solution solu = new Solution();
        solu.solution(jobs);
    }

    public int solution(int[][] jobs) {
        
        this.jobs = jobs;
        int count = 0;
        int curTime = 0;
        int answer = 0;
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        visit = new boolean[jobs.length];
        
        while(count != jobs.length)
        {
            heapInsert(curTime);
            if(heap.isEmpty())
            {
                curTime = jobs[count][0];
                continue;
            }
            int[] cur = heap.poll();
            answer += cur[1] + curTime - cur[0];
            curTime += cur[1];
            count++;
        }

        return answer / count;
    }

    private int heapInsert(int endTime) 
    {
        int result = -1;
        int frontIdx = Integer.MAX_VALUE;
        for(int i=0;i<jobs.length;i++)
        {
            if(!visit[i] && jobs[i][0] <= endTime)
            {
                visit[i] = true;
                heap.add(jobs[i]);
                result=1;
            }
        }
        return result;
    }
}